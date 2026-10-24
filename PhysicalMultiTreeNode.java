/**
 * A node of a {@link PhysicalMultiTree}.
 *
 * <p>This interface represents (the root node of) a subtree in a multi-way tree storing
 * {@link Physical} objects. A non-empty subtree has one primary object
 * and may have direct child nodes.</p>
 *
 * <p>The tree represents a containment hierarchy of {@link Physical} objects.
 * Two stored objects may either be disjoint or one may fully contain the other.
 * Partial overlap is not allowed.</p>
 */
public interface PhysicalMultiTreeNode {

    /**
     * Returns the primary {@link Physical} object of this subtree.
     *
     * @return the primary object, or {@code null} if this subtree is empty
     */
    Physical getValue();

    /**
     * Returns the direct child nodes of this node.
     *
     * <p>The returned array contains all direct children in unspecified order.
     * For a leaf or empty node, an empty array is returned.</p>
     *
     * <p>The returned array must not contain {@code null} entries (the length of the
     * returned array corresponds to the number of direct child nodes).</p>
     *
     * @return an array containing all direct child nodes (possibly empty)
     */
    PhysicalMultiTreeNode[] getChildren();

    /**
     * Returns whether the subtree rooted at this node contains an object equal to
     * the specified object.
     *
     * <p>This method checks structural equality using {@code equals}.</p>
     *
     * @param p the object to search for; {@code p != null}
     * @return {@code true} if this subtree contains an equal object,
     *         otherwise {@code false}
     */
    boolean contains(Physical p);

    /**
     * Returns the subtree whose primary object is equal to the specified object.
     *
     * <p>If no such object exists, an empty subtree is returned.</p>
     *
     * @param p the object to search for; {@code p != null}
     * @return the matching subtree, or an empty node if no such object exists
     */
    PhysicalMultiTreeNode findNode(Physical p);

    /**
     * Returns whether the specified object is compatible with all objects stored
     * in the subtree rooted at this node.
     *
     * <p>Compatibility means that for every stored object, the two circles are
     * either disjoint or one fully contains the other. Partial overlap is not allowed.</p>
     *
     * @param p the object to test; {@code p != null}
     * @return {@code true} if the object is compatible with this subtree,
     *         otherwise {@code false}
     */
    boolean isCompatibleWith(Physical p);

    /**
     * Inserts the specified object into this subtree and returns the root
     * of the resulting subtree.
     *
     * <p>The insertion follows the containment hierarchy:</p>
     * <ul>
     *   <li>If {@code p} fully contains this subtree's primary object,
     *       {@code p} becomes the new root and this subtree becomes its child.</li>
     *   <li>If one of the direct children fully contains {@code p}, insertion
     *       continues recursively into that child.</li>
     *   <li>Otherwise, {@code p} is inserted as a new direct child.</li>
     *   <li>Existing children that are fully contained in {@code p} are moved
     *       below the new node.</li>
     * </ul>
     *
     * @param p the object to insert; {@code p != null}
     * @return the root of the resulting subtree
     */
    PhysicalMultiTreeNode insert(Physical p);

    /**
     * Returns a string representation of this subtree using the specified indentation.
     *
     * <p>Each node is represented in one line. Child nodes are indented
     * relative to their parent.</p>
     *
     * @param indent the indentation prefix for this node
     * @return a string representation of this subtree
     */
    String toIndentedString(String indent);
}