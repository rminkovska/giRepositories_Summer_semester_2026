public class ApplicationAB3d1 {

    public static void main(String[] args) {

        System.out.println("\n===============================================");
        System.out.println("PhysicalPhysicalTreeMap TESTS");
        System.out.println("===============================================");

        PhysicalPhysicalTreeMap treeMap = new PhysicalPhysicalTreeMap();

        Physical k1 = new Nest(new Vector2D(2.0, 2.0), 1.0);
        Physical k2 = new FoodSource(new Vector2D(1.0, 5.0), 1.0);
        Physical k3 = new Nest(new Vector2D(3.0, 1.0), 1.0);
        Physical k4 = new FoodSource(new Vector2D(2.0, 3.0), 1.0);

        Physical v1 = new FoodSource(new Vector2D(20.0, 20.0), 2.0);
        Physical v2 = new Nest(new Vector2D(10.0, 50.0), 3.0);
        Physical v3 = new FoodSource(new Vector2D(30.0, 10.0), 4.0);
        Physical v4 = new Nest(new Vector2D(20.0, 30.0), 5.0);

        check("TreeMap initial size", 0, treeMap.size());
        check("TreeMap initially empty", true, treeMap.isEmpty());
        check("TreeMap get missing", null, treeMap.get(k1));
        check("TreeMap contains missing key", false, treeMap.containsKey(k1));
        check("TreeMap contains missing value", false, treeMap.containsValue(v1));
        check("TreeMap toString empty", "(empty)", treeMap.toString());

        check("TreeMap put k1 returns null", null, treeMap.put(k1, v1));
        check("TreeMap size after k1", 1, treeMap.size());
        check("TreeMap no longer empty", false, treeMap.isEmpty());
        check("TreeMap contains k1", true, treeMap.containsKey(k1));
        checkPhysical("TreeMap get k1", v1, treeMap.get(k1));
        check("TreeMap contains v1", true, treeMap.containsValue(v1));

        check("TreeMap put k2 returns null", null, treeMap.put(k2, v2));
        check("TreeMap put k3 returns null", null, treeMap.put(k3, v3));
        check("TreeMap put k4 returns null", null, treeMap.put(k4, v4));
        check("TreeMap size after four inserts", 4, treeMap.size());

        checkPhysical("TreeMap get k2", v2, treeMap.get(k2));
        checkPhysical("TreeMap get k3", v3, treeMap.get(k3));
        checkPhysical("TreeMap get k4", v4, treeMap.get(k4));

        Physical k1Equal = new Nest(new Vector2D(2.0, 2.0), 1.0);
        Physical v1Replacement = new Nest(new Vector2D(99.0, 99.0), 9.0);

        check("TreeMap contains equal key", true, treeMap.containsKey(k1Equal));
        checkPhysical("TreeMap get equal key", v1, treeMap.get(k1Equal));
        checkPhysical("TreeMap replace equal key returns old value", v1, treeMap.put(k1Equal, v1Replacement));
        check("TreeMap size unchanged after replace", 4, treeMap.size());
        checkPhysical("TreeMap get k1 after replace", v1Replacement, treeMap.get(k1));
        check("TreeMap contains old value after replace", false, treeMap.containsValue(v1));
        check("TreeMap contains replacement value", true, treeMap.containsValue(v1Replacement));

        PhysicalDoublyLinkedList keys = treeMap.keys();
        check("TreeMap keys size", 4, keys.size());
        checkPhysical("TreeMap keys ordered get(0)", k2, keys.get(0));
        checkPhysical("TreeMap keys ordered get(1)", k1, keys.get(1));
        checkPhysical("TreeMap keys ordered get(2)", k4, keys.get(2));
        checkPhysical("TreeMap keys ordered get(3)", k3, keys.get(3));

        Physical[] values = treeMap.values();
        check("TreeMap values length", 4, values.length);
        checkPhysical("TreeMap values ordered get(0)", v2, values[0]);
        checkPhysical("TreeMap values ordered get(1)", v1Replacement, values[1]);
        checkPhysical("TreeMap values ordered get(2)", v4, values[2]);
        checkPhysical("TreeMap values ordered get(3)", v3, values[3]);

        String treeMapString = treeMap.toString();
        check("TreeMap toString contains k1", true, treeMapString.contains(k1.toString()));
        check("TreeMap toString contains replacement value", true, treeMapString.contains(v1Replacement.toString()));

        treeMap.clear();
        check("TreeMap empty after clear", true, treeMap.isEmpty());
        check("TreeMap size after clear", 0, treeMap.size());
        check("TreeMap get after clear", null, treeMap.get(k1));
        check("TreeMap containsKey after clear", false, treeMap.containsKey(k1));
        check("TreeMap containsValue after clear", false, treeMap.containsValue(v1Replacement));
        check("TreeMap keys empty after clear", 0, treeMap.keys().size());
        check("TreeMap values empty after clear", 0, treeMap.values().length);
        check("TreeMap toString after clear", "(empty)", treeMap.toString());

        System.out.println("===============================================");
        System.out.println("PhysicalPhysicalHashMap TESTS");
        System.out.println("===============================================");

        PhysicalPhysicalMap map = new PhysicalPhysicalHashMap();

        Physical a = new Nest(new Vector2D(1.0, 1.0), 1.0);
        Physical b = new FoodSource(new Vector2D(2.0, 2.0), 1.0);
        Physical c = new Nest(new Vector2D(3.0, 3.0), 1.0);
        Physical aEqual = new Nest(new Vector2D(1.0, 1.0), 1.0);

        check("map initially empty", true, map.isEmpty());
        check("map initial size", 0, map.size());
        check("map contains missing key", false, map.containsKey(a));
        check("map get missing key", null, map.get(a));

        check("map put new key returns null", null, map.put(a, b));
        check("map size after put", 1, map.size());
        check("map isEmpty after put", false, map.isEmpty());
        check("map contains original key", true, map.containsKey(a));
        check("map contains equal key", true, map.containsKey(aEqual));
        checkPhysical("map get original key", b, map.get(a));
        checkPhysical("map get equal key", b, map.get(aEqual));

        checkPhysical("map replace returns old value", b, map.put(aEqual, c));
        check("map size unchanged after replace", 1, map.size());
        checkPhysical("map get after replace", c, map.get(a));

        for (int i = 0; i < 100; i++) {
            Physical key = new FoodSource(new Vector2D(i + 10.0, i + 20.0), i + 1.0);
            Physical value = new Nest(new Vector2D(i + 30.0, i + 40.0), i + 2.0);
            map.put(key, value);
            check("map contains inserted key " + i, true, map.containsKey(key));
            checkPhysical("map get inserted key " + i, value, map.get(key));
        }

        check("map size after many inserts", 101, map.size());

        map.clear();
        check("map empty after clear", true, map.isEmpty());
        check("map size after clear", 0, map.size());
        check("map no longer contains key after clear", false, map.containsKey(a));

        System.out.println("\n===============================================");
        System.out.println("PhysicalMultiTree TESTS");
        System.out.println("===============================================");

        PhysicalMultiTree emptyTree = new PhysicalMultiTree();

        check("empty tree size", 0, emptyTree.size());
        check("empty tree isEmpty", true, emptyTree.isEmpty());
        check("empty tree primary", null, emptyTree.getPrimary());
        check("empty tree contains missing", false, emptyTree.contains(a));
        check("empty tree toString", "(empty)", emptyTree.toString());

        Physical root = new Nest(new Vector2D(400, 270), 200);
        PhysicalMultiTree tree = new PhysicalMultiTree(root);

        Physical p1 = new FoodSource(new Vector2D(350, 300), 80);
        Physical p2 = new FoodSource(new Vector2D(360, 335), 7);
        Physical p3 = new FoodSource(new Vector2D(370, 275), 5);
        Physical p4 = new FoodSource(new Vector2D(350, 280), 30);
        Physical p5 = new FoodSource(new Vector2D(335, 285), 10);
        Physical p6 = new Nest(new Vector2D(500, 300), 60);
        Physical p7 = new Nest(new Vector2D(400, 200), 90);
        Physical p8 = new Nest(new Vector2D(390, 260), 250);

        check("tree initial size", 1, tree.size());
        check("tree initially not empty", false, tree.isEmpty());
        checkPhysical("tree initial primary", root, tree.getPrimary());
        check("tree contains root", true, tree.contains(root));

        Physical rootEqual = new Nest(new Vector2D(400, 270), 200);
        check("tree contains equal root", true, tree.contains(rootEqual));
        check("tree rejects equal duplicate root", false, tree.insert(rootEqual));
        check("tree size unchanged after duplicate", 1, tree.size());

        check("insert p1", true, tree.insert(p1));
        check("insert p2", true, tree.insert(p2));
        check("insert p3", true, tree.insert(p3));
        check("insert p5", true, tree.insert(p5));
        check("insert p6", true, tree.insert(p6));

        check("tree size after five inserts", 6, tree.size());
        check("tree contains p1", true, tree.contains(p1));
        check("tree contains p2", true, tree.contains(p2));
        check("tree contains p3", true, tree.contains(p3));
        check("tree contains p5", true, tree.contains(p5));
        check("tree contains p6", true, tree.contains(p6));

        check("insert p4 restructures subtree", true, tree.insert(p4));
        check("tree size after p4", 7, tree.size());
        check("tree contains p4", true, tree.contains(p4));

        check("insert p7 fails due to partial overlap", false, tree.insert(p7));
        check("tree size unchanged after failed insert", 7, tree.size());
        check("tree does not contain p7", false, tree.contains(p7));

        PhysicalPhysicalMap parentsBeforeNewRoot = tree.parentMap();

        check("parent map size before new root", 6, parentsBeforeNewRoot.size());
        checkPhysical("parent of p1", root, parentsBeforeNewRoot.get(p1));
        checkPhysical("parent of p6", root, parentsBeforeNewRoot.get(p6));
        checkPhysical("parent of p2", p1, parentsBeforeNewRoot.get(p2));
        checkPhysical("parent of p4", p1, parentsBeforeNewRoot.get(p4));
        checkPhysical("parent of p3", p4, parentsBeforeNewRoot.get(p3));
        checkPhysical("parent of p5", p4, parentsBeforeNewRoot.get(p5));
        check("parent map does not contain primary root", false, parentsBeforeNewRoot.containsKey(root));

        check("insert p8 becomes new primary", true, tree.insert(p8));
        check("tree size after p8", 8, tree.size());
        checkPhysical("new primary after p8", p8, tree.getPrimary());

        PhysicalPhysicalMap parentsAfterNewRoot = tree.parentMap();

        check("parent map size after new root", 7, parentsAfterNewRoot.size());
        checkPhysical("parent of old root after new root", p8, parentsAfterNewRoot.get(root));
        checkPhysical("parent of p1 after new root", root, parentsAfterNewRoot.get(p1));
        checkPhysical("parent of p6 after new root", root, parentsAfterNewRoot.get(p6));
        check("parent map does not contain new primary", false, parentsAfterNewRoot.containsKey(p8));

        check("old parent map is snapshot and does not contain old root", false,
                parentsBeforeNewRoot.containsKey(root));
        check("old parent map is snapshot and does not contain p8", false,
                parentsBeforeNewRoot.containsKey(p8));

        String treeString = tree.toString();
        check("toString contains new primary", true,
                treeString.contains("Nest@(390.0, 260.0), r=250.0"));
        check("toString contains old root", true,
                treeString.contains("Nest@(400.0, 270.0), r=200.0"));
        check("toString contains p4", true,
                treeString.contains("FoodSource@(350.0, 280.0), r=30.0"));

        tree.clear();
        check("tree empty after clear", true, tree.isEmpty());
        check("tree size after clear", 0, tree.size());
        check("tree primary after clear", null, tree.getPrimary());
        check("tree contains root after clear", false, tree.contains(root));
        check("tree parent map after clear empty", true, tree.parentMap().isEmpty());
        check("tree toString after clear", "(empty)", tree.toString());

        System.out.println("\n===============================================");
        System.out.println("PhysicalMultiTreeNode TESTS");
        System.out.println("===============================================");

        PhysicalMultiTreeNode emptyNode = new PhysicalMultiTreeNodeEmpty();
        check("empty node value", null, emptyNode.getValue());
        check("empty node children length", 0, emptyNode.getChildren().length);
        check("empty node contains", false, emptyNode.contains(root));
        check("empty node compatible", true, emptyNode.isCompatibleWith(root));

        PhysicalMultiTreeNode leaf = new PhysicalMultiTreeNodeLeaf(root);
        checkPhysical("leaf value", root, leaf.getValue());
        check("leaf children length", 0, leaf.getChildren().length);
        check("leaf contains root", true, leaf.contains(root));
        check("leaf contains equal root", true, leaf.contains(rootEqual));
        check("leaf find existing returns non-empty", true, leaf.findNode(root).getValue() != null);
        check("leaf find missing returns empty", null, leaf.findNode(p1).getValue());

        PhysicalMultiTreeNode insertedIntoLeaf = leaf.insert(p1);
        checkPhysical("leaf insert creates subtree with same primary", root, insertedIntoLeaf.getValue());
        check("leaf insert creates one child", 1, insertedIntoLeaf.getChildren().length);
        checkPhysical("leaf insert child is p1", p1, insertedIntoLeaf.getChildren()[0].getValue());

        System.out.println("\n===============================================");
        System.out.println("ALL TESTS COMPLETED");
        System.out.println("===============================================");
    }

    private static void checkPhysical(String testName, Physical expected, Physical actual) {
        if (expected == actual) {
            ok();
        } else {
            fail(testName, physicalToString(expected), physicalToString(actual));
        }
    }

    private static String physicalToString(Physical p) {
        if (p == null) {
            return "null";
        }
        return p.getClass().getSimpleName()
                + "@(" + p.getPosition().getX() + ", " + p.getPosition().getY()
                + "), r=" + p.getRadius();
    }

    private static void check(String testName, Object expected, Object actual) {
        if (java.util.Objects.equals(expected, actual)) {
            ok();
        } else {
            fail(testName, expected, actual);
        }
    }

    private static void fail(String testName, Object expected, Object actual) {
        System.out.println(testName + " FAILED -> Expected: " + expected + ", Actual: " + actual);
    }

    private static void ok() {
        System.out.println("OK");
    }
}