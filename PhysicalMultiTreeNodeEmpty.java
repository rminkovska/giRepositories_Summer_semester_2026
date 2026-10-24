/**
 * Empty node of a {@link PhysicalMultiTree}.
 *
 * <p>This class represents an empty subtree (no stored {@link Physical} object).
 * It is used as a neutral element to avoid {@code null} references.</p>
 *
 * <p>All operations are defined such that the empty node behaves like an
 * empty tree:</p>
 * <ul>
 *   <li>It contains no elements</li>
 *   <li>All queries return empty results</li>
 *   <li>Insertion creates a new non-empty node</li>
 * </ul>
 */
public class PhysicalMultiTreeNodeEmpty implements PhysicalMultiTreeNode {

    private static final PhysicalMultiTreeNodeEmpty EMPTY =
            new PhysicalMultiTreeNodeEmpty();

    /**
     * {@inheritDoc}
     *
     * <p>Since this subtree is empty, this method always returns {@code null}.</p>
     */
    @Override
    public Physical getValue() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Since this subtree is empty, the returned array is empty.</p>
     */
    @Override
    public PhysicalMultiTreeNode[] getChildren() {
        return new PhysicalMultiTreeNode[0];
    }

    /**
     * {@inheritDoc}
     *
     * <p>An empty subtree contains no elements.</p>
     */
    @Override
    public boolean contains(Physical p) {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * <p>If no object exists, this empty subtree is returned.</p>
     */
    @Override
    public PhysicalMultiTreeNode findNode(Physical p) {
        return EMPTY;
    }

    /**
     * {@inheritDoc}
     *
     * <p>An empty subtree is always compatible.</p>
     */
    @Override
    public boolean isCompatibleWith(Physical p) {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Insertion into an empty subtree creates a new leaf node.</p>
     */
    @Override
    public PhysicalMultiTreeNode insert(Physical p) {
        return new PhysicalMultiTreeNodeLeaf(p);
    }

    /**
     * {@inheritDoc}
     *
     * <p>For an empty subtree, the string {@code "(empty)"} is returned.</p>
     */
    @Override
    public String toIndentedString(String indent) {
        return "(empty)";
    }
}