import java.util.Comparator;

/**
 * Comparator for project nodes based on their total number of bytes.
 *
 * <p>Project nodes are ordered by the value returned by
 * {@link ProjectNode#totalBytes()}.</p>
 *
 * <p>If two project nodes have the same total number of bytes, their names
 * are compared lexicographically in order to obtain a deterministic order.</p>
 */
public class ProjectNodeSizeComparator implements Comparator<ProjectNode> {

    /**
     * Compares two project nodes by their total number of bytes.
     *
     * @param first the first project node; {@code first != null}
     * @param second the second project node; {@code second != null}
     * @return a negative value if {@code first} is smaller than {@code second},
     *         {@code 0} if both are considered equal by this comparator,
     *         or a positive value otherwise
     */
    @Override
    public int compare(ProjectNode first, ProjectNode second) {

        int result = Long.compare(
                first.totalBytes(),
                second.totalBytes()
        );

        if (result != 0) {
            return result;
        }

        return first.getName().compareTo(second.getName());
    }
}