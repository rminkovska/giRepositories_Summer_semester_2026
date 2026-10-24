/**
 * Binary-search-tree implementation of {@link PhysicalSet}.
 *
 * <p>The elements are stored in a binary search tree ordered according to
 * a {@link PhysicalComparator}. Duplicate elements are not stored. Two
 * elements are considered duplicates iff they are equal according to
 * {@link Physical#equals(Object)}.</p>
 */
public class PhysicalTreeSet implements PhysicalSet // TODO: activate clause.
{

    // TODO: all variables and additional constructors and methods are private,
    //  unless a method overrides or implements an inherited public method.

    private PhysicalTreeSetNodeNonEmpty root;
    private PhysicalComparator cmp;
    private int size;

    /**
     * Creates an empty set ordered according to {@link XComparator}.
     */
    public PhysicalTreeSet() {

        //TODO: implement constructor.
        this.root = null;
        this.size = 0;
        this.cmp = new XComparator();
    }

    /**
     * Creates an empty set ordered according to the specified comparator.
     *
     * @param comparator the comparator defining the tree order;
     *                   {@code comparator != null}
     */
    public PhysicalTreeSet(PhysicalComparator comparator) {

        //TODO: implement constructor.
        this();
        this.cmp = comparator;
    }

    /**
     * Adds the specified element to this set if no equal element
     * is already present.
     *
     * @param p the element to add; {@code p != null}
     * @return {@code true} if the set changed as a result of the call,
     * otherwise {@code false}
     */
    public boolean add(Physical p) {
        if (root == null) {
            root = new PhysicalTreeSetNodeNonEmpty(p, this.cmp);
            size++;
            return true;
        } else {
            if (root.contains(p, this.cmp)) {
                return false;
            } else {
                root.insert(p, this.cmp);
                size++;
                return true;
            }
        }
    }

    /**
     * Returns whether this set contains an element equal to the
     * specified element.
     *
     * @param p the element to search for; {@code p != null}
     * @return {@code true} if an equal element exists in this set,
     * otherwise {@code false}
     */
    public boolean contains(Physical p) {
        if (root == null) {
            return false;
        }
        return root.contains(p, this.cmp);
    }

    /**
     * Returns the number of elements currently stored in this set.
     *
     * @return the number of stored elements
     */
    public int size() {
        return size;
    }

    /**
     * Returns whether this set contains no elements.
     *
     * @return {@code true} if this set is empty,
     * otherwise {@code false}
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all elements from this set.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The iterator traverses the elements in ascending order according
     * to the comparator of this set.</p>
     */
    // @Override
    public PhysicalIterator iterator() {

        //TODO: implement method.
        PhysicalTreeIterator iter = new PhysicalTreeIterator();
        if (root != null) {
            root.iter(iter, false);
        }
        return iter;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Two {@code PhysicalTreeSet} objects are considered equal iff</p>
     * <ul>
     *   <li>the specified object is also a {@code PhysicalTreeSet},</li>
     *   <li>both sets contain the same number of elements, and</li>
     *   <li>for every element contained in this set, an equal element is
     *       contained in the other set.</li>
     * </ul>
     *
     * <p>The iteration order and the concrete internal tree structure are
     * irrelevant.</p>
     *
     * @param o the object to compare with
     * @return {@code true} iff the specified object represents an equal set
     */
    @Override
    public boolean equals(Object o) {

        //TODO: implement method.
        if (this == o) return true;
        if (!(o instanceof PhysicalTreeSet)) {
            return false;
        }
        PhysicalTreeSet other = (PhysicalTreeSet) o;
        if (this.size != other.size()) return false;

        PhysicalIterator iter = this.iterator();

        while (iter.hasNext()) {
            if (!other.contains(iter.next())) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        //TODO: implement method.

        int hash = 0;
        PhysicalIterator iter = this.iterator();

        while (iter.hasNext()) {
            Physical current = iter.next(); // Call it exactly ONCE
            if (current != null) {
                hash += current.hashCode();
            }
        }
        return hash;
    }
}