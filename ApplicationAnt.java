import codedraw.CodeDraw;

import java.awt.*;

/**
 * The class {@code ApplicationAnt} serves as an entry point for the simulation.
 * <p>
 * TODO: change this class according to 'angabe.md'.
 */
public class ApplicationAnt {

    private static final int WORLD_WIDTH = 800;
    private static final int WORLD_HEIGHT = 600;

    private static final Vector2D NEST_POSITION = new Vector2D(100, 100);
    private static final Vector2D FOOD_POSITION = new Vector2D(650, 450);

    private static final int ANT_COUNT = 40;

    private static final double ANT_RADIUS = 4.0;
    private static final double NEST_RADIUS = 12.0;
    private static final double FOOD_RADIUS = 12.0;

    //TODO: define more variables if needed.

    public static void main(String[] args) {

        //TODO: implement a simulation according to `angabe.md`.
        CodeDraw cd = new CodeDraw(WORLD_WIDTH, WORLD_HEIGHT);

        Ant[] ants = new Ant[ANT_COUNT];
        World antsWorld = new World(WORLD_WIDTH, WORLD_HEIGHT, NEST_POSITION, FOOD_POSITION, ants);
        for (int i = 0; i < ANT_COUNT; i++) {
            ants[i] = new Ant(antsWorld);
        }

        while (!cd.isClosed()) {
            cd.clear();

            for (int i = 0; i < ANT_COUNT; i++) {
                ants[i].step();
            }

            cd.setColor(Color.BLUE);
            cd.fillCircle(NEST_POSITION.getX(), NEST_POSITION.getY(), NEST_RADIUS);

            cd.setColor(Color.GREEN);
            cd.fillCircle(FOOD_POSITION.getX(), FOOD_POSITION.getY(), FOOD_RADIUS);

            for (int i = 0; i < ANT_COUNT; i++) {
                Ant thisAnt = ants[i];
                Vector2D thisPosition = ants[i].getPosition();

                if (thisAnt.isCarryingFood()) {
                    cd.setColor(Color.RED);
                }
                else cd.setColor(Color.BLACK);
                cd.fillCircle(thisPosition.getX(), thisPosition.getY(), ANT_RADIUS);

            }

            cd.show();
        }
    }
}