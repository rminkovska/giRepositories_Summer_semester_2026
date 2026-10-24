/**
 * Leaf node representing a file in a project tree.
 *
 * <p>A {@code ProjectFile} has no children. The subtree rooted at a file
 * therefore consists only of the file itself.</p>
 */
public class ProjectFile implements ProjectNode //TODO: activate clause.
{

    //TODO: define missing parts of this class (variables and methods).
    String name;
    long totalBytes;
    /**
     * Creates a new file node.
     *
     * @param name the file name;
     *             {@code name != null && !name.isEmpty()}
     * @param numberOfBytes the file numberOfBytes in bytes;
     *             {@code numberOfBytes >= 0}
     */
    public ProjectFile(String name, long numberOfBytes) {

        //TODO: implement constructor.
        this.name = name;
        this.totalBytes = numberOfBytes;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long totalBytes() {
        return this.totalBytes;
    }

    @Override
    public int fileCount() {
        return 1;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public ProjectNodeCollection collectionView() {
        return new ProjectFileCollectionView(this);
    }
}