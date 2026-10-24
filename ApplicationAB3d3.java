public class ApplicationAB3d3 {

    public static void main(String[] args) {

        // TODO: activate block:

        System.out.println("\n===============================================");
        System.out.println("PhysicalTreeSet TESTS");
        System.out.println("===============================================");

        Physical a = new FoodSource(new Vector2D(3.0, 3.0), 1.0);
        Physical b = new Nest(new Vector2D(1.0, 5.0), 2.0);
        Physical c = new FoodSource(new Vector2D(2.0, 2.0), 3.0);
        Physical d = new Nest(new Vector2D(2.0, 4.0), 4.0);
        Physical e = new Nest(new Vector2D(5.0, 4.0), 4.0);

        PhysicalSet emptySet = new PhysicalTreeSet();

        check("Empty set size", 0, emptySet.size());
        check("Empty set isEmpty", true, emptySet.isEmpty());
        check("Empty set contains a", false, emptySet.contains(a));

        PhysicalIterator itEmpty = emptySet.iterator();

        check("Empty iterator hasNext", false, itEmpty.hasNext());
        check("Empty iterator next", null, itEmpty.next());

        System.out.println("\n-----------------------------------------------");
        System.out.println("ADD / CONTAINS TESTS");
        System.out.println("-----------------------------------------------");

        PhysicalSet set = new PhysicalTreeSet();

        check("Add a", true, set.add(a));
        check("Add b", true, set.add(b));
        check("Add c", true, set.add(c));
        check("Add d", true, set.add(d));

        check("Set size after inserts", 4, set.size());

        check("Contains a", true, set.contains(a));
        check("Contains b", true, set.contains(b));
        check("Contains c", true, set.contains(c));
        check("Contains d", true, set.contains(d));

        check("Contains new object", false,
                set.contains(new FoodSource(new Vector2D(99, 99), 1)));

        System.out.println("\n-----------------------------------------------");
        System.out.println("DUPLICATE INSERT TESTS");
        System.out.println("-----------------------------------------------");

        check("Add duplicate a", false, set.add(a));
        check("Set size unchanged after duplicate", 4, set.size());

        System.out.println("\n-----------------------------------------------");
        System.out.println("ITERATOR TESTS");
        System.out.println("-----------------------------------------------");

        PhysicalIterator iterator = set.iterator();

        java.util.Set<Physical> returned =
                java.util.Collections.newSetFromMap(
                        new java.util.IdentityHashMap<Physical, Boolean>()
                );

        while (iterator.hasNext()) {
            returned.add(iterator.next());
        }

        check("Iterator returned 4 elements", 4, returned.size());

        check("Iterator returned a", true, returned.contains(a));
        check("Iterator returned b", true, returned.contains(b));
        check("Iterator returned c", true, returned.contains(c));
        check("Iterator returned d", true, returned.contains(d));

        check("Iterator exhausted", false, iterator.hasNext());
        check("Iterator next after exhausted", null, iterator.next());

        System.out.println("\n-----------------------------------------------");
        System.out.println("CLEAR TESTS");
        System.out.println("-----------------------------------------------");

        set.clear();

        check("Set size after clear", 0, set.size());
        check("Set empty after clear", true, set.isEmpty());
        check("Set contains a after clear", false, set.contains(a));

        PhysicalIterator itAfterClear = set.iterator();

        check("Iterator after clear hasNext", false, itAfterClear.hasNext());
        check("Iterator after clear next", null, itAfterClear.next());

        System.out.println("\n-----------------------------------------------");
        System.out.println("EQUALS / HASHCODE TESTS");
        System.out.println("-----------------------------------------------");

        PhysicalSet s1 = new PhysicalTreeSet();
        PhysicalSet s2 = new PhysicalTreeSet();

        s1.add(a);
        s1.add(b);
        s1.add(c);
        s1.add(d);

        s2.add(c);
        s2.add(a);
        s2.add(d);
        s2.add(b);

        check("Equal sets", true, s1.equals(s2));
        check("Equal sets symmetric", true, s2.equals(s1));
        check("Equal sets hashCode", s1.hashCode(), s2.hashCode());

        s2.add(e);

        check("Different sets unequal", false, s1.equals(s2));

        System.out.println("\n===============================================");
        System.out.println("PhysicalHashMapIterator TESTS");
        System.out.println("===============================================");

        PhysicalPhysicalHashMap map = new PhysicalPhysicalHashMap();

        Physical v1 = new Nest(new Vector2D(10, 10), 1);
        Physical v2 = new FoodSource(new Vector2D(20, 20), 2);
        Physical v3 = new Nest(new Vector2D(30, 30), 3);

        check("Empty map size", 0, map.size());

        PhysicalIterator mapEmptyIterator = map.iterator();

        check("Empty map iterator hasNext", false,
                mapEmptyIterator.hasNext());

        map.put(a, v1);
        map.put(b, v2);
        map.put(c, v3);

        check("Map size after inserts", 3, map.size());

        PhysicalIterator mapIterator = map.iterator();

        int count = 0;

        while (mapIterator.hasNext()) {

            Physical current = mapIterator.next();

            check("Iterator returned existing key",
                    true,
                    current == a || current == b || current == c);

            count++;
        }

        check("Hash map iterator element count", 3, count);
        check("Hash map iterator next after exhausted",
                null,
                mapIterator.next());

        System.out.println("\n===============================================");
        System.out.println("PhysicalPhysicalMap EQUALS / HASHCODE TESTS");
        System.out.println("===============================================");

        PhysicalPhysicalMap map1 = new PhysicalPhysicalHashMap();
        PhysicalPhysicalMap map2 = new PhysicalPhysicalHashMap();

        Physical mk1 = new Nest(new Vector2D(1.0, 1.0), 1.0);
        Physical mk2 = new FoodSource(new Vector2D(2.0, 2.0), 2.0);
        Physical mk3 = new Nest(new Vector2D(3.0, 3.0), 3.0);

        Physical mv1 = new FoodSource(new Vector2D(10.0, 10.0), 1.0);
        Physical mv2 = new Nest(new Vector2D(20.0, 20.0), 2.0);
        Physical mv3 = new FoodSource(new Vector2D(30.0, 30.0), 3.0);

        map1.put(mk1, mv1);
        map1.put(mk2, mv2);
        map1.put(mk3, mv3);

        map2.put(mk3, mv3);
        map2.put(mk1, mv1);
        map2.put(mk2, mv2);

        check("Equal maps", true, map1.equals(map2));
        check("Equal maps symmetric", true, map2.equals(map1));
        check("Equal maps hashCode", map1.hashCode(), map2.hashCode());

        map2.put(mk2, mv3);

        check("Maps with different value unequal", false, map1.equals(map2));

        map2.put(mk2, mv2);
        map2.put(new Nest(new Vector2D(4.0, 4.0), 4.0), mv1);

        check("Maps with different size unequal", false, map1.equals(map2));

        System.out.println("\n===============================================");
        System.out.println("PhysicalPhysicalTreeMap EQUALS / HASHCODE TESTS");
        System.out.println("===============================================");

        map1 = new PhysicalPhysicalTreeMap();
        map2 = new PhysicalPhysicalTreeMap();

        mk1 = new Nest(new Vector2D(1.0, 1.0), 1.0);
        mk2 = new FoodSource(new Vector2D(2.0, 2.0), 2.0);
        mk3 = new Nest(new Vector2D(3.0, 3.0), 3.0);

        mv1 = new FoodSource(new Vector2D(10.0, 10.0), 1.0);
        mv2 = new Nest(new Vector2D(20.0, 20.0), 2.0);
        mv3 = new FoodSource(new Vector2D(30.0, 30.0), 3.0);

        map1.put(mk1, mv1);
        map1.put(mk2, mv2);
        map1.put(mk3, mv3);

        map2.put(mk3, mv3);
        map2.put(mk1, mv1);
        map2.put(mk2, mv2);

        check("Equal maps", true, map1.equals(map2));
        check("Equal maps symmetric", true, map2.equals(map1));
        check("Equal maps hashCode", map1.hashCode(), map2.hashCode());

        map2.put(mk2, mv3);

        check("Maps with different value unequal", false, map1.equals(map2));

        map2.put(mk2, mv2);
        map2.put(new Nest(new Vector2D(4.0, 4.0), 4.0), mv1);

        check("Maps with different size unequal", false, map1.equals(map2));

        System.out.println("\n=========================================================================");
        System.out.println("PhysicalPhysicalTreeMap - PhysicalPhysicalHashMap EQUALS / HASHCODE TESTS");
        System.out.println("=========================================================================");

        map1 = new PhysicalPhysicalHashMap();
        map2 = new PhysicalPhysicalTreeMap();

        mk1 = new Nest(new Vector2D(1.0, 1.0), 1.0);
        mk2 = new FoodSource(new Vector2D(2.0, 2.0), 2.0);
        mk3 = new Nest(new Vector2D(3.0, 3.0), 3.0);

        mv1 = new FoodSource(new Vector2D(10.0, 10.0), 1.0);
        mv2 = new Nest(new Vector2D(20.0, 20.0), 2.0);
        mv3 = new FoodSource(new Vector2D(30.0, 30.0), 3.0);

        map1.put(mk1, mv1);
        map1.put(mk2, mv2);
        map1.put(mk3, mv3);

        map2.put(mk3, mv3);
        map2.put(mk1, mv1);
        map2.put(mk2, mv2);

        check("Equal maps", true, map1.equals(map2));
        check("Equal maps symmetric", true, map2.equals(map1));
        check("Equal maps hashCode", map1.hashCode(), map2.hashCode());

        map2.put(mk2, mv3);

        check("Maps with different value unequal", false, map1.equals(map2));

        map2.put(mk2, mv2);
        map2.put(new Nest(new Vector2D(4.0, 4.0), 4.0), mv1);

        check("Maps with different size unequal", false, map1.equals(map2));

        System.out.println("\n===============================================");
        System.out.println("ALL AB3.3 TESTS COMPLETED");
        System.out.println("===============================================");

        // TODO: end of block to activate */
    }

    private static void checkPhysical(
            String testName,
            Physical expected,
            Physical actual) {

        if (expected == actual) {
            ok();
        } else {
            fail(testName,
                    physicalToString(expected),
                    physicalToString(actual));
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

    private static void check(
            String testName,
            Object expected,
            Object actual) {

        if (java.util.Objects.equals(expected, actual)) {
            ok();
        } else {
            fail(testName, expected, actual);
        }
    }

    private static void fail(
            String testName,
            Object expected,
            Object actual) {

        System.out.println(
                testName
                        + " FAILED -> Expected: "
                        + expected
                        + ", Actual: "
                        + actual
        );
    }

    private static void ok() {

        System.out.println("OK");
    }
}