public class ApplicationAB3d2 {

    public static void main(String[] args) {

        // TODO: activate block:

        Physical root = new Nest(new Vector2D(400, 270), 200);
        Physical p1 = new FoodSource(new Vector2D(350, 300), 80);
        Physical p2 = new FoodSource(new Vector2D(360, 335), 7);
        Physical p3 = new Nest(new Vector2D(500, 300), 60);

        System.out.println("\n===============================================");
        System.out.println("PhysicalDoublyLinkedList ITERATOR TESTS");
        System.out.println("===============================================");

        PhysicalDoublyLinkedList emptyList = new PhysicalDoublyLinkedList();
        PhysicalIterator emptyListIterator = emptyList.iterator();

        check("Empty list iterator has no next", false, emptyListIterator.hasNext());
        check("Empty list iterator next returns null", null, emptyListIterator.next());

        PhysicalDoublyLinkedList singleList = new PhysicalDoublyLinkedList();
        singleList.addLast(root);

        PhysicalIterator singleListIterator = singleList.iterator();

        check("Single list iterator has first", true, singleListIterator.hasNext());
        check("Single list iterator hasNext stable", true, singleListIterator.hasNext());
        checkPhysical("Single list iterator first", root, singleListIterator.next());
        check("Single list iterator exhausted", false, singleListIterator.hasNext());
        check("Single list iterator next after exhausted", null, singleListIterator.next());

        PhysicalDoublyLinkedList list = new PhysicalDoublyLinkedList();
        list.addLast(root);
        list.addLast(p1);
        list.addLast(p2);
        list.addLast(p3);

        PhysicalIterator listIterator = list.iterator();

        check("List iterator has first", true, listIterator.hasNext());
        checkPhysical("List iterator 0", root, listIterator.next());
        checkPhysical("List iterator 1", p1, listIterator.next());
        checkPhysical("List iterator 2", p2, listIterator.next());
        checkPhysical("List iterator 3", p3, listIterator.next());
        check("List iterator exhausted", false, listIterator.hasNext());
        check("List iterator next after exhausted", null, listIterator.next());

        System.out.println("\n===============================================");
        System.out.println("PhysicalDoublyLinkedList SORTED ITERATOR TESTS");
        System.out.println("===============================================");

        Physical s1 = new Nest(new Vector2D(3.0, 3.0), 1.0);
        Physical s2 = new FoodSource(new Vector2D(1.0, 5.0), 1.0);
        Physical s3 = new Nest(new Vector2D(2.0, 2.0), 1.0);
        Physical s4 = new FoodSource(new Vector2D(2.0, 4.0), 1.0);

        PhysicalComparator comparator = new XComparator();

        PhysicalDoublyLinkedList emptySortedList = new PhysicalDoublyLinkedList();
        PhysicalIterator itEmptySorted = emptySortedList.sortedIterator(comparator);

        check("Sorted iterator empty hasNext", false, itEmptySorted.hasNext());
        check("Sorted iterator empty next", null, itEmptySorted.next());

        PhysicalDoublyLinkedList singleSortedList = new PhysicalDoublyLinkedList();
        singleSortedList.addLast(s1);

        PhysicalIterator itSingleSorted = singleSortedList.sortedIterator(comparator);

        check("Sorted iterator single hasNext", true, itSingleSorted.hasNext());
        checkPhysical("Sorted iterator single element", s1, itSingleSorted.next());
        check("Sorted iterator single exhausted", false, itSingleSorted.hasNext());
        check("Sorted iterator single next after exhausted", null, itSingleSorted.next());

        PhysicalDoublyLinkedList sortedList = new PhysicalDoublyLinkedList();
        sortedList.addLast(s1);
        sortedList.addLast(s2);
        sortedList.addLast(s3);
        sortedList.addLast(null);
        sortedList.addLast(s4);

        // Expected ascending order by XComparator:
        // s2: (1.0, 5.0)
        // s3: (2.0, 2.0)
        // s4: (2.0, 4.0)
        // s1: (3.0, 3.0)

        PhysicalIterator itSorted = sortedList.sortedIterator(comparator);

        check("Sorted iterator has first", true, itSorted.hasNext());
        check("Sorted iterator hasNext stable", true, itSorted.hasNext());
        checkPhysical("Sorted iterator 0", s2, itSorted.next());
        checkPhysical("Sorted iterator 1", s3, itSorted.next());
        checkPhysical("Sorted iterator 2", s4, itSorted.next());
        checkPhysical("Sorted iterator 3", s1, itSorted.next());
        check("Sorted iterator exhausted", false, itSorted.hasNext());
        check("Sorted iterator next after exhausted", null, itSorted.next());

        check("Sorted iterator does not change list size", 5, sortedList.size());
        checkPhysical("Sorted iterator original list get(0)", s1, sortedList.get(0));
        checkPhysical("Sorted iterator original list get(1)", s2, sortedList.get(1));
        checkPhysical("Sorted iterator original list get(2)", s3, sortedList.get(2));
        checkPhysical("Sorted iterator original list get(3)", null, sortedList.get(3));
        checkPhysical("Sorted iterator original list get(4)", s4, sortedList.get(4));

        System.out.println("\n===============================================");
        System.out.println("PhysicalMultiTreeNode ITERATOR-NODE TESTS");
        System.out.println("===============================================");
        PhysicalMultiTreeNode emptyNode = PhysicalMultiTreeNodeEmpty.EMPTY;
        PhysicalTreeIterator emptyIterator = new PhysicalTreeIterator();

        check("Empty node iter returns null", null, emptyNode.iter(emptyIterator, false));
        check("Empty node iterator remains empty", false, emptyIterator.hasNext());

        PhysicalMultiTreeNode leaf = new PhysicalMultiTreeNodeLeaf(root);
        PhysicalTreeIterator leafIterator = new PhysicalTreeIterator();

        checkPhysical("Leaf iter returns stored value", root, leaf.iter(leafIterator, false));
        check("Leaf iter does not add children", false, leafIterator.hasNext());

        PhysicalMultiTreeNode internal =
                new PhysicalMultiTreeNodeInternal(
                        root,
                        new PhysicalMultiTreeNode[]{
                                new PhysicalMultiTreeNodeLeaf(p1),
                                new PhysicalMultiTreeNodeLeaf(p3)
                        }
                );

        PhysicalTreeIterator internalIterator = new PhysicalTreeIterator();

        checkPhysical("Internal iter returns stored value", root, internal.iter(internalIterator, false));
        check("Internal iter adds children", true, internalIterator.hasNext());
        checkPhysical("Internal iterator first child", p1, internalIterator.next());
        checkPhysical("Internal iterator second child", p3, internalIterator.next());
        check("Internal iterator exhausted", false, internalIterator.hasNext());
        check("Internal iterator next after exhausted", null, internalIterator.next());

        System.out.println("\n===============================================");
        System.out.println("PhysicalMultiTree ITERATOR TESTS");
        System.out.println("===============================================");

        PhysicalMultiTree emptyTree = new PhysicalMultiTree();
        PhysicalIterator itEmptyTree = emptyTree.iterator();

        check("Empty tree iterator has no next", false, itEmptyTree.hasNext());
        check("Empty tree iterator next returns null", null, itEmptyTree.next());

        PhysicalMultiTree oneElementTree = new PhysicalMultiTree(root);
        PhysicalIterator itOne = oneElementTree.iterator();

        check("Single tree iterator has first", true, itOne.hasNext());
        checkPhysical("Single tree iterator first", root, itOne.next());
        check("Single tree iterator exhausted", false, itOne.hasNext());
        check("Single tree iterator next after exhausted", null, itOne.next());

        PhysicalMultiTree multiTree = new PhysicalMultiTree(root);

        // Expected structure:
        //
        // root
        //   p1
        //     p2
        //   p3
        //
        multiTree.insert(p1);
        multiTree.insert(p2);
        multiTree.insert(p3);

        PhysicalIterator itTree = multiTree.iterator();

        check("MultiTree iterator has root", true, itTree.hasNext());
        checkPhysical("MultiTree preorder 0", root, itTree.next());
        checkPhysical("MultiTree preorder 1", p1, itTree.next());
        checkPhysical("MultiTree preorder 2", p2, itTree.next());
        checkPhysical("MultiTree preorder 3", p3, itTree.next());
        check("MultiTree iterator exhausted", false, itTree.hasNext());
        check("MultiTree next after exhausted", null, itTree.next());

        System.out.println("\n===============================================");
        System.out.println("MultiTree CHILD ORDER TEST");
        System.out.println("===============================================");

        PhysicalMultiTree orderTree = new PhysicalMultiTree(root);

        Physical c1 = new FoodSource(new Vector2D(400, 270), 2);
        Physical c2 = new FoodSource(new Vector2D(405, 270), 2);
        Physical c3 = new FoodSource(new Vector2D(410, 270), 2);

        // all direct children of root
        orderTree.insert(c1);
        orderTree.insert(c2);
        orderTree.insert(c3);

        PhysicalIterator itOrder = orderTree.iterator();

        // root first
        checkPhysical("Order test root", root, itOrder.next());

        // check array order
        checkPhysical("Order test child 0", c1, itOrder.next());
        checkPhysical("Order test child 1", c2, itOrder.next());
        checkPhysical("Order test child 2", c3, itOrder.next());

        System.out.println("\n===============================================");
        System.out.println("PhysicalPhysicalTreeMapNode DESCENDING ITERATOR TESTS");
        System.out.println("===============================================");

        Physical k1 = new Nest(new Vector2D(2.0, 2.0), 1.0);
        Physical k2 = new FoodSource(new Vector2D(1.0, 5.0), 1.0);
        Physical k3 = new Nest(new Vector2D(3.0, 1.0), 1.0);
        Physical k4 = new FoodSource(new Vector2D(2.0, 3.0), 1.0);

        Physical v1 = new FoodSource(new Vector2D(20.0, 20.0), 2.0);
        Physical v2 = new Nest(new Vector2D(10.0, 50.0), 3.0);
        Physical v3 = new FoodSource(new Vector2D(30.0, 10.0), 4.0);
        Physical v4 = new Nest(new Vector2D(20.0, 30.0), 5.0);

        PhysicalPhysicalTreeMapNode nodeRoot =
                new PhysicalPhysicalTreeMapNode(k1, v1);

        nodeRoot.put(k2, v2);
        nodeRoot.put(k3, v3);
        nodeRoot.put(k4, v4);

        PhysicalTreeIterator nodeIterator = new PhysicalTreeIterator();

        // For reverse in-order, iter(..., false) initializes the right path.
        nodeRoot.iter(nodeIterator, false);

        check("Node iterator has first", true, nodeIterator.hasNext());
        checkPhysical("Node descending 0", k3, nodeIterator.next());
        checkPhysical("Node descending 1", k4, nodeIterator.next());
        checkPhysical("Node descending 2", k1, nodeIterator.next());
        checkPhysical("Node descending 3", k2, nodeIterator.next());
        check("Node iterator exhausted", false, nodeIterator.hasNext());
        check("Node iterator next after exhausted", null, nodeIterator.next());

        System.out.println("\n===============================================");
        System.out.println("PhysicalPhysicalTreeMap DESCENDING ITERATOR TESTS");
        System.out.println("===============================================");

        PhysicalPhysicalTreeMap emptyMap = new PhysicalPhysicalTreeMap();
        PhysicalIterator itEmptyMap = emptyMap.iterator();

        check("Empty map hasNext", false, itEmptyMap.hasNext());
        check("Empty map next", null, itEmptyMap.next());
        check("Empty map hasNext after next", false, itEmptyMap.hasNext());

        PhysicalPhysicalTreeMap singleMap = new PhysicalPhysicalTreeMap();
        singleMap.put(k1, v1);

        PhysicalIterator itSingleMap = singleMap.iterator();

        check("Single map hasNext first", true, itSingleMap.hasNext());
        check("Single map hasNext stable", true, itSingleMap.hasNext());
        checkPhysical("Single map element", k1, itSingleMap.next());
        check("Single map exhausted", false, itSingleMap.hasNext());
        check("Single map next after exhausted", null, itSingleMap.next());

        PhysicalPhysicalTreeMap treeMap = new PhysicalPhysicalTreeMap();

        treeMap.put(k1, v1);
        treeMap.put(k2, v2);
        treeMap.put(k3, v3);
        treeMap.put(k4, v4);

        // XComparator ascending order: k2, k1, k4, k3
        // Descending iterator expected: k3, k4, k1, k2

        PhysicalIterator it = treeMap.iterator();

        check("TreeMap hasNext before first", true, it.hasNext());
        check("TreeMap hasNext stable before first", true, it.hasNext());

        checkPhysical("TreeMap descending 0", k3, it.next());
        check("TreeMap hasNext after first", true, it.hasNext());
        checkPhysical("TreeMap descending 1", k4, it.next());
        checkPhysical("TreeMap descending 2", k1, it.next());
        checkPhysical("TreeMap descending 3", k2, it.next());

        check("TreeMap exhausted", false, it.hasNext());
        check("TreeMap next after exhausted", null, it.next());
        check("TreeMap still exhausted", false, it.hasNext());

        PhysicalPhysicalTreeMap rightChain = new PhysicalPhysicalTreeMap();

        rightChain.put(k2, v2);
        rightChain.put(k1, v1);
        rightChain.put(k4, v4);
        rightChain.put(k3, v3);

        PhysicalIterator itRightChain = rightChain.iterator();

        checkPhysical("Right chain descending 0", k3, itRightChain.next());
        checkPhysical("Right chain descending 1", k4, itRightChain.next());
        checkPhysical("Right chain descending 2", k1, itRightChain.next());
        checkPhysical("Right chain descending 3", k2, itRightChain.next());
        check("Right chain exhausted", false, itRightChain.hasNext());
        check("Right chain next after exhausted", null, itRightChain.next());

        PhysicalPhysicalTreeMap leftChain = new PhysicalPhysicalTreeMap();

        leftChain.put(k3, v3);
        leftChain.put(k4, v4);
        leftChain.put(k1, v1);
        leftChain.put(k2, v2);

        PhysicalIterator itLeftChain = leftChain.iterator();

        checkPhysical("Left chain descending 0", k3, itLeftChain.next());
        checkPhysical("Left chain descending 1", k4, itLeftChain.next());
        checkPhysical("Left chain descending 2", k1, itLeftChain.next());
        checkPhysical("Left chain descending 3", k2, itLeftChain.next());
        check("Left chain exhausted", false, itLeftChain.hasNext());
        check("Left chain next after exhausted", null, itLeftChain.next());

        System.out.println("\n===============================================");
        System.out.println("TreeMap keys() vs iterator() CONSISTENCY TEST");
        System.out.println("===============================================");

        PhysicalPhysicalTreeMap mapConsistency = new PhysicalPhysicalTreeMap();
        mapConsistency.put(k1, v1);
        mapConsistency.put(k2, v2);
        mapConsistency.put(k3, v3);
        mapConsistency.put(k4, v4);
        PhysicalDoublyLinkedList keysList = mapConsistency.keys(); // ascending
        PhysicalIterator itConsistency = mapConsistency.iterator(); // descending

        int n = keysList.size();
        boolean consistent = true;
        for (int i = n - 1; i >= 0; i--) {
            Physical expected = keysList.get(i);
            Physical actual = itConsistency.next();

            if (expected != actual) {
                consistent = false;
                System.out.println("Mismatch at position " + i
                        + " -> expected: " + physicalToString(expected)
                        + ", actual: " + physicalToString(actual));
            }
        }

        if (itConsistency.hasNext()) {
            consistent = false;
            System.out.println("Iterator has unexpected additional elements");
        }

        if (consistent) {
            System.out.println("OK");
        }

        System.out.println("\n===============================================");
        System.out.println("ALL AB3.2 ITERATOR TESTS COMPLETED");
        System.out.println("===============================================");

        //TODO: end of block to activate.
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
                + "@("
                + p.getPosition().getX()
                + ", "
                + p.getPosition().getY()
                + "), r="
                + p.getRadius();
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