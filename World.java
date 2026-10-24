/**
 * Simulation world containing nest and food location.
 *
 * <p>The world is represented as a two-dimensional coordinate system.
 * Ants can check whether a position corresponds to the nest or
 * the food source.</p>
 */
public class World {

    // TODO: all variables and additional methods are private.

    /**
     * Creates a new `World` object with the specified width, height and positions of
     * ant nest and food source. Valid positions range from `(0.0,0.0)` to `(width, height)`.
     *
     * @param width the width of the world; {@code width > 0}
     * @param height the height of the world; {@code height > 0}
     * @param nest the position of the ant nest; {@code nest != null}, coordinates have to be within world boundaries
     * @param food the position of the food source; {@code food != null}, coordinates have to be within world boundaries
     * @param ants the array used to store the ants of this world.
     * The length of this array determines the number of ants that will live in this world;
     * {@code ants != null && ants.length > 0}
     */
    int width;
    int height;
    Vector2D nest;
    Vector2D food;
    Ant[] ants;

    public World(int width, int height, Vector2D nest, Vector2D food, Ant[] ants) {

        //TODO: implement constructor.
        this.width = width;
        this.height = height;
        this.nest = nest;
        this.food = food;
        this.ants = ants;
    }

    /**
     * Return the position of the nest.
     *
     * @return the position of the nest.
     */
    public Vector2D getNest() {

        //TODO: implement method.
        return new Vector2D(nest.getX(), nest.getY());
    }

    /**
     * Return the position of the food source.
     *
     * @return the position of the food source.
     */
    public Vector2D getFood() {

        //TODO: implement method.
        return new Vector2D(food.getX(), food.getY());
    }

    /**
     * Applies the world's boundary rule to the given state as follows:
     *
     * <p>
     * If the position lies outside the world, it results in a new position corresponding to the
     * closest point on the boundary of the world and a velocity vector that is the negative of the
     * current velocity vector.
     * <p>
     * You are free to refine the specification.
     * </p>
     *
     * @param s the state to be adjusted; {@code s != null}
     * @return a new {@link State} whose position lies inside the world
     */
    public State enforceBoundary(State s) {

        //TODO: implement method.
        double x = s.getPosition().getX();
        double y = s.getPosition().getY();
        double vx = s.getVelocity().getX();
        double vy = s.getVelocity().getY();
        if (x < 0) {
            vx *= -1;
            x = 0;
        }
        if (x > this.width) {
            vx *= -1;
            x = this.width;
        }
        if (y < 0) {
            vy *= -1;
            y = 0;
        }
        if (y > this.height) {
            vy *= -1;
            y = this.height;
        }
        Vector2D pos = new Vector2D(x, y);
        Vector2D vel = new Vector2D(vx, vy);
        return new State(pos, vel);
    }

    /**
     * Returns the current distribution of ants in the world, represented by a
     * map with as many entries as there are ants in the world.
     *
     * <p>
     * The returned association maps positions in the world to the ants
     * currently located at those positions. Each key represents a
     * {@link Vector2D} position and the associated value is the
     * {@link Ant} located at that position.
     * </p>
     *
     * <p>
     * The returned structure contains one entry for every ant currently
     * present in the world.
     * </p>
     *
     * <p>
     * {@code this} is not changed by this method.
     * </p>
     *
     * @return a {@link Vector2DAntTreeMap} representing the mapping
     * from positions to ants in the world
     */
    public Vector2DAntTreeMap asMap() {

        //TODO: implement method.
        Vector2DAntTreeMap map = new Vector2DAntTreeMap();

        Vector2D[] position = new Vector2D[ants.length];
        Ant[] value = new Ant[ants.length];

        for (int i = 0; i < ants.length; i++) {
            position[i] = ants[i].getPosition();
            value[i] = ants[i];
            map.put(position[i], value[i]);
        }

        return map;
    }
}