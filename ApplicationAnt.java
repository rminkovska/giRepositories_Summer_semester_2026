import codedraw.CodeDraw;
import java.awt.*;

/**
 * The class {@code ApplicationAnt} serves as an entry point for the simulation.
 */
public class ApplicationAnt {

    private static final int WORLD_WIDTH = 800;
    private static final int WORLD_HEIGHT = 600;

    private static final Vector2D NEST_POSITION = new Vector2D(100, 100);
    private static final Vector2D FOOD_POSITION = new Vector2D(650, 450);

    private static final int ANT_COUNT = 300;

    private static final double ANT_RADIUS = 4.0;
    private static final double NEST_RADIUS = 12.0;
    private static final double FOOD_RADIUS = 12.0;

    private static final int FRAME_DELAY_MS = 0;

    public static void main(String[] args) {

        Ant[] ants = new Ant[ANT_COUNT];

        World world = new World(
                WORLD_WIDTH,
                WORLD_HEIGHT,
                new Nest(NEST_POSITION, NEST_RADIUS),
                new FoodSource(FOOD_POSITION, FOOD_RADIUS),
                ants,
                new Obstacle[]{} // add obstacles here
        );

        initializeAnts(world, ants);

        CodeDraw cd = new CodeDraw(WORLD_WIDTH, WORLD_HEIGHT);

        while (true) {
            simulateStep(ants);
            drawWorld(cd, world);
            cd.show(FRAME_DELAY_MS);
        }
    }

    /**
     * Creates ants and inserts them into the array.
     *
     * @param world the world
     * @param ants the array storing all ants
     */
    private static void initializeAnts(World world, Ant[] ants) {
        for (int i = 0; i < ants.length; i++) {
            ants[i] = new Ant(world);
        }
    }

    /**
     * Executes one simulation step for all ants.
     *
     * @param ants the ants
     */
    private static void simulateStep(Ant[] ants) {
        for (int i = 0; i < ants.length; i++) {
            if (ants[i] != null) {
                ants[i].step();
            }
        }
    }

    /**
     * Draws the complete world state.
     *
     * @param cd the CodeDraw canvas
     * @param world the simulation world
     */
    private static void drawWorld(CodeDraw cd, World world) {
        cd.clear(Color.WHITE);

        drawNest(cd, world.getNest());
        drawFood(cd, world.getFood());
        drawAnts(cd, world.getAnts());
    }

    /**
     * Draws the nest.
     *
     * @param cd the CodeDraw canvas
     * @param nest the nest
     */
    private static void drawNest(CodeDraw cd, Nest nest) {
        Vector2D p = nest.getPosition();
        cd.setColor(Color.BLUE);
        cd.fillCircle(p.getX(), p.getY(), nest.getRadius());
    }

    /**
     * Draws the food source.
     *
     * @param cd the CodeDraw canvas
     * @param food the food source
     */
    private static void drawFood(CodeDraw cd, FoodSource food) {
        Vector2D p = food.getPosition();
        cd.setColor(Color.GREEN);
        cd.fillCircle(p.getX(), p.getY(), food.getRadius());
    }

    /**
     * Draws all ants from the array.
     *
     * @param cd the CodeDraw canvas
     * @param ants the ants
     */
    private static void drawAnts(CodeDraw cd, Ant[] ants) {
        for (int i = 0; i < ants.length; i++) {
            if (ants[i] != null) {
                drawAnt(cd, ants[i]);
            }
        }
    }

    /**
     * Draws a single ant.
     *
     * @param cd the CodeDraw canvas
     * @param ant the ant to draw
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