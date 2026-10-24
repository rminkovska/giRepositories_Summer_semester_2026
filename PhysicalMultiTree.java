/**
 * A multi-way tree of {@link Physical} objects.
 *
 * <p>Each node stores exactly one primary {@code Physical} object.
 * A node may have arbitrarily many direct child nodes.</p>
 *
 * <p>This tree represents a containment hierarchy of circles.
 * Two stored objects may either be disjoint or one may fully contain the other.
 * Partial overlap is not allowed.</p>
 *
 * TODO: make this class `PhysicalIterable` according to `angabe.md`
 */
public class PhysicalMultiTree implements PhysicalIterable //TODO: activate clause
{

    private PhysicalMultiTreeNode root;
    private int size;

    /**
     * Creates an empty tree.
     */
    public PhysicalMultiTree() {

        this.root = PhysicalMultiTreeNodeEmpty.EMPTY;
        this.size = 0;
    }

    /**
     * Creates a tree with the specified root object.
     *
     * @param rootValue the root object; {@code rootValue != null}
     */
    public PhysicalMultiTree(Physical rootValue) {

        this.root = new PhysicalMultiTreeNodeLeaf(rootValue);
        this.size = 1;
    }

    /**
     * Returns the primary object of this tree.
     *
     * @return the primary object, or {@code null} if this tree is empty
     */
    public Physical getPrimary() {

        return root.getValue();
    }

    /**
     * Returns the number of {@code Physical} elements currently stored in this tree.
     *
     * @return the number of elements
     */
    public int size() {

        return size;
    }

    /**
     * Returns whether this tree contains no {@code Physical} elements.
     *
     * @return {@code true} if this tree is empty, otherwise {@code false}
     */
    public boolean isEmpty() {

        return size == 0;
    }

    /**
     * Returns whether this tree contains an object equal to the specified object
     * (as defined by {@link Physical#equals}).
     *
     * @param p the object to search for; {@code p != null}
     * @return {@code true} if an equal object is stored in this tree,
     *         otherwise {@code false}
     */
    public boolean contains(Physical p) {

        return root.contains(p);
    }

    /**
     * Inserts the specified object into this tree.
     *
     * <p>The insertion succeeds if and only if one of the following holds:</p>
     * <ul>
     *   <li>the tree is empty, or</li>
     *   <li>the object fits fully into an existing stored object, or</li>
     *   <li>the object fully contains existing stored objects.</li>
     * </ul>
     *
     * <p>Partial overlap (see {@code intersectsWithoutContainment}) with any already stored object
     * is not allowed. In such a case the method returns {@code false} without changing {@code this}.
     * If the new object fully contains the current root, it becomes the new root.
     * Otherwise, it is inserted at the deepest suitable position in the containment
     * hierarchy. This means {@code p} becomes a direct child of the smallest {@code Physical} element {@code e}
     * that fully contains {@code p}.
     * Existing direct children of {@code e} fully contained in the new object are moved below the new object.</p>
     *
     * @param p the object to insert; {@code p != null}
     * @return {@code true} if insertion succeeded, otherwise {@code false}
     */
    public boolean insert(Physical p) {

        if (contains(p)) {
            return false;
        }

        if (!root.isCompatibleWith(p)) {
            return false;
        }

        root = root.insert(p);
        size++;
        return true;
    }

    /**
     * Returns all direct child objects of the specified parent.
     *
     * @param parent the parent object; {@code parent != null}
     * @return an array containing all direct child objects,
     *         or {@code null} if the parent is not found
     */
    public Physical[] childrenOf(Physical parent) {

        PhysicalMultiTreeNode[] a = root.findNode(parent).getChildren();
        Physical[] result = new Physical[a.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = a[i].getValue();
        }
        return result;
    }

    /**
     * Removes all nodes from this tree.
     */
    public void clear() {

        root = PhysicalMultiTreeNodeEmpty.EMPTY;
        size = 0;
    }

    /**
     * Returns a map representing the current parent relation of this tree.
     *
     * <p>For every stored {@link Physical} object except the primary object,
     * the returned map contains an association
     *
     *   child -> parent
     *
     * where {@code parent} is the direct parent of {@code child} in this tree.</p>
     *
     * <p>The returned map is a snapshot of the current tree structure.
     * The contained {@link Physical} objects themselves are not copied,
     * but the map is independent of this tree. Later structural changes of this
     * tree do not affect the returned map.</p>
     *
     * <p>If this tree is empty or contains only one node, an empty map is returned.</p>
     *
     * @return a new map containing the parent relation of this tree
     */
    public PhysicalPhysicalMap parentMap() {

        PhysicalPhysicalMap result = new PhysicalPhysicalHashMap();
        constructMap(root, result);
        return result;
    }

    private void constructMap(PhysicalMultiTreeNode node,  PhysicalPhysicalMap map) {

        for (PhysicalMultiTreeNode current: node.getChildren()) {
            map.put(current.getValue(), node.getValue());
            constructMap(current, map);
        }
    }

    /**
     * Returns a string representation of this tree.
     *
     * <p>Each node is shown in its own line. Child nodes are indented
     * relative to their parent so that the recursive structure becomes visible.</p>
     *
     * <p>
     * Example:
     *
     * Nest@(390.0, 260.0), r=250.0
     *   Nest@(400.0, 270.0), r=200.0
     *     FoodSource@(350.0, 300.0), r=80.0
     *       FoodSource@(360.0, 335.0), r=7.0
     *       FoodSource@(370.0, 275.0), r=5.0
     *       FoodSource@(335.0, 285.0), r=10.0
     *     Nest@(500.0, 300.0), r=60.0
     * </p>
     *
     * @return a string representation of this tree.
     */
    @Override
    public String toString() {

        return root.toIndentedString("");
    }

    public PhysicalIterator iterator(){
        PhysicalTreeIterator iter = new PhysicalTreeIterator();
        if (root != null) {
            new PhysicalTreeIterator(root, iter);
        }
        return iter;
    }
}