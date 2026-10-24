import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Reads project trees from the file system and provides static methods
 * for storing and loading project trees.
 *
 * <p>A {@code ProjectIO} object is associated with a root directory in the
 * file system. The method {@link #fromFileSystem()} creates a project tree
 * representing the complete subtree rooted at this directory.</p>
 *
 * <p>The static method {@link #save(ProjectNode, String)} operates
 * on independent text files and does not depend on the associated root
 * directory.</p>
 */
public class ProjectIO {

    private Path rootDirectory;

    /**
     * Creates a new {@code ProjectIO} object.
     *
     * @param rootDirectory the root directory of the file-system subtree;
     *                      {@code rootDirectory != null}
     */
    public ProjectIO(Path rootDirectory) {

        this.rootDirectory = rootDirectory;
    }

    /**
     * Returns the root directory associated with this object.
     *
     * @return the root directory
     */
    public Path getRootDirectory() {

        return rootDirectory;
    }

    /**
     * Creates a project tree from the associated file-system subtree.
     *
     * @return a project tree representing the associated file-system subtree
     */
    public ProjectNode fromFileSystem() {

        return fromFileSystem(rootDirectory.toFile());
    }

    /**
     * Writes the specified project tree to the specified file.
     *
     * <p>If the file already exists, its previous content is replaced.</p>
     *
     * @param root the root node to write; {@code root != null}
     * @param fileName the output file name; {@code fileName != null}
     * @throws IOException if the file cannot be written
     */
    public static void save(ProjectNode root, String fileName)
            throws IOException {
        //TODO: implement method.
        if (root == null || fileName == null) {
            throw new IllegalArgumentException("Invalid argument");
        }
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(fileName))) {
            writeNodeRecursively(root, "", writer);
        }

    }

    private static void writeNodeRecursively(ProjectNode node, String parentPath, BufferedWriter writer) throws IOException {
        String currentPath;
        if(parentPath.isEmpty()) {
            currentPath = node.getName();
        }
        else {
            currentPath = parentPath + "/" + node.getName();
        }

        if (node.isDirectory()) {
            writer.write("dir;" + currentPath);
            writer.write("\n");

            ProjectDirectory dir = (ProjectDirectory) node;
            for (ProjectNode child : dir.getChildren()) {
                writeNodeRecursively(child, currentPath, writer);
            }
        }
        else {
            writer.write("file;" + currentPath + ";" + node.totalBytes());
            writer.write("\n");
        }

    }

    /**
     * Creates a project node from the specified file-system entry.
     *
     * @param file the file-system entry;
     *             {@code file != null && file.exists()}
     * @return a project node representing the specified file-system entry
     */
    private ProjectNode fromFileSystem(File file) {

        // TODO: activate implementation:
        if (file.isDirectory()) {
            ProjectDirectory result = new ProjectDirectory(file.getName());

            File[] children = file.listFiles();

            if (children != null) {
                for (File child : children) {
                    result.add(fromFileSystem(child));
                }
            }

            return result;
        }

        return new ProjectFile(file.getName(), file.length());
        // */
    }
}