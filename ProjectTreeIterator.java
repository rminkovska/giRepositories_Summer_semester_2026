import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Iterator traversing a project tree in pre-order.
 *
 * <p>The iterator state shall be represented using an object of the
 * predefined Java class {@code java.util.Stack<ProjectNode>}.</p>
 *
 * <p>In a pre-order traversal, a node is visited before all nodes
 * contained in its child subtrees. Child nodes are visited in the order
 * in which they are returned by {@link ProjectDirectory#getChildren()}.</p>
 *
 * <p>For example, the tree</p>
 *
 * <pre>
 * root
 * ├── src
 * │   ├── Main.java
 * │   └── World.java
 * └── README.md
 * </pre>
 *
 * <p>is traversed in the following order:</p>
 *
 * <pre>
 * root
 * src
 * Main.java
 * World.java
 * README.md
 * </pre>
 */
public class ProjectTreeIterator implements ProjectIterator {

    private Stack<ProjectNode> stack;
    private ProjectNode root;

    //TODO: implement iterator using the stack.
    public ProjectTreeIterator(ProjectNode root) {

        //TODO: implement constructor.
        this.stack = new Stack<>();
        this.root = root;
        if (root != null) {
            stack.push(this.root);
        }
    }

    @Override
    public boolean hasNext() {

        //TODO: implement method.
        return !stack.isEmpty();
    }

    @Override
    public ProjectNode next() {

        //TODO: implement method.
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        ProjectNode current = stack.pop();
        if (current == null)
        {
            throw new NoSuchElementException();
        }
        if (current.isDirectory())
        {
            List<ProjectNode> children = ((ProjectDirectory)current).getChildren();
            for (int i = children.size() - 1; i >= 0; i--) {
                stack.push(children.get(i));
            }
        }
        return current;
    }
}