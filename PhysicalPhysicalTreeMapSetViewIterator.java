/**
 * Iterator over the keys of a {@link PhysicalPhysicalTreeMapSetView}.
 *
 * <p>This iterator traverses the underlying binary search tree in
 * in-order, producing the keys in ascending order according to the
 * comparator used by the backing tree map.</p>
 *
 * <p>The iterator uses an explicit {@link java.util.Stack} to store the
 * traversal state. No independent copy of all keys is created.</p>
 *
 * <p>The iterator is fail-fast. If the backing map is structurally modified
 * after this iterator has been created, {@code #next()} throws a
 * {@link ConcurrentModificationException}.</p>
 */
public class PhysicalPhysicalTreeMapSetViewIterator implements PhysicalIterator //TODO: acivate clause.
{

    // TODO: all variables and additional constructors and methods are private,
    //  unless a method overrides or implements an inherited public method.

    /**
     * Creates a new iterator over the specified tree.
     *
     * <p>The iterator starts before the first key according to in-order traversal.
     * If {@code root} is an empty node, the iterator has no elements.</p>
     *
     * @param root the root node of the tree to traverse; {@code root != null}
     * @param modCounterBox shared modification counter of the backing map;
     * {@code modCounterBox != null}
     * and {@code modCounterBox.length >= 1}
     */
    PhysicalPhysicalTreeMapNode root;
    int[] modCounterBox;
    int expectedModCount;
    private final java.util.Stack<PhysicalPhysicalTreeMapNode> stack;

    public PhysicalPhysicalTreeMapSetViewIterator(
            PhysicalPhysicalTreeMapNode root,
            int[] modCounterBox) {

        //TODO: implement constructor.
        this.root = root;
        this.modCounterBox = modCounterBox;
        this.expectedModCount = modCounterBox[0];
        this.stack = new java.util.Stack<>();


        if (root == null) return;
        else{
            pushLeft(root);
        }

    }

    private void pushLeft(PhysicalPhysicalTreeMapNode n){
        while (n.getClass() != PhysicalPhysicalTreeMapNodeEmpty.class) {
            stack.push(n);
            n = n.getLeft();
        }
    }

    /**
     * Returns whether another physical object is available.
     *
     * @return {@code true} if a subsequent call to {@link #next()} can return
     * another object, otherwise {@code false}
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * Returns the next physical object of the traversal.
     *
     * <p>If no further object is available, this method returns {@code null}.</p>
     *
     * @return the next physical object, or {@code null} if no further object exists
     * @throws ConcurrentModificationException if the underlying collection
     *                                         was structurally modified during iteration
     */
    public Physical next() {
        if (expectedModCount != modCounterBox[0]) {
            int diff = modCounterBox[0] - expectedModCount;
            throw new ConcurrentModificationException(diff);
        }
        PhysicalPhysicalTreeMapNode node = stack.pop();
        if(node.getClass() != PhysicalPhysicalTreeMapNodeEmpty.class)
        {
            pushLeft(node.getRight());
        }
        return node.getKey();
    }
}