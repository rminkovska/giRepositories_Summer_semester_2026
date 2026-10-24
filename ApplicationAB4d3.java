import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Simple test application for AB4.3.
 */
public class ApplicationAB4d3 {

    // TODO: activate block:
    public static void main(String[] args) {

        testProjectFile();
        testProjectDirectory();
        testSubtreeView();
        testIterator();
        testSave();

        System.out.println();
        System.out.println("ALL AB4.3 TESTS COMPLETED");
    }

    private static void testProjectFile() {

        ProjectFile file = new ProjectFile("README.md", 42);

        check("ProjectFile.totalBytes()", 42L, file.totalBytes());
        check("ProjectFile.fileCount()", 1, file.fileCount());
        check("ProjectFile.subtreeView().size()", 1, file.collectionView().size());
    }

    private static void testProjectDirectory() {

        ProjectDirectory root = createSampleTree();

        check("ProjectDirectory.totalBytes()",45L,root.totalBytes());
        check("ProjectDirectory.fileCount()",3,root.fileCount());
        check("ProjectDirectory.childCount()", 2, root.getChildren().size());
    }

    private static void testSubtreeView() {

        ProjectDirectory root =
                createSampleTree();

        ProjectNodeCollection subtree = root.collectionView();

        check("subtree size", 5, subtree.size());
        check("subtree contains root", true, subtree.contains(root));
        check("subtree contains unrelated file", false,
                subtree.contains(new ProjectFile("X", 1)));

        ProjectFile test = new ProjectFile("TEST", 1);
        ((ProjectDirectory) root.getChildren().get(1)).add(test); // according to order get(1) must be "src" directory
        check("subtree contains new file", true,
                subtree.contains(test));
    }

    private static void testIterator() {

        ProjectDirectory root = createSampleTree();

        ProjectIterator it = root.collectionView().iterator();

        StringBuilder result = new StringBuilder();

        while (it.hasNext()) {
            result.append(it.next().getName());
            result.append(" ");
        }

        check("preorder iterator", "root README.md src Main.java World.java ", result.toString());
    }

    private static void testSave() {

        try {
            ProjectDirectory root = createSampleTree();
            ProjectIO.save(root, "ab4d3-test.txt");
            String actual = Files.readString(Path.of("ab4d3-test.txt"));
            String expected =
                    """
                    dir;root
                    file;root/README.md;15
                    dir;root/src
                    file;root/src/Main.java;10
                    file;root/src/World.java;20
                    """;
            check("save (order may be different)", expected, actual);

        } catch (IOException e) {
            fail("save", "no IOException expected", e.getMessage());
        }
    }

    private static ProjectDirectory createSampleTree() {

        ProjectDirectory root = new ProjectDirectory("root");
        ProjectDirectory src = new ProjectDirectory("src");

        src.add(new ProjectFile("Main.java", 10));
        src.add(new ProjectFile("World.java", 20));
        root.add(src);
        root.add(new ProjectFile("README.md", 15));
        return root;
    }

    private static void check(String testName, Object expected, Object actual) {

        if (java.util.Objects.equals(expected, actual)) {
            ok(testName);
        } else {
            fail(testName, expected, actual);
        }
    }

    private static void ok(String testName) {

        System.out.println("[OK]   " + testName);
    }

    private static void fail(String testName, Object expected, Object actual) {

        System.out.println("[FAIL] " + testName + " -> Expected: " + expected + ", Actual: " + actual);
    }
    //TODO: end of block to activate */
}