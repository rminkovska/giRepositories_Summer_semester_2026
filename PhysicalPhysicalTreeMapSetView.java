/**
 * A set view backed by a {@link PhysicalPhysicalTreeMap}.
 * <p>
 * The elements of this set are the keys of the underlying tree map.
 * The set directly reflects the current state of the backing map and does
 * not maintain its own storage.
 * </p>
 *
 * <p>
 * Iterators of this set view iterate over all keys of the underlying tree map
 * in its natural tree order. Iterators of this set view may throw {@link ConcurrentModificationException}
 * if the backing map is structurally modified during iteration.
 * </p>
 *
 * <p>
 * The implementation uses a tree structure together with a
 * {@link PhysicalComparator} for element ordering and lookup operations.
 * </p>
 */
public class PhysicalPhysicalTreeMapSetView implements PhysicalSet //TODO: acivate clause.
{

    // TODO: all variables and additional constructors and methods are private,
    //  unless a method overrides or implements an inherited public method.

    private PhysicalPhysicalMap map;
    private PhysicalPhysicalTreeMapNode[] rootBox;
    private int[] modCounterBox;

    /**
     * Creates a new set view backed by the specified tree map.
     *
     * @param map           the backing tree map; {@code map != null}
     * @param rootBox       a shared mutable reference to the root node of the
     *                      backing tree structure; {@code rootBox != null}
     *                      and {@code rootBox.length >= 1}
     * @param modCounterBox a shared mutable modification counter used to
     *                      detect structural changes during iteration;
     *                      {@code modCounterBox != null}
     *                      and {@code modCounterBox.length >= 1}
     */
    public PhysicalPhysicalTreeMapSetView(
            PhysicalPhysicalTreeMap map,
            PhysicalPhysicalTreeMapNode[] rootBox,
            int[] modCounterBox) {

        //TODO: implement constructor.
        this.map = map;
        this.rootBox = rootBox;
        this.modCounterBox = modCounterBox;
    }

    /**
     * {@inheritDoc}
     *
     * <p>If {@code p} is not already a key of the backing map, it is inserted
     * with associated value {@code null}.</p>
     *
     * <p>If an equal key already exists, the backing map remains unchanged.</p>
     *
     * @param p the element to add; {@code p != null}
     * @return {@code true} if the backing map changed as a result of the call,
     * otherwise {@code false}
     */
    //@Override
    public boolean add(Physical p) {

        //TODO: implement method.
        if (p == null) return false;
        if (map.containsKey(p)) return false;
        else {
            map.put(p, null);
            return true;
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
        if (p == null) return false;
        else {
            return map.containsKey(p);
        }
    }

    /**
     * Returns the number of elements currently stored in this set.
     *
     * @return the number of stored elements
     */
    public int size() {
        return map.size();
    }

    /**
     * Returns whether this set contains no elements.
     *
     * @return {@code true} if this set is empty,
     * otherwise {@code false}
     */
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * Removes all elements from this set.
     */
    public void clear() {
        if (map.isEmpty()) return;
        map.clear();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns an iterator over all elements stored in this set.
     *
     * <p>If this set is empty, the returned iterator has no elements.</p>
     */
    @Override
    public PhysicalIterator iterator() {
        return new PhysicalPhysicalTreeMapSetViewIterator(rootBox[0], modCounterBox);
    }
}