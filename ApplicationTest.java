public class ApplicationTest {

    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("PhysicalDoublyLinkedRingList TESTS");
        System.out.println("===============================================");

        PhysicalDoublyLinkedRingList list1 = new PhysicalDoublyLinkedRingList();

        Physical p1 = new Nest(new Vector2D(1.0, 2.0), 1.0);
        Physical p2 = new FoodSource(new Vector2D(3.0, 4.0), 2.0);
        Physical p3 = new Nest(new Vector2D(5.0, 6.0), 3.0);
        Physical p4 = new FoodSource(new Vector2D(7.0, 8.0), 4.0);
        Physical p5 = new Nest(new Vector2D(9.0, 10.0), 5.0);

        check("PhysicalDoublyLinkedRingList initial size", 0, list1.size());
        check("PhysicalDoublyLinkedRingList isEmpty on empty list", true, list1.isEmpty());
        check("PhysicalDoublyLinkedRingList peekFirst on empty list", null, list1.peekFirst());
        check("PhysicalDoublyLinkedRingList peekLast on empty list", null, list1.peekLast());
        check("PhysicalDoublyLinkedRingList pollFirst on empty list", null, list1.pollFirst());
        check("PhysicalDoublyLinkedRingList pollLast on empty list", null, list1.pollLast());

        list1.addFirst(p1);
        check("PhysicalDoublyLinkedRingList addFirst size", 1, list1.size());
        checkPhysical("PhysicalDoublyLinkedRingList addFirst content", p1, list1.peekFirst());
        checkPhysical("PhysicalDoublyLinkedRingList peekLast after addFirst", p1, list1.peekLast());

        list1.addFirst(p2);
        check("PhysicalDoublyLinkedRingList addFirst second size", 2, list1.size());
        checkPhysical("PhysicalDoublyLinkedRingList addFirst second head", p2, list1.peekFirst());
        checkPhysical("PhysicalDoublyLinkedRingList peekLast after second addFirst", p1, list1.peekLast());

        Physical polledFirst = list1.pollFirst();
        checkPhysical("PhysicalDoublyLinkedRingList pollFirst value", p2, polledFirst);
        check("PhysicalDoublyLinkedRingList size after pollFirst", 1, list1.size());
        checkPhysical("PhysicalDoublyLinkedRingList new head after pollFirst", p1, list1.peekFirst());

        list1.addLast(p3);
        check("PhysicalDoublyLinkedRingList addLast size", 2, list1.size());
        checkPhysical("PhysicalDoublyLinkedRingList addLast head unchanged", p1, list1.peekFirst());
        checkPhysical("PhysicalDoublyLinkedRingList addLast last", p3, list1.peekLast());

        list1.add(1, p4); // list: p1, p4, p3
        check("PhysicalDoublyLinkedRingList add(index) size", 3, list1.size());
        checkPhysical("PhysicalDoublyLinkedRingList get(0)", p1, list1.get(0));
        checkPhysical("PhysicalDoublyLinkedRingList get(1)", p4, list1.get(1));
        checkPhysical("PhysicalDoublyLinkedRingList get(2)", p3, list1.get(2));

        list1.add(0, p5); // list: p5, p1, p4, p3
        check("PhysicalDoublyLinkedRingList add(index=0) size", 4, list1.size());
        checkPhysical("PhysicalDoublyLinkedRingList head after add(index=0)", p5, list1.peekFirst());

        check("PhysicalDoublyLinkedRingList contains existing element", true, list1.contains(p4));
        check("PhysicalDoublyLinkedRingList contains missing element", false,
                list1.contains(new Nest(new Vector2D(11.0, 12.0), 1.0)));

        Physical removed = list1.remove(1); // removes p1
        checkPhysical("PhysicalDoublyLinkedRingList remove(index) returned element", p1, removed);
        check("PhysicalDoublyLinkedRingList size after remove(index)", 3, list1.size());
        checkPhysical("PhysicalDoublyLinkedRingList get(0) after remove(index)", p5, list1.get(0));
        checkPhysical("PhysicalDoublyLinkedRingList get(1) after remove(index)", p4, list1.get(1));
        checkPhysical("PhysicalDoublyLinkedRingList get(2) after remove(index)", p3, list1.get(2));

        Physical polledLast = list1.pollLast(); // removes p3
        checkPhysical("PhysicalDoublyLinkedRingList pollLast value", p3, polledLast);
        check("PhysicalDoublyLinkedRingList size after pollLast", 2, list1.size());
        checkPhysical("PhysicalDoublyLinkedRingList last after pollLast", p4, list1.peekLast());

        PhysicalDoublyLinkedRingList copy = new PhysicalDoublyLinkedRingList(list1);
        check("PhysicalDoublyLinkedRingList copy constructor size", list1.size(), copy.size());
        checkPhysical("PhysicalDoublyLinkedRingList copy constructor first element", list1.get(0), copy.get(0));
        checkPhysical("PhysicalDoublyLinkedRingList copy constructor second element", list1.get(1), copy.get(1));

        list1.clear();
        check("PhysicalDoublyLinkedRingList empty after clear", true, list1.isEmpty());
        check("PhysicalDoublyLinkedRingList size after clear", 0, list1.size());
        check("PhysicalDoublyLinkedRingList peekFirst after clear", null, list1.peekFirst());
        check("PhysicalDoublyLinkedRingList peekLast after clear", null, list1.peekLast());

        System.out.println("\n===============================================");
        System.out.println("PhysicalKDTree TESTS");
        System.out.println("===============================================");

        Physical t1 = new Nest(new Vector2D(2.0, 2.0), 1.0);
        Physical t2 = new FoodSource(new Vector2D(1.0, 5.0), 1.0);
        Physical t3 = new Nest(new Vector2D(3.0, 1.0), 1.0);
        Physical t4 = new FoodSource(new Vector2D(2.0, 1.0), 1.0);
        Physical t5 = new Nest(new Vector2D(2.0, 3.0), 2.5);

        Physical[] data = new Physical[]{t1, t2, t3, t4, t5};

        PhysicalKDTreeSet tree1 = new PhysicalKDTreeSet();

        check("PhysicalKDTree initial size", 0, tree1.size());
        check("PhysicalKDTree isEmpty initially", true, tree1.isEmpty());
        check("PhysicalKDTree max radius on empty tree", 0.0, tree1.getMaxStoredRadius());

        tree1.add(t1);
        check("PhysicalKDTree size after first add", 1, tree1.size());
        check("PhysicalKDTree isEmpty after first add", false, tree1.isEmpty());
        check("PhysicalKDTree max radius after first add", 1.0, tree1.getMaxStoredRadius());

        tree1.add(t2);
        tree1.add(t3);
        check("PhysicalKDTree size after individual adds", 3, tree1.size());
        check("PhysicalKDTree max radius after three adds", 1.0, tree1.getMaxStoredRadius());

        check("PhysicalKDTree contains t1 after first add", true, tree1.contains(t1));
        check("PhysicalKDTree contains t2 after add", true, tree1.contains(t2));
        check("PhysicalKDTree contains t3 after add", true, tree1.contains(t3));

        Physical sameDataDifferentObject = new Nest(new Vector2D(2.0, 2.0), 1.0);
        check("PhysicalKDTree contains uses identity, not equality", false, tree1.contains(sameDataDifferentObject));

        PhysicalDoublyLinkedRingList q1 = tree1.rangeQuery(new Vector2D(2.0, 2.0), 0.0);
        check("PhysicalKDTree rangeQuery radius 0 size after individual adds", 1, q1.size());
        checkPhysical("PhysicalKDTree rangeQuery radius 0 result after individual adds", t1, q1.get(0));

        PhysicalDoublyLinkedRingList q2 = tree1.rangeQuery(new Vector2D(2.0, 2.0), 10.0);
        check("PhysicalKDTree rangeQuery large radius size after individual adds", 3, q2.size());
        check("PhysicalKDTree rangeQuery large radius contains t1", true, containsPhysical(q2, t1));
        check("PhysicalKDTree rangeQuery large radius contains t2", true, containsPhysical(q2, t2));
        check("PhysicalKDTree rangeQuery large radius contains t3", true, containsPhysical(q2, t3));

        tree1.add(new Physical[]{t4, t5});
        check("PhysicalKDTree size after add(array)", 5, tree1.size());
        check("PhysicalKDTree max radius after add(array)", 2.5, tree1.getMaxStoredRadius());

        check("PhysicalKDTree contains t4 after add(array)", true, tree1.contains(t4));
        check("PhysicalKDTree contains t5 after add(array)", true, tree1.contains(t5));

        Physical missing = new FoodSource(new Vector2D(100.0, 100.0), 1.0);
        check("PhysicalKDTree contains missing object", false, tree1.contains(missing));

        PhysicalDoublyLinkedRingList q3 = tree1.rangeQuery(new Vector2D(2.0, 2.0), 1.1);
        check("PhysicalKDTree rangeQuery small radius size", 3, q3.size());
        check("PhysicalKDTree rangeQuery small radius contains t1", true, containsPhysical(q3, t1));
        check("PhysicalKDTree rangeQuery small radius contains t4", true, containsPhysical(q3, t4));
        check("PhysicalKDTree rangeQuery small radius contains t5", true, containsPhysical(q3, t5));

        PhysicalDoublyLinkedRingList q4 = tree1.rangeQuery(new Vector2D(2.0, 2.0), 10.0);
        check("PhysicalKDTree rangeQuery large radius size after all adds", 5, q4.size());
        check("PhysicalKDTree rangeQuery large radius contains t2", true, containsPhysical(q4, t2));
        check("PhysicalKDTree rangeQuery large radius contains t3", true, containsPhysical(q4, t3));
        check("PhysicalKDTree rangeQuery large radius contains t4", true, containsPhysical(q4, t4));
        check("PhysicalKDTree rangeQuery large radius contains t5", true, containsPhysical(q4, t5));

        PhysicalDoublyLinkedRingList q5 = tree1.rangeQuery(1.5, 1.5, 2.5, 3.5);
        check("PhysicalKDTree rectangle range size", 2, q5.size());
        check("PhysicalKDTree rectangle range contains t1", true, containsPhysical(q5, t1));
        check("PhysicalKDTree rectangle range contains t5", true, containsPhysical(q5, t5));

        PhysicalDoublyLinkedRingList q6 = tree1.intersectingAt(new Vector2D(2.0, 2.0), 0.0);
        check("PhysicalKDTree intersectingAt exact center contains t1", true, containsPhysical(q6, t1));
        check("PhysicalKDTree intersectingAt exact center contains t4", true, containsPhysical(q6, t4));
        check("PhysicalKDTree intersectingAt exact center contains t5", true, containsPhysical(q6, t5));

        PhysicalDoublyLinkedRingList q7 = tree1.intersectingAt(new Vector2D(2.0, 2.0), 0.25);
        check("PhysicalKDTree intersectingAt small radius contains t1", true, containsPhysical(q7, t1));
        check("PhysicalKDTree intersectingAt small radius contains t4", true, containsPhysical(q7, t4));
        check("PhysicalKDTree intersectingAt small radius contains t5", true, containsPhysical(q7, t5));

        tree1.clear();
        check("PhysicalKDTree size after clear", 0, tree1.size());
        check("PhysicalKDTree isEmpty after clear", true, tree1.isEmpty());
        check("PhysicalKDTree max radius after clear", 0.0, tree1.getMaxStoredRadius());

        check("PhysicalKDTree contains t1 after clear", false, tree1.contains(t1));
        check("PhysicalKDTree contains t5 after clear", false, tree1.contains(t5));

        PhysicalKDTreeSet tree2 = new PhysicalKDTreeSet(data);
        check("PhysicalKDTree constructor with array size", 5, tree2.size());
        check("PhysicalKDTree constructor with array isEmpty", false, tree2.isEmpty());
        check("PhysicalKDTree constructor with array max radius", 2.5, tree2.getMaxStoredRadius());

        check("PhysicalKDTree constructor tree contains t1", true, tree2.contains(t1));
        check("PhysicalKDTree constructor tree contains t2", true, tree2.contains(t2));
        check("PhysicalKDTree constructor tree contains t3", true, tree2.contains(t3));
        check("PhysicalKDTree constructor tree contains t4", true, tree2.contains(t4));
        check("PhysicalKDTree constructor tree contains t5", true, tree2.contains(t5));

        Physical sameDataDifferentObject2 = new Nest(new Vector2D(2.0, 3.0), 2.5);
        check("PhysicalKDTree constructor tree contains uses identity, not equality", false,
                tree2.contains(sameDataDifferentObject2));

        PhysicalDoublyLinkedRingList q8 = tree2.rangeQuery(new Vector2D(2.0, 2.0), 10.0);
        check("PhysicalKDTree constructor tree rangeQuery size", 5, q8.size());
        check("PhysicalKDTree constructor tree contains t1", true, containsPhysical(q8, t1));
        check("PhysicalKDTree constructor tree contains t2", true, containsPhysical(q8, t2));
        check("PhysicalKDTree constructor tree contains t3", true, containsPhysical(q8, t3));
        check("PhysicalKDTree constructor tree contains t4", true, containsPhysical(q8, t4));
        check("PhysicalKDTree constructor tree contains t5", true, containsPhysical(q8, t5));

        System.out.println("\n===============================================");
        System.out.println("ALL TESTS COMPLETED");
        System.out.println("===============================================");
    }

    private static boolean containsPhysical(PhysicalDoublyLinkedRingList values, Physical target) {
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i) == target) {
                return true;
            }
        }
        return false;
    }

    private static void checkPhysical(String testName, Physical expected, Physical actual) {
        boolean ok = expected == actual;

        if (ok) {
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
                + "@(" + p.getPosition().getX() + ", " + p.getPosition().getY() + "), r=" + p.getRadius();
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