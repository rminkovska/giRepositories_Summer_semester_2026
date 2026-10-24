import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Composite node representing a directory in a project tree.
 */
public class ProjectDirectory implements ProjectNode //TODO: activate clause.
{
    String name;
    List<ProjectNode> children;

    //TODO: define missing parts of this class (variables and methods).
    /**
     * Creates an empty directory node with the specified name.
     *
     * <p>The newly created directory contains no child nodes.</p>
     *
     * @param name the directory name; {@code name != null && !name.isEmpty()}
     */
    public ProjectDirectory(String name) {

        //TODO: implement constructor.
        this.name = name;
        children =  new ArrayList<>();
    }

    /**
     * Adds the specified child node to this directory.
     *
     * @param node the child node;
     *             {@code node != null}
     */
    public void add(ProjectNode node) {

        //TODO: implement method.
        if (node == null) throw new NullPointerException();
        children.add(node);
    }

    /**
     * {@inheritDoc}
     */
    //@Override
    public String getName() {

        //TODO: implement method.
        if (name == null) throw new NullPointerException();
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    //@Override
    public long totalBytes() {

        //TODO: implement method.
        if(children.isEmpty()) return 0;
        long count =0;
        for (ProjectNode node : children) {
            count += node.totalBytes();
        }
        return count;
    }

    @Override
    public int fileCount() {
        if (children.isEmpty()) return 0;
        else return children.size()+1;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public ProjectNodeCollection collectionView() {
        return new ProjectDirectoryCollectionView(this);
    }

    /**
     * Returns the direct children of this directory.
     *
     * <p>The returned list is a new list object. Structural modifications
     * to the returned list do not affect this directory.</p>
     *
     * <p>The order of the elements of the list is ascending according to {@link ProjectNodeSizeComparator}.</p>
     *
     * <p>The contained project nodes themselves are not copied.</p>
     *
     * @return a copy of the direct children of this directory
     */
    public List<ProjectNode> getChildren() {

        //TODO: implement method.
        List<ProjectNode> copy = new ArrayList<>(children);
        copy.sort(new ProjectNodeSizeComparator());
        return copy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        appendTree(builder, "", true);
        return builder.toString();
    }

    private void appendTree(StringBuilder builder, String prefix, boolean isLast) {

        builder.append(prefix);

        if (!prefix.isEmpty()) {
            builder.append(isLast ? "└── " : "├── ");
        }

        builder.append(getName());
        builder.append(", ");
        builder.append(totalBytes());
        builder.append(System.lineSeparator());

        List<ProjectNode> children = getChildren();

        for (int i = 0; i < children.size(); i++) {

            ProjectNode child = children.get(i);

            String childPrefix =
                    prefix + (isLast ? "    " : "│   ");

            boolean childIsLast =
                    i == children.size() - 1;

            if (child.isDirectory()) {

                ((ProjectDirectory) child).appendTree(
                        builder,
                        childPrefix,
                        childIsLast
                );

            } else {

                builder.append(childPrefix);
                builder.append(childIsLast ? "└── " : "├── ");
                builder.append(child);
                builder.append(System.lineSeparator());
            }
        }
    }
}