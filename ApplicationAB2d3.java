public class ApplicationAB2d3 {

    public static void main(String[] args) {


        System.out.println("===============================================");
        System.out.println("PhysicalDoublyLinkedList TESTS");
        System.out.println("===============================================");

        PhysicalDoublyLinkedList dList = new PhysicalDoublyLinkedList();

        Physical dl1 = new Nest(new Vector2D(1.0, 2.0), 1.0);
        Physical dl2 = new FoodSource(new Vector2D(3.0, 4.0), 2.0);
        Physical dl3 = new Nest(new Vector2D(5.0, 6.0), 3.0);
        Physical dl4 = new FoodSource(new Vector2D(7.0, 8.0), 4.0);
        Physical dl5 = new Nest(new Vector2D(9.0, 10.0), 5.0);

        check("PhysicalDoublyLinkedList initial size", 0, dList.size());
        check("PhysicalDoublyLinkedList isEmpty on empty list", true, dList.isEmpty());
        check("PhysicalDoublyLinkedList peekFirst on empty list", null, dList.peekFirst());
        check("PhysicalDoublyLinkedList peekLast on empty list", null, dList.peekLast());
        check("PhysicalDoublyLinkedList pollFirst on empty list", null, dList.pollFirst());
        check("PhysicalDoublyLinkedList pollLast on empty list", null, dList.pollLast());

        dList.addFirst(dl1);
        check("PhysicalDoublyLinkedList addFirst size", 1, dList.size());
        checkPhysical("PhysicalDoublyLinkedList addFirst content", dl1, dList.peekFirst());
        checkPhysical("PhysicalDoublyLinkedList peekLast after addFirst", dl1, dList.peekLast());

        dList.addFirst(dl2);
        check("PhysicalDoublyLinkedList addFirst second size", 2, dList.size());
        checkPhysical("PhysicalDoublyLinkedList addFirst second head", dl2, dList.peekFirst());
        checkPhysical("PhysicalDoublyLinkedList peekLast after second addFirst", dl1, dList.peekLast());

        Physical dPolledFirst = dList.pollFirst();
        checkPhysical("PhysicalDoublyLinkedList pollFirst value", dl2, dPolledFirst);
        check("PhysicalDoublyLinkedList size after pollFirst", 1, dList.size());
        checkPhysical("PhysicalDoublyLinkedList new head after pollFirst", dl1, dList.peekFirst());

        dList.addLast(dl3);
        check("PhysicalDoublyLinkedList addLast size", 2, dList.size());
        checkPhysical("PhysicalDoublyLinkedList addLast head unchanged", dl1, dList.peekFirst());
        checkPhysical("PhysicalDoublyLinkedList addLast last", dl3, dList.peekLast());

        dList.add(1, dl4); // list: dl1, dl4, dl3
        check("PhysicalDoublyLinkedList add(index) size", 3, dList.size());
        checkPhysical("PhysicalDoublyLinkedList get(0)", dl1, dList.get(0));
        checkPhysical("PhysicalDoublyLinkedList get(1)", dl4, dList.get(1));
        checkPhysical("PhysicalDoublyLinkedList get(2)", dl3, dList.get(2));

        dList.add(0, dl5); // list: dl5, dl1, dl4, dl3
        check("PhysicalDoublyLinkedList add(index=0) size", 4, dList.size());
        checkPhysical("PhysicalDoublyLinkedList head after add(index=0)", dl5, dList.peekFirst());

        check("PhysicalDoublyLinkedList contains existing element", true, dList.contains(dl4));
        check("PhysicalDoublyLinkedList contains missing element", false,
                dList.contains(new Nest(new Vector2D(11.0, 12.0), 1.0)));

        Physical dRemoved = dList.remove(1); // removes dl1
        checkPhysical("PhysicalDoublyLinkedList remove(index) returned element", dl1, dRemoved);
        check("PhysicalDoublyLinkedList size after remove(index)", 3, dList.size());
        checkPhysical("PhysicalDoublyLinkedList get(0) after remove(index)", dl5, dList.get(0));
        checkPhysical("PhysicalDoublyLinkedList get(1) after remove(index)", dl4, dList.get(1));
        checkPhysical("PhysicalDoublyLinkedList get(2) after remove(index)", dl3, dList.get(2));

        Physical dPolledLast = dList.pollLast(); // removes dl3
        checkPhysical("PhysicalDoublyLinkedList pollLast value", dl3, dPolledLast);
        check("PhysicalDoublyLinkedList size after pollLast", 2, dList.size());
        checkPhysical("PhysicalDoublyLinkedList last after pollLast", dl4, dList.peekLast());

        PhysicalDoublyLinkedList dCopy = new PhysicalDoublyLinkedList(dList);
        check("PhysicalDoublyLinkedList copy constructor size", dList.size(), dCopy.size());
        checkPhysical("PhysicalDoublyLinkedList copy constructor first element", dList.get(0), dCopy.get(0));
        checkPhysical("PhysicalDoublyLinkedList copy constructor second element", dList.get(1), dCopy.get(1));

        dList.clear();
        check("PhysicalDoublyLinkedList empty after clear", true, dList.isEmpty());
        check("PhysicalDoublyLinkedList size after clear", 0, dList.size());
        check("PhysicalDoublyLinkedList peekFirst after clear", null, dList.peekFirst());
        check("PhysicalDoublyLinkedList peekLast after clear", null, dList.peekLast());

        System.out.println("\n===============================================");
        System.out.println("PhysicalStringTreeMap TESTS");
        System.out.println("===============================================");

        PhysicalStringTreeMap map = new PhysicalStringTreeMap();

        Physical m1 = new Nest(new Vector2D(2.0, 2.0), 1.0);
        Physical m2 = new FoodSource(new Vector2D(1.0, 5.0), 1.0);
        Physical m3 = new Nest(new Vector2D(3.0, 1.0), 1.0);
        Physical m4 = new FoodSource(new Vector2D(2.0, 1.0), 1.0);
        Physical m5 = new Nest(new Vector2D(2.0, 3.0), 2.5);

        check("PhysicalStringTreeMap initial size", 0, map.size());
        check("PhysicalStringTreeMap get on empty map", null, map.get(m1));
        check("PhysicalStringTreeMap containsKey on empty map", false, map.containsKey(m1));
        check("PhysicalStringTreeMap containsValue on empty map", false, map.containsValue("alpha"));
        check("PhysicalStringTreeMap toString on empty map", "(empty)", map.toString());

        check("PhysicalStringTreeMap put first returns null", null, map.put(m1, "alpha"));
        check("PhysicalStringTreeMap size after first put", 1, map.size());
        check("PhysicalStringTreeMap get first key", "alpha", map.get(m1));
        check("PhysicalStringTreeMap containsKey first key", true, map.containsKey(m1));
        check("PhysicalStringTreeMap containsValue first value", true, map.containsValue("alpha"));

        check("PhysicalStringTreeMap put second returns null", null, map.put(m2, "beta"));
        check("PhysicalStringTreeMap put third returns null", null, map.put(m3, "gamma"));
        check("PhysicalStringTreeMap put fourth returns null", null, map.put(m4, "delta"));
        check("PhysicalStringTreeMap put fifth returns null", null, map.put(m5, "epsilon"));

        check("PhysicalStringTreeMap size after five puts", 5, map.size());
        check("PhysicalStringTreeMap get second key", "beta", map.get(m2));
        check("PhysicalStringTreeMap get third key", "gamma", map.get(m3));
        check("PhysicalStringTreeMap get fourth key", "delta", map.get(m4));
        check("PhysicalStringTreeMap get fifth key", "epsilon", map.get(m5));
        check("PhysicalStringTreeMap containsKey second key", true, map.containsKey(m2));
        check("PhysicalStringTreeMap containsKey third key", true, map.containsKey(m3));
        check("PhysicalStringTreeMap containsValue beta", true, map.containsValue("beta"));
        check("PhysicalStringTreeMap containsValue gamma", true, map.containsValue("gamma"));
        check("PhysicalStringTreeMap containsValue delta", true, map.containsValue("delta"));
        check("PhysicalStringTreeMap containsValue epsilon", true, map.containsValue("epsilon"));

        Physical sameCoordsAsM1 = new Nest(new Vector2D(2.0, 2.0), 99.0);
        check("PhysicalStringTreeMap containsKey uses identity, not coordinates", false, map.containsKey(sameCoordsAsM1));
        check("PhysicalStringTreeMap get with same coordinates but different object returns null", null, map.get(sameCoordsAsM1));

        check("PhysicalStringTreeMap put distinct object with same coordinates returns null", null, map.put(sameCoordsAsM1, "alpha2"));
        check("PhysicalStringTreeMap size after inserting distinct same-coordinates key", 6, map.size());
        check("PhysicalStringTreeMap get old key unchanged", "alpha", map.get(m1));
        check("PhysicalStringTreeMap get new same-coordinates key", "alpha2", map.get(sameCoordsAsM1));
        check("PhysicalStringTreeMap containsKey old key still true", true, map.containsKey(m1));
        check("PhysicalStringTreeMap containsKey new same-coordinates key", true, map.containsKey(sameCoordsAsM1));
        check("PhysicalStringTreeMap containsValue old value still present", true, map.containsValue("alpha"));
        check("PhysicalStringTreeMap containsValue new value present", true, map.containsValue("alpha2"));

        check("PhysicalStringTreeMap replace existing identical key returns old value", "alpha", map.put(m1, "alpha3"));
        check("PhysicalStringTreeMap size unchanged after replacing identical key", 6, map.size());
        check("PhysicalStringTreeMap get identical key after replace", "alpha3", map.get(m1));
        check("PhysicalStringTreeMap get distinct same-coordinates key unchanged", "alpha2", map.get(sameCoordsAsM1));
        check("PhysicalStringTreeMap containsValue replaced old value removed", false, map.containsValue("alpha"));
        check("PhysicalStringTreeMap containsValue replacement value present", true, map.containsValue("alpha3"));

        Physical missingKey = new FoodSource(new Vector2D(100.0, 100.0), 1.0);
        check("PhysicalStringTreeMap get missing key", null, map.get(missingKey));
        check("PhysicalStringTreeMap containsKey missing key", false, map.containsKey(missingKey));
        check("PhysicalStringTreeMap containsValue missing value", false, map.containsValue("zeta"));

        PhysicalSinglyLinkedList keys = map.keys();
        check("PhysicalStringTreeMap keys size", 6, keys.size());
        checkPhysical("PhysicalStringTreeMap keys[0]", m2, keys.get(0));
        checkPhysical("PhysicalStringTreeMap keys[1]", m4, keys.get(1));
        checkPhysical("PhysicalStringTreeMap keys[2]", m1, keys.get(2));
        checkPhysical("PhysicalStringTreeMap keys[3]", sameCoordsAsM1, keys.get(3));
        checkPhysical("PhysicalStringTreeMap keys[4]", m5, keys.get(4));
        checkPhysical("PhysicalStringTreeMap keys[5]", m3, keys.get(5));

        String[] values = map.values();
        check("PhysicalStringTreeMap values length", 6, values.length);
        check("PhysicalStringTreeMap values contains alpha3", true, containsString(values, "alpha3"));
        check("PhysicalStringTreeMap values contains alpha2", true, containsString(values, "alpha2"));
        check("PhysicalStringTreeMap values contains beta", true, containsString(values, "beta"));
        check("PhysicalStringTreeMap values contains gamma", true, containsString(values, "gamma"));
        check("PhysicalStringTreeMap values contains delta", true, containsString(values, "delta"));
        check("PhysicalStringTreeMap values contains epsilon", true, containsString(values, "epsilon"));

        String treeMapString = map.toString();
        check("PhysicalStringTreeMap toString contains root", true,
                treeMapString.contains("Nest@(2.0, 2.0), r=1.0 -> alpha3"));
        check("PhysicalStringTreeMap toString contains left child", true,
                treeMapString.contains("  FoodSource@(1.0, 5.0), r=1.0 -> beta"));
        check("PhysicalStringTreeMap toString contains left-right grandchild", true,
                treeMapString.contains("    FoodSource@(2.0, 1.0), r=1.0 -> delta"));
        check("PhysicalStringTreeMap toString contains right child", true,
                treeMapString.contains("  Nest@(3.0, 1.0), r=1.0 -> gamma"));
        check("PhysicalStringTreeMap toString contains nested left child", true,
                treeMapString.contains("    Nest@(2.0, 3.0), r=2.5 -> epsilon"));
        check("PhysicalStringTreeMap toString contains distinct same-coordinates key", true,
                treeMapString.contains("      Nest@(2.0, 2.0), r=99.0 -> alpha2"));
        check("PhysicalStringTreeMap toString starts with root line", true,
                treeMapString.startsWith("Nest@(2.0, 2.0), r=1.0 -> alpha3"));

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

    private static boolean containsPhysical(Physical[] values, Physical target) {
        if (values == null) {
            return false;
        }
        for (Physical value : values) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsString(String[] values, String target) {
        if (values == null) {
            return false;
        }
        for (String value : values) {
            if (java.util.Objects.equals(value, target)) {
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
