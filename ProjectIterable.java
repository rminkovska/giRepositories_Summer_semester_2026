/**
 * Describes an object that can provide a project iterator.
 */
public interface ProjectIterable extends Iterable<ProjectNode> {

    /**
     * Returns an iterator over the elements of this object.
     *
     * @return an iterator
     */
    ProjectIterator iterator();
}