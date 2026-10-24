/**
 * An associative data structure (map) mapping {@link Physical} keys
 * to {@link Physical} values.
 *
 * <p>This class represents a minimal key-value association structure.
 * Each {@link Physical} key object is associated with exactly one {@link Physical} value.</p>
 *
 * <p>The associations are stored in a binary search tree ordered according
 * to a {@link PhysicalComparator}.</p>
 */
public class PhysicalPhysicalTreeMap implements PhysicalPhysicalMap {

    private PhysicalPhysicalTreeMapNode[] root = new PhysicalPhysicalTreeMapNode[1];
    private int size;
    private PhysicalComparator comparator;

    /**
     * Shared mutable modification counter used to detect concurrent structural
     * modifications during iteration.
     * <p>
     * The counter value is incremented whenever the tree structure changes.
     * Wrapping the counter inside a single-element array allows the counter to be
     * shared by reference between the map, its views, and active iterators.
     * </p>
     */
    private int[] modCounterBox = new int[1];

    //TODO: edit according to `angabe.md`: update modCounterBox where necessary.

    /**
     * Creates an empty map ordered according to {@link XComparator}.
     */
    public PhysicalPhysicalTreeMap() {

        this.root[0] = PhysicalPhysicalTreeMapNodeEmpty.EMPTY;
        this.size = 0;
        this.comparator = new XComparator();
    }

    /**
     * Creates an empty map with the specified comparator.
     *
     * @param comparator the comparator defining the key order; {@code comparator != null}
     */
    public PhysicalPhysicalTreeMap(PhysicalComparator comparator) {

        this.root[0] = PhysicalPhysicalTreeMapNodeEmpty.EMPTY;
        this.size = 0;
        this.comparator = comparator;
    }

    /**
     * Returns the comparator used by this tree map for ordering and lookup
     * operations.
     * <p>
     * The returned comparator defines the key order of this map and must also be
     * used by any views backed by this map.
     * </p>
     *
     * @return the comparator used by this map
     */
    public PhysicalComparator comparator() {
        return comparator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Physical put(Physical key, Physical value) {


        Physical[] oldValueBox = new Physical[1];

        boolean[] insertedBox = new boolean[]{false};

        root[0] = root[0].put(key, value, comparator, oldValueBox, insertedBox);

        if (insertedBox[0]) {
            size++;
            modCounterBox[0]++;
        }
        return oldValueBox[0];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Physical get(Physical key) {

        return root[0].get(key, comparator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(Physical key) {

        return root[0].containsKey(key, comparator);
    }

    /**
     * {@inheritDoc}
     */
    public boolean containsValue(Physical value) {

        return root[0].containsValue(value);
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
        if (root != null) modCounterBox[0]++;
        root[0] = PhysicalPhysicalTreeMapNodeEmpty.EMPTY;
        size = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PhysicalSet keySetView() {

        // TODO: implement method.
        return new PhysicalPhysicalTreeMapSetView(this, root, modCounterBox);
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

        if (size == 0) {
            return "(empty)";
        }

        return root[0].toIndentedString("");
    }
}