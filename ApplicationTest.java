public class ApplicationTest {

    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("Vector2DStack TESTS");
        System.out.println("===============================================");

        Vector2DStack stack1 = new Vector2DStack();
        Vector2D v1 = new Vector2D(1.0, 2.0);
        Vector2D v2 = new Vector2D(3.0, 4.0);

        stack1.push(v1);
        check("Vector2DStack push", 1, stack1.size());

        stack1.push(v2);
        check("Vector2DStack push second", 2, stack1.size());

        Vector2D popped = stack1.pop();
        check("Vector2DStack pop", true, popped != null);
        check("Vector2DStack pop value", 3.0, popped.getX());
        check("Vector2DStack pop y value", 4.0, popped.getY());
        check("Vector2DStack size after pop", 1, stack1.size());

        Vector2D v3 = new Vector2D(5.0, 6.0);
        Vector2D v4 = new Vector2D(7.0, 8.0);
        Vector2D v5 = new Vector2D(9.0, 10.0);
        stack1.push(v3);
        stack1.push(v4);
        stack1.push(v5);
        check("Vector2DStack size after multiple pushes", 4, stack1.size());

        Vector2D top = stack1.peek();
        check("Vector2DStack peek", true, top != null);
        check("Vector2DStack peek x value", 9.0, top.getX());
        check("Vector2DStack peek y value", 10.0, top.getY());
        check("Vector2DStack peek preserves size", 4, stack1.size());

        Vector2DStack expandStack = new Vector2DStack();
        expandStack.push(new Vector2D(1.0, 1.0));
        expandStack.push(new Vector2D(2.0, 2.0));
        expandStack.push(new Vector2D(3.0, 3.0));
        check("Vector2DStack capacity expansion", 3, expandStack.size());

        check("Vector2DStack isEmpty false", false, stack1.isEmpty());

        stack1.clear();
        check("Vector2DStack isEmpty after clear", true, stack1.isEmpty());
        check("Vector2DStack size after clear", 0, stack1.size());

        System.out.println("\n===============================================");
        System.out.println("AntQueue TESTS");
        System.out.println("===============================================");

        AntQueue queue1 = new AntQueue(4);
        World world1 = new World(200, 200, new Vector2D(0, 0), new Vector2D(0, 0), queue1);

        Ant a1 = new Ant(world1);
        Ant a2 = new Ant(world1);

        queue1.add(a1);
        check("AntQueue add", 1, queue1.size());

        queue1.add(a2);
        check("AntQueue add second", 2, queue1.size());

        Ant polled = queue1.poll();
        check("AntQueue poll", true, polled != null);
        check("AntQueue size after poll", 1, queue1.size());

        Ant a3 = new Ant(world1);
        Ant a4 = new Ant(world1);
        Ant a5 = new Ant(world1);
        queue1.add(a3);
        queue1.add(a4);
        queue1.add(a5);
        check("AntQueue size after multiple adds", 4, queue1.size());

        Ant first = queue1.poll();
        check("AntQueue poll value", a2, first);

        Ant peeked = queue1.peek();
        check("AntQueue peek", true, peeked != null);
        check("AntQueue peek value", a3, peeked);
        check("AntQueue peek preserves size", 3, queue1.size());

        AntQueue expandQueue = new AntQueue(2);
        expandQueue.add(new Ant(world1));
        expandQueue.add(new Ant(world1));
        expandQueue.add(new Ant(world1));
        check("AntQueue capacity expansion", 3, expandQueue.size());

        System.out.println("\n===============================================");
        System.out.println("Vector2DAntMap TESTS");
        System.out.println("===============================================");

        Vector2DAntMap map1 = new Vector2DAntMap();
        World world2 = new World(200, 200, new Vector2D(0, 0), new Vector2D(0, 0), queue1);

        Ant ant1 = new Ant(world2);
        Ant ant2 = new Ant(world2);
        Vector2D key1 = new Vector2D(1.0, 2.0);
        Vector2D key2 = new Vector2D(3.0, 4.0);

        map1.put(key1, ant1);
        check("Vector2DAntMap size after put", 1, map1.size());

        check("Vector2DAntMap containsKey true", true, map1.containsKey(key1));
        check("Vector2DAntMap containsKey false", false, map1.containsKey(key2));

        check("Vector2DAntMap containsValue true", true, map1.containsValue(ant1));
        check("Vector2DAntMap containsValue false", false, map1.containsValue(ant2));

        Ant retrieved = map1.get(key1);
        check("Vector2DAntMap get", ant1, retrieved);

        map1.put(key2, ant2);
        check("Vector2DAntMap size after second put", 2, map1.size());

        Ant oldValue = map1.put(key1, ant2);
        check("Vector2DAntMap put existing key returns old value", true, oldValue != null);
        check("Vector2DAntMap put existing key value", ant1, oldValue);
        check("Vector2DAntMap size after replace", 2, map1.size());

        Vector2DStack keysStack = map1.keys();
        check("Vector2DAntMap keys stack size", 2, keysStack.size());

        AntQueue valuesQueue = map1.values();
        check("Vector2DAntMap values queue size", 2, valuesQueue.size());

        Ant removed = map1.remove(key2);
        check("Vector2DAntMap remove existing key", true, removed != null);
        check("Vector2DAntMap size after remove", 1, map1.size());
        check("Vector2DAntMap remove nonexistent key", null, map1.remove(new Vector2D(99, 99)));

        check("Vector2DAntMap null key: nonexistent before put", false, map1.containsKey(null));
        map1.put(null, removed);
        check("Vector2DAntMap null key: stack size", 2, map1.size());
        check("Vector2DAntMap null key: exists after put", true, map1.containsKey(null));

        System.out.println("\n===============================================");
        System.out.println("ALL TESTS COMPLETED");
        System.out.println("===============================================");
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