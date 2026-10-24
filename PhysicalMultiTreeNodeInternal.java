/**
 * Internal node of a {@link PhysicalMultiTree}.
 *
 * <p>Each internal node stores exactly one primary {@link Physical} object and
 * one or more child nodes.</p>
 * <p>
 * TODO: change this file according to `angabe.md`.
 */
public class PhysicalMultiTreeNodeInternal implements PhysicalMultiTreeNode {

    private final Physical value;
    private PhysicalMultiTreeNode[] children;
    private int childCount;

    /**
     * Creates a new internal node with the specified physical object and
     * the specified direct children.
     *
     * @param value    the stored object; {@code value != null}
     * @param children the direct child nodes;
     *                 {@code children != null && children.length > 0} and no entry is {@code null}
     */
    public PhysicalMultiTreeNodeInternal(Physical value, PhysicalMultiTreeNode[] children) {

        this.value = value;
        this.children = new PhysicalMultiTreeNode[Math.max(4, children.length)];
        this.childCount = children.length;

        for (int i = 0; i < childCount; i++) {
            this.children[i] = children[i];
        }
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
     * <p>The returned array is a newly created array containing the direct child
     * subtrees of this internal node.</p>
     */
    @Override
    public PhysicalMultiTreeNode[] getChildren() {

        PhysicalMultiTreeNode[] result = new PhysicalMultiTreeNode[childCount];

        for (int i = 0; i < childCount; i++) {
            result[i] = children[i];
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * <p>This method first checks the object stored in this node and then
     * recursively searches all child subtrees.</p>
     */
    @Override
    public boolean contains(Physical p) {

        if (value.equals(p)) {
            return true;
        }

        for (int i = 0; i < childCount; i++) {
            if (children[i].contains(p)) {
                return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * <p>If no matching object is found in this subtree, an empty subtree (empty node) is
     * returned.</p>
     */
    @Override
    public PhysicalMultiTreeNode findNode(Physical p) {

        if (value.equals(p)) {
            return this;
        }

        for (int i = 0; i < childCount; i++) {
            PhysicalMultiTreeNode found = children[i].findNode(p);
            if (found.getValue() != null) {
                return found;
            }
        }

        return PhysicalMultiTreeNodeEmpty.EMPTY;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The specified object must be compatible with the object stored in this
     * node and with all child subtrees.</p>
     */
    @Override
    public boolean isCompatibleWith(Physical p) {

        if (value.intersectsWithoutContainment(p)) {
            return false;
        }

        for (int i = 0; i < childCount; i++) {
            if (!children[i].isCompatibleWith(p)) {
                return false;
            }
        }

        return true;
    }

    /**
     * {@inheritDoc}
     *
     * <p>If {@code p} fully contains this node's stored object, a new internal
     * node storing {@code p} becomes the root of the resulting subtree.</p>
     *
     * <p>Otherwise, insertion continues recursively into the smallest direct
     * child subtree that fully contains {@code p}, if such a child exists.
     * If no such child exists, {@code p} is inserted as a new direct child.
     * Existing direct children fully contained in {@code p} are moved below
     * the new node.</p>
     */
    @Override
    public PhysicalMultiTreeNode insert(Physical p) {

        if (p.fullyContains(value)) {
            return new PhysicalMultiTreeNodeInternal(
                    p,
                    new PhysicalMultiTreeNode[]{this}
            );
        }

        int bestChildIndex = -1;
        double bestRadius = Double.POSITIVE_INFINITY;

        for (int i = 0; i < childCount; i++) {
            if (children[i].getValue().fullyContains(p)) {
                double r = children[i].getValue().getRadius();
                if (r < bestRadius) {
                    bestRadius = r;
                    bestChildIndex = i;
                }
            }
        }

        if (bestChildIndex != -1) {
            children[bestChildIndex] = children[bestChildIndex].insert(p);
            return this;
        }

        PhysicalMultiTreeNode[] movedChildren = new PhysicalMultiTreeNode[childCount];
        int movedCount = 0;

        int i = 0;
        while (i < childCount) {
            if (p.fullyContains(children[i].getValue())) {
                movedChildren[movedCount] = children[i];
                movedCount++;
                removeChildAt(i);
            } else {
                i++;
            }
        }

        PhysicalMultiTreeNode newNode;

        if (movedCount == 0) {
            newNode = new PhysicalMultiTreeNodeLeaf(p);
        } else {
            PhysicalMultiTreeNode[] actualMovedChildren =
                    new PhysicalMultiTreeNode[movedCount];

            for (int j = 0; j < movedCount; j++) {
                actualMovedChildren[j] = movedChildren[j];
            }

            newNode = new PhysicalMultiTreeNodeInternal(p, actualMovedChildren);
        }

        addDirectChild(newNode);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toIndentedString(String indent) {

        String result = indent;

        result += value + "\n";

        for (int i = 0; i < childCount; i++) {
            result += children[i].toIndentedString(indent + "  ");
        }

        return result;
    }

    /**
     * Adds the specified node as a direct child of this node.
     *
     * @param child the child node; {@code child != null}
     */
    private void addDirectChild(PhysicalMultiTreeNode child) {

        ensureChildCapacity();
        children[childCount] = child;
        childCount++;
    }

    /**
     * Removes the direct child at the specified index.
     *
     * @param index the child index; {@code 0 <= index < childCount}
     */
    private void removeChildAt(int index) {

        for (int i = index; i < childCount - 1; i++) {
            children[i] = children[i + 1];
        }

        children[childCount - 1] = null;
        childCount--;
    }

    /**
     * Enlarges the internal child array if necessary.
     */
    private void ensureChildCapacity() {

        if (childCount < children.length) {
            return;
        }

        PhysicalMultiTreeNode[] newChildren =
                new PhysicalMultiTreeNode[children.length * 2];

        for (int i = 0; i < childCount; i++) {
            newChildren[i] = children[i];
        }

        children = newChildren;
    }

    public Physical iter(PhysicalTreeIterator iterator, boolean next) {

        PhysicalMultiTreeNode[] children = this.getChildren();
        if (children != null) {
            for (int i = children.length - 1; i >= 0; i--) {
                if (children[i] != null) {
                    new PhysicalTreeIterator(children[i], iterator);
                }
            }
        }
        return this.value;

    }
}