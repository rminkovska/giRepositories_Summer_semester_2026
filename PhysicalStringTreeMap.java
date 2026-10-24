/**
 * A simple associative data structure (map) mapping {@link Physical} keys
 * to {@link String} values.
 *
 * <p>This class represents a minimal key-value association structure.
 * Each {@code Physical} key object is associated with exactly one {@code String} value.
 * Internally, the associations are stored in a binary search tree.</p>
 *
 * <p>The tree is ordered lexicographically by the coordinates of the keys:
 * first by x-coordinate, and if these are equal, by y-coordinate.
 * Example: (1.0,2.0) < (1.0,3.0) < (1.1, 0.0)</p>
 *
 * <p>Note that the tree may contain multiple distinct key objects with the same
 * coordinates, as long as they are not identical (see {@code containsKey}).</p>
 *
 * TODO: change this file according to `Teilaufgabe 3` in `angabe.md`.
 */
public class PhysicalStringTreeMap {

    private PhysicalStringTreeMapNode root;
    private int size;
    private final PhysicalComparator comparator;
    /**
     * Creates an empty map (with no associations).
     *
     * <p>The key order is according to the x-coordinate of the {@link Physical}
     * object. If the x-coordinates are equal, the y-coordinate is used as a
     * tie-breaker.</p>
     */
    public PhysicalStringTreeMap() {

        this(new XComparator());
    }
    /**
     * Returns a string representation of this map.
     *
     * <p>Each node of the underlying binary search tree is shown in its own line.
     * Child nodes are indented relative to their parent so that the tree structure
     * becomes visible.</p>
     *
     * <p>The left subtree is shown before the right subtree.</p>
     *
     * <p>
     * Example:
     *
     * FoodSource@(350.0, 300.0), r=80.0 -> "A"
     *   FoodSource@(300.0, 200.0), r=50.0 -> "B"
     *   Nest@(400.0, 300.0), r=100.0 -> "C"
     *     Nest@(420.0, 320.0), r=20.0 -> "D"
     * </p>
     *
     * @return a string representation of this map
     */
    @Override
    public String toString() {

        //TODO: implement method.
        if (root == null) {
            return "(empty)";
        }
        return root.toIndentedString("");
    }


    /**
     * Creates an empty map with the specified comparator that defines the key order
     * of this tree.
     *
     * @param comparator the comparator defining the key order; {@code comparator != null}
     */
    public PhysicalStringTreeMap(PhysicalComparator comparator) {
        this.root = null;
        this.size = 0;
        this.comparator = comparator;
    }

    /**
     * Inserts a new association into the map.
     *
     * <p>If the specified key object is already stored in this map
     * (see {@code containsKey}), its associated value is replaced by the given
     * value.</p>
     *
     * <p>Otherwise, a new association is inserted.</p>
     *
     * @param k the key; {@code k != null}
     * @param a the value; {@code a != null}
     * @return the previously associated value, or {@code null} if none existed
     */
    public String put(Physical k, String a) {

        if (root == null) {
            root = new PhysicalStringTreeMapNode(k, a, comparator);
            size++;
            return null;
        }

        String toReturn = root.put(k, a);

        if (toReturn == null) {
            size++;
        }
        return toReturn;
    }

    /**
     * Returns the value associated with the specified key object.
     *
     * <p>If the specified key object is contained in this map (see {@code containsKey}),
     * its associated value is returned. Otherwise, the method returns {@code null}.</p>
     *
     * @param k the key to search for; {@code k != null}
     * @return the associated {@link String}, or {@code null} if the key is not present
     */
    public String get(Physical k) {

        return root == null ? null : root.get(k);
    }

    /**
     * Returns whether this map contains the specified physical object as a key.
     *
     * <p>This method checks for object identity, not structural equality.
     * That is, it returns {@code true} if and only if the exact same object
     * reference (using {@code ==}) is stored in this tree as key.</p>
     *
     * @param k the key to check; {@code k != null}
     * @return {@code true} if the key exists in the map,
     *         {@code false} otherwise
     */
    public boolean containsKey(Physical k) {

        return root != null && root.containsKey(k);
    }

    /**
     * Returns whether this map contains the given string as value.
     *
     * <p>The tree is searched recursively.</p>
     *
     * @param v the value to check; {@code v != null}
     * @return {@code true} if the value exists in the map,
     *         {@code false} otherwise
     */
    public boolean containsValue(String v) {

        return root != null && root.containsValue(v);
    }

    /**
     * Returns the number of stored associations.
     *
     * @return the number of key-value pairs stored in this map
     */
    public int size() {

        return size;
    }

    /**
     * Returns all keys stored in this map.
     *
     * <p>The returned list contains all {@link Physical} keys currently
     * stored in the map. Each key appears exactly once in the list.
     * The keys are returned in ascending order according to the key order
     * of this tree map. The smallest key appears at index {@code 0}
     * of the returned list.</p>
     *
     * @return a list containing all stored keys
     */
    public PhysicalSinglyLinkedList keys() {

        PhysicalSinglyLinkedList result = new PhysicalSinglyLinkedList();
        if (root != null) {
            root.fillKeysInOrder(result);
        }
        return result;
    }

    /**
     * Returns all values stored in this map.
     *
     * <p>The returned array contains all values currently stored in the map.
     * Duplicate entries may occur if different keys are associated with equal
     * values.</p>
     *
     * <p>The values are written in ascending order of their associated keys.</p>
     *
     * @return an array containing all stored values
     */
    public String[] values() {

        String[] result = new String[size];
        if (root != null) {
            root.fillValues(result, 0);
        }
        return result;
    }
}