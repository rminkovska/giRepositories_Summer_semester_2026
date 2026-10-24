public class ApplicationAB4d1 {

    public static void main(String[] args) {

        Physical a = new FoodSource(new Vector2D(1, 1), 1);
        Physical b = new Nest(new Vector2D(2, 2), 2);
        Physical c = new FoodSource(new Vector2D(3, 3), 3);
        Physical d = new Nest(new Vector2D(4, 4), 4);

        Physical v1 = new FoodSource(new Vector2D(10, 10), 1);
        Physical v2 = new Nest(new Vector2D(20, 20), 2);
        Physical v3 = new FoodSource(new Vector2D(30, 30), 3);

        System.out.println("\n===============================================");
        System.out.println("HashMap keySetView TESTS");
        System.out.println("===============================================");

        PhysicalPhysicalMap hashMap = new PhysicalPhysicalHashMap();

        PhysicalSet hashView = hashMap.keySetView();

        check("Hash view initially empty", true, hashView.isEmpty());
        check("Hash view initial size", 0, hashView.size());

        hashMap.put(a, v1);
        hashMap.put(b, v2);

        check("Hash view size after map puts", 2, hashView.size());
        check("Hash view contains a", true, hashView.contains(a));
        check("Hash view contains b", true, hashView.contains(b));
        check("Hash view contains c", false, hashView.contains(c));

        check("Hash view add c", true, hashView.add(c));
        check("Hash map contains c after view add", true, hashMap.containsKey(c));
        check("Hash map value for c is null", null, hashMap.get(c));
        check("Hash view add duplicate c", false, hashView.add(c));
        check("Hash map size after duplicate add", 3, hashMap.size());

        checkIteratorContainsExactly(
                "Hash view iterator after adds",
                hashView.iterator(),
                new Physical[]{a, b, c}
        );

        hashMap.put(d, v3);

        check("Hash view sees later map put", true, hashView.contains(d));
        check("Hash view size after later map put", 4, hashView.size());

        PhysicalIterator hashIteratorNoStructuralChange = hashView.iterator();
        hashMap.put(a, new FoodSource(new Vector2D(500, 500), 50));

        check(
                "Hash iterator survives value replacement",
                true,
                hashIteratorNoStructuralChange.hasNext()
        );

        check(
                "Hash iterator next after value replacement",
                a,
                hashIteratorNoStructuralChange.next()
        );

        PhysicalIterator hashIteratorBeforeModification = hashView.iterator();
        hashMap.put(new Nest(new Vector2D(99, 99), 9), v1);

        checkConcurrentModification(
                "Hash view iterator detects map modification",
                hashIteratorBeforeModification,
                "1 modification(s) detected!"

        );

        hashView.clear();
        hashMap.put(d, v3);

        PhysicalIterator hashIteratorBeforeViewModification = hashView.iterator();
        hashView.add(new FoodSource(new Vector2D(100, 100), 10));
        hashView.add(new FoodSource(new Vector2D(101, 101), 11));

        checkConcurrentModification(
                "Hash view iterator detects view modification",
                hashIteratorBeforeViewModification,
                "2 modification(s) detected!"
        );

        hashView.clear();

        check("Hash map empty after view clear", true, hashMap.isEmpty());
        check("Hash view empty after clear", true, hashView.isEmpty());

        System.out.println("\n===============================================");
        System.out.println("TreeMap keySetView TESTS");
        System.out.println("===============================================");

        PhysicalPhysicalMap treeMap = new PhysicalPhysicalTreeMap();

        PhysicalSet treeView = treeMap.keySetView();

        check("Tree view initially empty", true, treeView.isEmpty());
        check("Tree view initial size", 0, treeView.size());

        treeMap.put(a, v1);
        treeMap.put(b, v2);

        check("Tree view size after map puts", 2, treeView.size());
        check("Tree view contains a", true, treeView.contains(a));
        check("Tree view contains b", true, treeView.contains(b));
        check("Tree view contains c", false, treeView.contains(c));

        check("Tree view add c", true, treeView.add(c));
        check("Tree map contains c after view add", true, treeMap.containsKey(c));
        check("Tree map value for c is null", null, treeMap.get(c));
        check("Tree view add duplicate c", false, treeView.add(c));
        check("Tree map size after duplicate add", 3, treeMap.size());

        checkIteratorContainsExactly(
                "Tree view iterator after adds",
                treeView.iterator(),
                new Physical[]{a, b, c}
        );

        PhysicalIterator ordered = treeView.iterator();

        check("Tree order 0", a, ordered.next());
        check("Tree order 1", b, ordered.next());
        check("Tree order 2", c, ordered.next());

        treeMap.put(d, v3);

        check("Tree view sees later map put", true, treeView.contains(d));
        check("Tree view size after later map put", 4, treeView.size());

        PhysicalIterator treeIteratorNoStructuralChange = treeView.iterator();

        treeMap.put(a, new FoodSource(new Vector2D(600, 600), 60));

        check(
                "Tree iterator survives value replacement",
                true,
                treeIteratorNoStructuralChange.hasNext()
        );

        check(
                "Tree iterator next after value replacement",
                a,
                treeIteratorNoStructuralChange.next()
        );

        PhysicalIterator treeIteratorBeforeModification = treeView.iterator();
        treeMap.put(new Nest(new Vector2D(101, 101), 11), v1);
        treeMap.put(new Nest(new Vector2D(102, 102), 12), v1);

        checkConcurrentModification(
                "Tree view iterator detects map modification",
                treeIteratorBeforeModification,
                "2 modification(s) detected!"
        );

        treeView.clear();
        treeMap.put(d, v3);

        PhysicalIterator treeIteratorBeforeViewModification = treeView.iterator();
        treeView.add(new FoodSource(new Vector2D(102, 102), 12));

        checkConcurrentModification(
                "Tree view iterator detects view modification",
                treeIteratorBeforeViewModification,
                "1 modification(s) detected!"
        );

        treeView.clear();
        check("Tree map empty after view clear", true, treeMap.isEmpty());
        check("Tree view empty after clear", true, treeView.isEmpty());

        System.out.println("\n===============================================");
        System.out.println("ALL AB4.1 VIEW TESTS COMPLETED");
        System.out.println("===============================================");
    }

    private static void checkIteratorContainsExactly(
            String testName,
            PhysicalIterator iterator,
            Physical[] expected) {

        boolean[] found = new boolean[expected.length];
        int count = 0;
        boolean ok = true;

        while (iterator.hasNext()) {
            Physical current = iterator.next();
            count++;

            boolean matched = false;

            for (int i = 0; i < expected.length; i++) {
                if (current == expected[i] && !found[i]) {
                    found[i] = true;
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                ok = false;
                System.out.println(testName + " unexpected element: "
                        + physicalToString(current));
            }
        }

        if (count != expected.length) {
            ok = false;
            System.out.println(testName + " wrong count -> Expected: "
                    + expected.length + ", Actual: " + count);
        }

        for (int i = 0; i < expected.length; i++) {
            if (!found[i]) {
                ok = false;
                System.out.println(testName + " missing element: "
                        + physicalToString(expected[i]));
            }
        }

        if (ok) {
            ok();
        }
    }

    private static void checkConcurrentModification(
            String testName,
            PhysicalIterator iterator,
            String expectedMessage) {

        try {
            iterator.next();
            fail(testName, "ConcurrentModificationException", "no exception");
        } catch (ConcurrentModificationException e) {
            ok();
            check(testName, expectedMessage, e.getMessage());
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
        System.out.println(testName + " FAILED -> Expected: "
                + expected + ", Actual: " + actual);
    }

    private static void ok() {
        System.out.println("OK");
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
}