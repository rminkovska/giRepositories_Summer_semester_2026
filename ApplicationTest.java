public class ApplicationTest {

    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("Vector2DSinglyLinkedList TESTS");
        System.out.println("===============================================");

        Vector2DSinglyLinkedList list1 = new Vector2DSinglyLinkedList();
        Vector2D v1 = new Vector2D(1.0, 2.0);
        Vector2D v2 = new Vector2D(3.0, 4.0);
        Vector2D v3 = new Vector2D(5.0, 6.0);
        Vector2D v4 = new Vector2D(7.0, 8.0);
        Vector2D v5 = new Vector2D(9.0, 10.0);

        check("Vector2DSinglyLinkedList initial size", 0, list1.size());
        check("Vector2DSinglyLinkedList isEmpty on empty list", true, list1.isEmpty());
        check("Vector2DSinglyLinkedList peekFirst on empty list", null, list1.peekFirst());
        check("Vector2DSinglyLinkedList peekLast on empty list", null, list1.peekLast());
        check("Vector2DSinglyLinkedList pollFirst on empty list", null, list1.pollFirst());
        check("Vector2DSinglyLinkedList pollLast on empty list", null, list1.pollLast());

        list1.addFirst(v1);
        check("Vector2DSinglyLinkedList addFirst size", 1, list1.size());
        checkVector("Vector2DSinglyLinkedList addFirst content", v1, list1.peekFirst());
        checkVector("Vector2DSinglyLinkedList peekLast after addFirst", v1, list1.peekLast());

        list1.addFirst(v2);
        check("Vector2DSinglyLinkedList addFirst second size", 2, list1.size());
        checkVector("Vector2DSinglyLinkedList addFirst second head", v2, list1.peekFirst());
        checkVector("Vector2DSinglyLinkedList peekLast after second addFirst", v1, list1.peekLast());

        Vector2D polledFirst = list1.pollFirst();
        checkVector("Vector2DSinglyLinkedList pollFirst value", v2, polledFirst);
        check("Vector2DSinglyLinkedList size after pollFirst", 1, list1.size());
        checkVector("Vector2DSinglyLinkedList new head after pollFirst", v1, list1.peekFirst());

        list1.addLast(v3);
        check("Vector2DSinglyLinkedList addLast size", 2, list1.size());
        checkVector("Vector2DSinglyLinkedList addLast head unchanged", v1, list1.peekFirst());
        checkVector("Vector2DSinglyLinkedList addLast last", v3, list1.peekLast());

        list1.add(1, v4); // list: v1, v4, v3
        check("Vector2DSinglyLinkedList add(index) size", 3, list1.size());
        checkVector("Vector2DSinglyLinkedList get(0)", v1, list1.get(0));
        checkVector("Vector2DSinglyLinkedList get(1)", v4, list1.get(1));
        checkVector("Vector2DSinglyLinkedList get(2)", v3, list1.get(2));

        list1.add(0, v5); // list: v5, v1, v4, v3
        check("Vector2DSinglyLinkedList add(index=0) size", 4, list1.size());
        checkVector("Vector2DSinglyLinkedList head after add(index=0)", v5, list1.peekFirst());

        check("Vector2DSinglyLinkedList contains existing element", true, list1.contains(v4));
        check("Vector2DSinglyLinkedList contains missing element", false, list1.contains(new Vector2D(11.0, 12.0)));

        Vector2D removed = list1.remove(1); // removes v1
        checkVector("Vector2DSinglyLinkedList remove(index) returned element", v1, removed);
        check("Vector2DSinglyLinkedList size after remove(index)", 3, list1.size());
        checkVector("Vector2DSinglyLinkedList get(0) after remove(index)", v5, list1.get(0));
        checkVector("Vector2DSinglyLinkedList get(1) after remove(index)", v4, list1.get(1));
        checkVector("Vector2DSinglyLinkedList get(2) after remove(index)", v3, list1.get(2));

        Vector2D polledLast = list1.pollLast(); // removes v3
        checkVector("Vector2DSinglyLinkedList pollLast value", v3, polledLast);
        check("Vector2DSinglyLinkedList size after pollLast", 2, list1.size());
        checkVector("Vector2DSinglyLinkedList last after pollLast", v4, list1.peekLast());

        Vector2DSinglyLinkedList copy = new Vector2DSinglyLinkedList(list1);
        check("Vector2DSinglyLinkedList copy constructor size", list1.size(), copy.size());
        checkVector("Vector2DSinglyLinkedList copy constructor first element", list1.get(0), copy.get(0));
        checkVector("Vector2DSinglyLinkedList copy constructor second element", list1.get(1), copy.get(1));

        list1.pollFirst();
        list1.pollFirst();
        check("Vector2DSinglyLinkedList empty after removing all", true, list1.isEmpty());
        check("Vector2DSinglyLinkedList size after removing all", 0, list1.size());

        System.out.println("\n===============================================");
        System.out.println("Vector2DAntTreeMap TESTS");
        System.out.println("===============================================");

        Vector2DAntTreeMap map1 = new Vector2DAntTreeMap();

        check("Vector2DAntTreeMap initial size", 0, map1.size());
        check("Vector2DAntTreeMap containsKey on empty map", false, map1.containsKey(new Vector2D(1.0, 1.0)));
        check("Vector2DAntTreeMap get on empty map", null, map1.get(new Vector2D(1.0, 1.0)));

        // Adjust these two lines if your current constructors of World or Ant differ.
        Ant[] ants = new Ant[5];
        World world = new World(200, 200, new Vector2D(0, 0), new Vector2D(0, 0), ants);

        Ant ant1 = ants[0] = new Ant(world);
        Ant ant2 = ants[1] = new Ant(world);
        Ant ant3 = ants[2] = new Ant(world);
        Ant ant4 = ants[3] = new Ant(world);
        Ant ant5 = ants[4] = new Ant(world);

        Vector2D k1 = new Vector2D(2.0, 2.0);
        Vector2D k2 = new Vector2D(1.0, 5.0);
        Vector2D k3 = new Vector2D(3.0, 1.0);
        Vector2D k4 = new Vector2D(2.0, 1.0);
        Vector2D k5 = new Vector2D(2.0, 3.0);

        map1.put(k1, ant1);
        check("Vector2DAntTreeMap size after first put", 1, map1.size());
        check("Vector2DAntTreeMap get first inserted value", ant1, map1.get(k1));
        check("Vector2DAntTreeMap containsKey first inserted key", true, map1.containsKey(k1));
        check("Vector2DAntTreeMap containsValue first inserted value", true, map1.containsValue(ant1));
        check("Vector2DAntTreeMap containsValue missing value", false, map1.containsValue(ant2));

        map1.put(k2, ant2);
        map1.put(k3, ant3);
        map1.put(k4, ant4);
        map1.put(k5, ant5);
        check("Vector2DAntTreeMap size after multiple puts", 5, map1.size());

        check("Vector2DAntTreeMap get existing left subtree value", ant2, map1.get(k2));
        check("Vector2DAntTreeMap get existing right subtree value", ant3, map1.get(k3));
        check("Vector2DAntTreeMap get existing same-x smaller-y value", ant4, map1.get(k4));
        check("Vector2DAntTreeMap get existing same-x larger-y value", ant5, map1.get(k5));
        check("Vector2DAntTreeMap get missing key", null, map1.get(new Vector2D(100.0, 100.0)));

        check("Vector2DAntTreeMap containsKey existing", true, map1.containsKey(k3));
        check("Vector2DAntTreeMap containsKey missing", false, map1.containsKey(new Vector2D(4.0, 4.0)));

        check("Vector2DAntTreeMap containsValue existing deep node", true, map1.containsValue(ant5));
        check("Vector2DAntTreeMap containsValue missing", false, map1.containsValue(new Ant(world)));

        Ant oldValue = map1.put(k1, ant3);
        check("Vector2DAntTreeMap put existing key returns old value", ant1, oldValue);
        check("Vector2DAntTreeMap size unchanged after replace", 5, map1.size());
        check("Vector2DAntTreeMap replaced value accessible", ant3, map1.get(k1));

        Vector2DSinglyLinkedList keysList = map1.keys();
        check("Vector2DAntTreeMap keys size", 5, keysList.size());

        // Expected lexicographic order:
        // (1,5), (2,1), (2,2), (2,3), (3,1)
        checkVector("Vector2DAntTreeMap keys order index 0", k2, keysList.get(0));
        checkVector("Vector2DAntTreeMap keys order index 1", k4, keysList.get(1));
        checkVector("Vector2DAntTreeMap keys order index 2", k1, keysList.get(2));
        checkVector("Vector2DAntTreeMap keys order index 3", k5, keysList.get(3));
        checkVector("Vector2DAntTreeMap keys order index 4", k3, keysList.get(4));

        Ant[] values = map1.values();
        check("Vector2DAntTreeMap values length", 5, values.length);
        check("Vector2DAntTreeMap values contains ant2", true, containsAnt(values, ant2));
        check("Vector2DAntTreeMap values contains ant3", true, containsAnt(values, ant3));
        check("Vector2DAntTreeMap values contains ant4", true, containsAnt(values, ant4));
        check("Vector2DAntTreeMap values contains ant5", true, containsAnt(values, ant5));

        System.out.println("\n===============================================");
        System.out.println("ALL TESTS COMPLETED");
        System.out.println("===============================================");
    }

    private static boolean containsAnt(Ant[] values, Ant target) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == target) {
                return true;
            }
        }
        return false;
    }

    private static void checkVector(String testName, Vector2D expected, Vector2D actual) {
        boolean ok = expected == null && actual == null
                || expected != null && actual != null
                && Double.compare(expected.getX(), actual.getX()) == 0
                && Double.compare(expected.getY(), actual.getY()) == 0;

        if (ok) {
            ok();
        } else {
            fail(testName,
                    expected == null ? "null" : "(" + expected.getX() + ", " + expected.getY() + ")",
                    actual == null ? "null" : "(" + actual.getX() + ", " + actual.getY() + ")");
        }
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