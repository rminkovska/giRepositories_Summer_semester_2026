/**
 * Unordered collection of distinct {@link Physical} objects.
 *
 * <p>A set contains no duplicate elements. Two elements are considered
 * duplicates iff they are equal according to
 * {@link Physical#equals(Object)}.</p>
 *
 * <p>If an element equal to an already stored element is added again,
 * the set remains unchanged.</p>
 *
 * <p>The iteration order is defined by the implementing class.</p>
 */
public interface PhysicalSet extends PhysicalIterable {

    /**
     * Adds the specified element to this set if no equal element
     * is already present.
     *
     * @param p the element to add; {@code p != null}
     * @return {@code true} if the set changed as a result of the call,
     *         otherwise {@code false}
     */
    boolean add(Physical p);

    /**
     * Returns whether this set contains an element equal to the
     * specified element.
     *
     * @param p the element to search for; {@code p != null}
     * @return {@code true} if an equal element exists in this set,
     *         otherwise {@code false}
     */
    boolean contains(Physical p);

    /**
     * Returns the number of elements currently stored in this set.
     *
     * @return the number of stored elements
     */
    int size();

    /**
     * Returns whether this set contains no elements.
     *
     * @return {@code true} if this set is empty,
     *         otherwise {@code false}
     */
    boolean isEmpty();

    /**
     * Removes all elements from this set.
     */
    void clear();

    /**
     * {@inheritDoc}
     *
     * Returns an iterator over all elements stored in this set.
     *
     * <p>If this set is empty, the returned iterator has no elements.</p>
     */
    @Override
    PhysicalIterator iterator();
}