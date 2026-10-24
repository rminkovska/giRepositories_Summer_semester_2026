/**
 * View of the subtree rooted at a file node.
 *
 * <p>The represented subtree consists only of the file itself.</p>
 *
 * <p>This class does not create an independent copy of the file node.
 * It provides a read-only view onto the file.</p>
 */
public class ProjectFileCollectionView implements ProjectNodeCollection //TODO: activate clause.
{
    //TODO: define class (incl. definition of public constructors).
    private ProjectFile n;

    public ProjectFileCollectionView (ProjectFile n){
        this.n = n;
    }

    @Override
    public int size() {
        return n.fileCount();
    }

    public boolean contains(ProjectNode node){
        if (node.equals(n)) return true;
        else return false;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public ProjectIterator iterator() {
        return new ProjectTreeIterator(n);
    }
}
