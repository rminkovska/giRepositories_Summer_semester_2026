/**
 * Set view of the keys stored in a {@link PhysicalPhysicalHashMap}.
 *
 * <p>This set is backed by the underlying map. Changes to the map are
 * reflected in this set view, and changes performed through this set view
 * affect the underlying map.</p>
 *
 * <p>Adding an element to this set inserts the element as a key into the
 * underlying map and associates it with {@code null}.</p>
 *
 * <p>The iteration order is determined by the internal hash table and is
 * not specified. Iterators of this set view may throw
 * {@link ConcurrentModificationException} if the backing map is structurally
 * modified during iteration.</p>
 */
public class PhysicalPhysicalHashMapSetView implements PhysicalSet //TODO: acivate clause.
{
    // TODO: all variables and additional constructors and methods are private,
    //  unless a method overrides or implements an inherited public method.

    /**
     * Creates a new key-set view backed by the specified map.
     *
     * @param map           the backing map; {@code map != null}
     * @param keysBox       a shared mutable reference to the internal key array
     * of the backing map; {@code keysBox != null}
     * and {@code keysBox.length >= 1}
     * @param modCounterBox a shared mutable modification counter used to
     * detect structural changes during iteration;
     * {@code modCounterBox != null}
     * and {@code modCounterBox.length >= 1}
     */

    private PhysicalPhysicalHashMap map;
    private Physical[][] keysBox;
    private int[] modCounterBox;

    public PhysicalPhysicalHashMapSetView(
            PhysicalPhysicalHashMap map,
            Physical[][] keysBox,
            int[] modCounterBox) {

        //TODO: implement constructor.
        this.map = map;
        this.keysBox = keysBox;
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
        if (map.containsKey(p)) {
            return false;
        }
        map.put(p, null);
        return true;
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
        if (map.containsKey(p)) return true;
        else return false;
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
        return new PhysicalPhysicalHashMapSetViewIterator(keysBox[0], modCounterBox);
    }
}