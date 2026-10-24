/**
 * Non-empty subtree of a {@link PhysicalTreeSet}.
 *
 * <p>This class represents a node of a binary search tree storing
 * {@link Physical} objects.</p>
 */
public class PhysicalTreeSetNodeNonEmpty implements PhysicalTreeSetNode // TODO: activate clause.
{
    // TODO: all object variables and additional constructors and methods are private,
    //  unless a method overrides or implements an inherited public method.
    private final Physical value;
    private PhysicalComparator cmp;
    private PhysicalTreeSetNode left;
    private PhysicalTreeSetNode right;
    private int size;

    /**
     * Creates a new node storing the specified value.
     *
     * @param value      the stored value; {@code value != null}
     * @param comparator the comparator defining the tree order;
     *                   {@code comparator != null}
     */
    public PhysicalTreeSetNodeNonEmpty(Physical value, PhysicalComparator comparator) {
        // TODO: implement constructor.
        this.value = value;
        this.cmp = comparator;
        this.size = 0;

    }

    public Physical getValue() {
        return this.value;
    }

    public PhysicalTreeSetNode getLeft() {
        return left;
    }

    public PhysicalTreeSetNode getRight() {
        return right;
    }

    public boolean contains(Physical p, PhysicalComparator comparator) {

        if (this.value.equals(p)) {
            return true;
        }
        int cmp = comparator.compare(p, this.value);

        if (cmp < 0) {
            return left != null && left.contains(p, comparator);
        } else {
            return right != null && right.contains(p, comparator);
        }
    }

    public PhysicalTreeSetNode insert(Physical p, PhysicalComparator comparator) {
        if (this.value.equals(p)) {
            return this;
        }
        int cmp = comparator.compare(p, this.value);
        if (cmp < 0) {
            if (left == null) {
                left = new PhysicalTreeSetNodeNonEmpty(p, this.cmp);
                size++;
                return this;
            } else {
                return left.insert(p, comparator);
            }
        } else {
            if (right == null) {
                right = new PhysicalTreeSetNodeNonEmpty(p, this.cmp);
                size++;
                return this;
            } else {
                return right.insert(p, comparator);
            }
        }
    }

    public int size() {
        return size;
    }

    public Physical iter(PhysicalTreeIterator iterator, boolean next) {
        if (!next){
            PhysicalTreeSetNodeNonEmpty n = this;
            while (n != null){
                new PhysicalTreeIterator(n, iterator);
                n = (PhysicalTreeSetNodeNonEmpty) n.getRight();
            }
            return null;
        }
        else{
            PhysicalTreeSetNodeNonEmpty n = (PhysicalTreeSetNodeNonEmpty) this.getLeft();
            while (n != null){
                new PhysicalTreeIterator(n, iterator);
                n = (PhysicalTreeSetNodeNonEmpty) n.getRight();
            }
        }
        return this.value;
    }
}