import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ApplicationAB4d2 {

    public static void main(String[] args) {

        testExceptions();
        testLogger();
        testReadCollisionsPerStep();
        testWorldValidation();
        testTravelledDistancesCopy();

        System.out.println("\nALL AB4.2 TESTS COMPLETED");
    }

    private static void testExceptions() {

        Physical obstacle = new Nest(new Vector2D(100, 100), 10);

        UnresolvedCollisionException e =
                new UnresolvedCollisionException(7, null, obstacle);

        check("UnresolvedCollisionException step", 7, e.getStep());
        check("UnresolvedCollisionException obstacle", obstacle, e.getObstacle());

        ArrayList<UnresolvedCollisionException> collisions = new ArrayList<>();
        collisions.add(e);

        SimulationStepException se =
                new SimulationStepException(7, collisions);

        collisions.clear();

        check("SimulationStepException step", 7, se.getStep());
        check("SimulationStepException collision count", 1, se.collisionCount());
        check("SimulationStepException defensive copy", 1, se.getCollisions().size());
    }

    private static void testLogger() {

        try {
            String fileName = "ab4d2-test-log.txt";
            new File(fileName).delete();

            SimulationLogger logger = new SimulationLogger(fileName);

            ArrayList<UnresolvedCollisionException> collisions = new ArrayList<>();

            collisions.add(new UnresolvedCollisionException(
                    1,
                    null,
                    new Nest(new Vector2D(100, 100), 10)
            ));

            collisions.add(new UnresolvedCollisionException(
                    1,
                    null,
                    new FoodSource(new Vector2D(200, 200), 10)
            ));

            logger.log(new SimulationStepException(1, collisions));

            check("Logger failed step count", 1, logger.failedStepCount());
            check("Logger unresolved collision count", 2, logger.unresolvedCollisionCount());

            logger.close();

        } catch (IOException e) {
            fail("Logger IOException", "no exception", e.getMessage());
        }
    }

    private static void testReadCollisionsPerStep() {

        try {
            String fileName = "ab4d2-read-test-log.txt";
            new File(fileName).delete();

            SimulationLogger logger = new SimulationLogger(fileName);

            ArrayList<UnresolvedCollisionException> c1 = new ArrayList<>();
            c1.add(new UnresolvedCollisionException(3, null, null));
            c1.add(new UnresolvedCollisionException(3, null, null));

            ArrayList<UnresolvedCollisionException> c2 = new ArrayList<>();
            c2.add(new UnresolvedCollisionException(5, null, null));

            logger.log(new SimulationStepException(3, c1));
            logger.log(new SimulationStepException(5, c2));
            logger.close();

            Map<Integer, Integer> result =
                    SimulationLogger.readCollisionsPerStep(fileName);

            check("Read collisions step 3", 2, result.get(3));
            check("Read collisions step 5", 1, result.get(5));

        } catch (IOException e) {
            fail("Read IOException", "no exception", e.getMessage());
        }
    }

    private static void testWorldValidation() {

        try {
            new World(
                    800,
                    600,
                    50,
                    new Nest(new Vector2D(-100, 100), 30),
                    new FoodSource(new Vector2D(650, 450), 30)
            );

            fail("World validation", "IllegalArgumentException", "no exception");

        } catch (IllegalArgumentException e) {
            ok("World validation");
        }
    }

    private static void testTravelledDistancesCopy() {

        World world = new World(
                800,
                600,
                2,
                new Nest(new Vector2D(100, 100), 30),
                new FoodSource(new Vector2D(650, 450), 30)
        );

        Map<Ant, Double> distances = world.getTravelledDistances();

        int originalSize = distances.size();
        distances.clear();

        check(
                "Travelled distances defensive copy",
                originalSize,
                world.getTravelledDistances().size()
        );
    }

    private static void check(String testName, Object expected, Object actual) {

        if (java.util.Objects.equals(expected, actual)) {
            ok(testName);
        } else {
            fail(testName, expected, actual);
        }
    }

    private static void fail(String testName, Object expected, Object actual) {

        System.out.println("[FAIL] " + testName
                + " -> Expected: "
                + expected
                + ", Actual: "
                + actual);
    }

    private static void ok(String testName) {

        System.out.println("[OK]   " + testName);
    }
}