/**
 * An associative data structure (map) mapping {@link Physical} keys
 * to {@link Physical} values.
 *
 * TODO: change this file according to `Teilaufgabe 1` in `angabe.md`.
 *
 * <p>This class represents a key-value association structure.
 *  Each {@link Physical} key is associated with exactly one {@link Physical} value.
 *  Key equality is based on structural equality (using the {@code equals} method),
 *  ensuring the Liskov Substitution Principle. This means that two distinct
 *  objects that represent the same physical entity are treated as the same key.</p>
 *
 *  <p>The associations are stored in a binary search tree ordered according
 *  to a {@link PhysicalComparator}.</p>
 */
public class PhysicalPhysicalTreeMap implements PhysicalPhysicalMap {

    private PhysicalPhysicalTreeMapNode root;
    private int size;
    private PhysicalComparator comparator;

    /**
     * Creates an empty map ordered according to {@link XComparator}.</p> .
     */
    public PhysicalPhysicalTreeMap() {

        this.root = null;
        this.size = 0;
        this.comparator = new XComparator();
    }

    /**
     * Creates an empty map with the specified comparator.
     *
     * @param comparator the comparator defining the key order; {@code comparator != null}
     */
    public PhysicalPhysicalTreeMap(PhysicalComparator comparator) {

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
     * @param key the key; {@code key != null}
     * @param value the value; {@code a != null}
     * @return the previously associated value, or {@code null} if none existed
     */
    @Override
    public Physical put(Physical key, Physical value) {

        if (root == null) {
            root = new PhysicalPhysicalTreeMapNode(key, value, comparator);
            size++;
            return null;
        }

        Physical old = root.put(key, value);

        if (old == null) {
            size++;
        }

        return old;
    }

    /**
     * Returns the value associated with the specified key.
     *
     * <p>If this map contains a key identical to {@code key},
     * the associated value is returned.</p>
     *
     * @param key the key; {@code key != null}
     * @return the associated value, or {@code null} if the key is not present
     */
    @Override
    public Physical get(Physical key) {

        return root == null ? null : root.get(key);
    }

    /**
     * Returns whether this map contains the specified physical object as a key.
     *
     * <p>This method checks for object identity, not structural equality.
     * That is, it returns {@code true} if and only if the exact same object
     * reference (using {@code ==}) is stored in this tree as key.</p>
     *
     * @param key the key to check; {@code key != null}
     * @return {@code true} if the key exists in the map,
     *         {@code false} otherwise
     */
    @Override
    public boolean containsKey(Physical key) {

        return root != null && root.containsKey(key);
    }

    /**
     * Returns whether this map contains the given object as value.
     *
     * <p>This method checks object identity, not structural equality.</p>
     *
     * @param value the value to check; {@code value != null}
     * @return {@code true} if the value exists in the map,
     *         {@code false} otherwise
     */
    public boolean containsValue(Physical value) {

        return root != null && root.containsValue(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {

        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {

        return size == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {

        root = null;
        size = 0;
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
    public PhysicalDoublyLinkedList keys() {

        PhysicalDoublyLinkedList result = new PhysicalDoublyLinkedList();

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
    public Physical[] values() {

        Physical[] result = new Physical[size];

        if (root != null) {
            root.fillValues(result, 0);
        }

        return result;
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
     * FoodSource@(350.0, 300.0), r=80.0 -> FoodSource@(352.0, 300.0), r=90.0
     *   FoodSource@(300.0, 200.0), r=50.0 -> FoodSource@(350.0, 300.0), r=80.0
     *   Nest@(400.0, 300.0), r=100.0 -> FoodSource@(350.0, 300.0), r=80.0
     *     Nest@(420.0, 320.0), r=20.0 -> FoodSource@(350.0, 300.0), r=80.0
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
}