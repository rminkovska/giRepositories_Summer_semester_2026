import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator over project nodes.
 */
public interface ProjectIterator extends Iterator<ProjectNode> {

    /**
     * Returns whether another project node is available.
     *
     * @return {@code true} iff another project node is available
     */
    boolean hasNext();

    /**
     * Returns the next project node.
     *
     * @return the next project node
     * @throws NoSuchElementException if no further project node is available
     */
    ProjectNode next();
}