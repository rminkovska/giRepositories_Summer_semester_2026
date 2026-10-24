/**
 * Read-only collection of project nodes.
 *
 * <p>A {@code ProjectNodeCollection} represents a collection of
 * {@link ProjectNode} objects. The collection may either be a
 * view onto another data structure or an independent copy.</p>
 */
public interface ProjectNodeCollection
        extends ProjectIterable {

    /**
     * Returns the number of contained project nodes.
     *
     * @return the number of nodes
     */
    int size();

    /**
     * Returns whether a node equal to the specified node is contained.
     *
     * @param node the node to test; {@code node != null}
     * @return {@code true} iff a node equal to {@code node} is contained
     */
    boolean contains(ProjectNode node);

    /**
     * Returns whether this collection is empty.
     *
     * @return {@code true} iff this collection is empty
     */
    default boolean isEmpty() {
        return size() == 0;
    }
}