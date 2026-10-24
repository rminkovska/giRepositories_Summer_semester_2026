import codedraw.CodeDraw;
import java.awt.*;
import java.io.IOException;
import java.util.Collection;

/**
 * The class {@code ApplicationAnt} serves as an entry point for the simulation.
 */
public class ApplicationAnt {

    private static final int WORLD_WIDTH = 800;
    private static final int WORLD_HEIGHT = 600;

    private static final Vector2D NEST_POSITION = new Vector2D(100, 100);
    private static final Vector2D FOOD_POSITION = new Vector2D(650, 450);

    private static final int ANT_COUNT = 50;

    private static final double ANT_RADIUS = 4.0;
    private static final double NEST_RADIUS = 30.0;
    private static final double FOOD_RADIUS = 30.0;

    private static final int FRAME_DELAY_MS = 0;
    private static final int NUMBER_OF_STEPS = 2000;
    private static final String LOG_FILE_NAME = "simulation-log.txt";

    public static void main(String[] args) {

        SimulationLogger logger = null;

        try {
            logger = new SimulationLogger(LOG_FILE_NAME);

            CodeDraw cd = new CodeDraw(WORLD_WIDTH, WORLD_HEIGHT);

            Nest nest = new Nest(NEST_POSITION, NEST_RADIUS);
            FoodSource food = new FoodSource(FOOD_POSITION, FOOD_RADIUS);
            World world = new World(WORLD_WIDTH, WORLD_HEIGHT, ANT_COUNT, nest, food);

            // 4. Die Haupt-Simulationsschleife
            for (int i = 0; i < NUMBER_OF_STEPS; i++) {

                int newCollisions = 0;

                try {
                    world.step();
                } catch (SimulationStepException e) {
                    logger.log(e);
                    newCollisions = e.collisionCount();
                }

                drawWorld(
                        cd,
                        world,
                        logger.unresolvedCollisionCount(),
                        newCollisions,
                        "Simulation running..."
                );

                cd.show(FRAME_DELAY_MS);
            }

            drawWorld(cd, world, logger.unresolvedCollisionCount(), 0, "Simulation finished!");
            cd.show();

        } catch (IOException e) {
            System.err.println("Kritischer Fehler: Logger konnte nicht erstellt werden.");
            e.printStackTrace();
        } finally {
            if (logger != null) {
                logger.close();
            }
        }
    }

    /**
     * Draws the complete world state and collision statistics.
     *
     * @param cd the CodeDraw canvas; {@code cd != null}
     * @param world the simulation world; {@code world != null}
     * @param unresolvedCollisions the total number of unresolved collisions;
     *                             {@code unresolvedCollisions >= 0}
     * @param newCollisions the number of unresolved collisions in the current
     *                      simulation step; {@code newCollisions >= 0}
     * @param info additional textual information about the world
     */
    private static void drawWorld(
            CodeDraw cd,
            World world,
            int unresolvedCollisions,
            int newCollisions,
            String info) {

        cd.clear(Color.WHITE);

        drawNest(cd, world.getNest());
        drawFood(cd, world.getFood());
        drawAnts(cd, world.getAnts());
        drawStatistics(cd, unresolvedCollisions, newCollisions, world.getCurrentStep(), info);
    }

    /**
     * Draws collision statistics.
     *
     * @param cd the CodeDraw canvas; {@code cd != null}
     * @param unresolvedCollisions the total number of unresolved collisions;
     *                             {@code unresolvedCollisions >= 0}
     * @param newCollisions the number of unresolved collisions in the current
     *                      simulation step; {@code newCollisions >= 0}
     * @param step the current simulation step; {@code step >= 0}
     * @param info additional textual information
     */
    private static void drawStatistics(
            CodeDraw cd,
            int unresolvedCollisions,
            int newCollisions,
            int step,
            String info) {

        cd.setColor(Color.BLACK);
        cd.drawText(
                10,
                WORLD_HEIGHT - 80,
                "Unresolved collisions total: " + unresolvedCollisions
        );

        cd.drawText(
                10,
                WORLD_HEIGHT - 60,
                "Unresolved collisions in this step: " + newCollisions
        );
        cd.drawText(
                10,
                WORLD_HEIGHT - 40,
                "Steps: " + step
        );
        cd.drawText(
                10,
                WORLD_HEIGHT - 20,
                info
        );
    }

    /**
     * Draws the nest.
     *
     * @param cd the CodeDraw canvas; {@code cd != null}
     * @param nest the nest; {@code nest != null}
     */
    private static void drawNest(CodeDraw cd, Nest nest) {
        Vector2D p = nest.getPosition();
        cd.setColor(Color.BLUE);
        cd.fillCircle(p.getX(), p.getY(), nest.getRadius());
    }

    /**
     * Draws the food source.
     *
     * @param cd the CodeDraw canvas; {@code cd != null}
     * @param food the food source; {@code food != null}
     */
    private static void drawFood(CodeDraw cd, FoodSource food) {
        Vector2D p = food.getPosition();
        cd.setColor(Color.GREEN);
        cd.fillCircle(p.getX(), p.getY(), food.getRadius());
    }

    /**
     * Draws all ants from the specified collection.
     *
     * @param cd the CodeDraw canvas; {@code cd != null}
     * @param ants the ants; {@code ants != null}
     */
    private static void drawAnts(CodeDraw cd, Collection<Ant> ants) {
        for (Ant ant : ants) {
            drawAnt(cd, ant);
        }
    }

    /**
     * Draws a single ant.
     *
     * @param cd the CodeDraw canvas; {@code cd != null}
     * @param ant the ant to draw; {@code ant != null}
     */
    private static void drawAnt(CodeDraw cd, Ant ant) {
        Vector2D p = ant.getPosition();

        if (ant.isCarryingFood()) {
            cd.setColor(Color.RED);
        } else {
            cd.setColor(Color.BLACK);
        }

        cd.fillCircle(p.getX(), p.getY(), ANT_RADIUS);
    }
}