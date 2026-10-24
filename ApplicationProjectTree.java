import java.nio.file.Path;

/**
 * Reads the current working directory and prints the corresponding
 * project tree to the console.
 */
public class ApplicationProjectTree {

    /*
    AB4.3, 69982
        ├── ab4d3-test.txt, 100
        ├── AB4.3.iml, 423
        ├── .idea, 7223
        │   ├── vcs.xml, 244
        │   ├── modules.xml, 250
        │   ├── misc.xml, 271
        │   └── workspace.xml, 6458
        ├── angabe, 8253
        │   └── angabe.md, 8253
        ├── out, 23725
        │   └── production, 23725
        │       └── AB4.3, 23725
        │           ├── ProjectIterable.class, 152
        │           ├── ProjectIterator.class, 168
        │           ├── ProjectNode.class, 278
        │           ├── ProjectFormatException.class, 358
        │           ├── ProjectNodeCollection.class, 408
        │           ├── ProjectFileCollectionView.class, 809
        │           ├── ApplicationProjectTree.class, 1008
        │           ├── ProjectNodeSizeComparator.class, 1020
        │           ├── ProjectTreeIterator.class, 1419
        │           ├── ProjectFile.class, 1458
        │           ├── ProjectDirectoryCollectionView.class, 1549
        │           ├── ProjectDirectory.class, 3790
        │           ├── ProjectIO.class, 5592
        │           └── ApplicationAB4d3.class, 5716
        └── src, 30258
            ├── ProjectFormatException.java, 220
            ├── ProjectIterable.java, 248
            ├── ProjectIterator.java, 370
            ├── ProjectFileCollectionView.java, 814
            ├── ProjectNodeCollection.java, 876
            ├── ProjectNodeSizeComparator.java, 1199
            ├── ProjectDirectoryCollectionView.java, 1428
            ├── ProjectNode.java, 1651
            ├── ProjectTreeIterator.java, 1846
            ├── ProjectFile.java, 1942
            ├── ApplicationProjectTree.java, 2311
            ├── ApplicationAB4d3.java, 4278
            ├── ProjectDirectory.java, 4460
            └── ProjectIO.java, 8615
     */

    public static void main(String[] args) {

        Path rootDirectory = Path.of(".").toAbsolutePath().normalize();

        ProjectIO io = new ProjectIO(rootDirectory);
        ProjectNode root = io.fromFileSystem();

        System.out.println(root);
    }
}