/**
 * Common interface of all nodes in a project tree.
 *
 * <p>A project tree consists of directories and files. Every node has
 * a name and represents a subtree whose size and number of contained
 * files can be determined.</p>
 */
public interface ProjectNode {

    /**
     * Returns the name of this node.
     *
     * @return the name
     */
    String getName();

    /**
     * Returns the total number of bytes contained in the subtree rooted
     * at this node.
     *
     * <p>For a file, this is the size of the file itself.
     * For a directory, this is the sum of the sizes of all files contained
     * in the corresponding subtree. An empty directory returns {@code 0}.</p>
     *
     * @return the total number of bytes contained in the subtree
     */
    long totalBytes();

    /**
     * Returns the number of file nodes contained in the subtree rooted
     * at this node.
     *
     * <p>A file node therefore returns {@code 1}. An empty directory returns {@code 0}.</p>
     *
     * @return the number of file nodes
     */
    int fileCount();

    /**
     * Returns whether this node represents a directory.
     *
     * @return {@code true} iff this node is a directory
     */
    boolean isDirectory();

    /**
     * Returns a view of the subtree rooted at this node.
     *
     * <p>The returned collection contains this node and all nodes contained
     * in its subtree. Iteration order is pre-order.</p>
     *
     * <p>The returned object is a view and not an independent copy.</p>
     *
     * @return a view of the subtree rooted at this node
     */
    ProjectNodeCollection collectionView();
}