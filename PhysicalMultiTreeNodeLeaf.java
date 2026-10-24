/**
 * Leaf node of a {@link PhysicalMultiTree}.
 *
 * <p>A leaf node stores exactly one primary {@link Physical} object and
 * has no child nodes.</p>
 * <p>
 * TODO: change this file according to `angabe.md`.
 */
public class PhysicalMultiTreeNodeLeaf implements PhysicalMultiTreeNode {

    private final Physical value;

    /**
     * Creates a new leaf node with the specified physical object.
     *
     * @param value the stored object; {@code value != null}
     */
    public PhysicalMultiTreeNodeLeaf(Physical value) {

        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Physical getValue() {

        return value;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Since this node is a leaf, the returned array is empty.</p>
     */
    @Override
    public PhysicalMultiTreeNode[] getChildren() {

        return new PhysicalMultiTreeNode[0];
    }

    /**
     * {@inheritDoc}
     *
     * <p>This subtree consists only of the stored object.</p>
     */
    @Override
    public boolean contains(Physical p) {

        return value.equals(p);
    }

    /**
     * {@inheritDoc}
     *
     * <p>If the stored object equals {@code p}, this node is returned.
     * Otherwise an empty subtree is returned.</p>
     */
    @Override
    public PhysicalMultiTreeNode findNode(Physical p) {

        return value.equals(p) ? this : PhysicalMultiTreeNodeEmpty.EMPTY;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Compatibility is determined only with respect to the single stored object.</p>
     */
    @Override
    public boolean isCompatibleWith(Physical p) {

        return !value.intersectsWithoutContainment(p);
    }

    /**
     * {@inheritDoc}
     *
     * <p>If {@code p} fully contains the stored object, a new internal node
     * storing {@code p} becomes the root of the resulting subtree.</p>
     *
     * <p>Otherwise, a new internal node storing this node's object is created
     * and {@code p} becomes its direct child.</p>
     */
    @Override
    public PhysicalMultiTreeNode insert(Physical p) {

        if (p.fullyContains(value)) {
            return new PhysicalMultiTreeNodeInternal(
                    p,
                    new PhysicalMultiTreeNode[]{this}
            );
        }

        return new PhysicalMultiTreeNodeInternal(
                value,
                new PhysicalMultiTreeNode[]{
                        new PhysicalMultiTreeNodeLeaf(p)
                }
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toIndentedString(String indent) {

        return indent + value + "\n";
    }

    public Physical iter(PhysicalTreeIterator iterator, boolean next) {
        return this.value;
    }
}