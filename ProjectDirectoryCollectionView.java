/**
 * View of the subtree rooted at a directory node.
 *
 * <p>The represented subtree contains the directory itself and all nodes
 * contained recursively in its child subtrees.</p>
 *
 * <p>This class does not create an independent copy of the subtree.
 * It works directly on the directory and its children.</p>
 *
 * <p>Iteration order is pre-order: first the directory itself is visited,
 * then its children from left to right.</p>
 */
public class ProjectDirectoryCollectionView implements ProjectNodeCollection //TODO: activate clause.
{
    //TODO: define class (incl. definition of public constructors).

    private ProjectNode root;

    public ProjectDirectoryCollectionView (ProjectNode root)
    {
        this.root = root;
    }

    @Override
    public int size() {
        int count = 0;
        ProjectIterator iter = iterator();
        while (iter.hasNext()) {
            iter.next();
            count++;
        }
        return count;
    }

    @Override
    public boolean contains(ProjectNode node) {
        if (node == null) return false;
        if (this.root.equals(node)) return true;

        ProjectIterator iter = iterator();
        while(iter.hasNext()){
            if(node.equals(iter.next())){
                return true;
            }
        }
        return false;
    }

    @Override
    public ProjectIterator iterator() {
        return new ProjectTreeIterator(root);
    }

}
