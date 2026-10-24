/**
 * An associative data structure (map) mapping {@link Physical} keys
 * to {@link Physical} values.
 *
 * <p>This class represents a minimal key-value association structure.
 * Each {@link Physical} key object is associated with exactly one {@link Physical} value.
 *
 * <p>The associations are stored in a binary search tree ordered according
 * to a {@link PhysicalComparator}.</p>
 * <p>
 * TODO: change this file according to `angabe.md`
 */
public class PhysicalPhysicalTreeMap implements PhysicalPhysicalMap, PhysicalIterable //TODO: activate clause
{

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
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public Physical get(Physical key) {

        return root == null ? null : root.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(Physical key) {

        return root != null && root.containsKey(key);
    }

    /**
     * {@inheritDoc}
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
     * <p>
     * FoodSource@(350.0, 300.0), r=80.0 -> FoodSource@(352.0, 300.0), r=90.0
     * FoodSource@(300.0, 200.0), r=50.0 -> FoodSource@(350.0, 300.0), r=80.0
     * Nest@(400.0, 300.0), r=100.0 -> FoodSource@(350.0, 300.0), r=80.0
     * Nest@(420.0, 320.0), r=20.0 -> FoodSource@(350.0, 300.0), r=80.0
     * </p>
     *
     * @return a string representation of this map
     */
    @Override
    public String toString() {

        if (root == null) {
            return "(empty)";
        }

        return root.toIndentedString("");
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns an iterator over all keys stored in this map in descending
     * order according to the comparator of this tree map.
     *
     * <p>The iterator performs a reverse in-order traversal of the
     * underlying binary search tree.</p>
     *
     * <p>If the map is empty, the returned iterator has no
     * elements.</p>
     */
    @Override
    public PhysicalIterator iterator() {

        // TODO: implement method.
        PhysicalTreeIterator iter = new PhysicalTreeIterator();
        if (root != null) {
            root.iter(iter, false);
        }
        return iter;
    }
}