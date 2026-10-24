/**
 * Simulation world containing nest, food location, ants and obstacles.
 *
 * <p>The world is represented as a two-dimensional coordinate system.
 * It provides access to all physical objects currently present in the world
 * and applies the boundary rule to states.</p>
 */
public class World {

    private final int width;
    private final int height;
    private final Nest nest;
    private final FoodSource food;
    private final Ant[] ants;
    private final Obstacle[] obstacles;

    /**
     * Creates a new {@code World} object with the specified width, height,
     * nest, food source, ant array and obstacle array.
     *
     * @param width the width of the world; {@code width > 0}
     * @param height the height of the world; {@code height > 0}
     * @param nest the nest; {@code nest != null}
     * @param food the food source; {@code food != null}
     * @param ants the array used to store the ants of this world;
     *             {@code ants != null && ants.length > 0}
     * @param obstacles the obstacles of this world; {@code obstacles != null}
     */
    public World(int width, int height, Nest nest, FoodSource food, Ant[] ants, Obstacle[] obstacles) {

        this.width = width;
        this.height = height;
        this.nest = nest;
        this.food = food;
        this.ants = ants;
        this.obstacles = obstacles;
    }

    /**
     * Returns the position of the nest object.
     *
     * @return the nest
     */
    public Nest getNest() {

        return nest;
    }

    /**
     * Returns the food source object.
     *
     * @return the food source
     */
    public FoodSource getFood() {

        return food;
    }

    /**
     * Returns the ant array used by this world.
     *
     * @return the ants
     */
    public Ant[] getAnts() {

        return ants;
    }

    /**
     * Returns all physical objects currently present in the world.
     *
     * <p>The returned array contains
     * <ul>
     *   <li>the nest,</li>
     *   <li>the food source,</li>
     *   <li>all non-{@code null} obstacles, and</li>
     *   <li>all non-{@code null} ants.</li>
     * </ul>
     * Each non-{@code null} physical object appears exactly once.</p>
     *
     * <p>The returned array is independent of the internal representation of
     * this world. Changes to the returned array do not affect {@code this}.</p>
     *
     * @return an array containing all physical objects in this world
     */
    public Physical[] getPhysicals() {

        //TODO: implement method.
        int i=0;
        int size = 2; //beginning with 2 for nest and food
        for (Obstacle obstacle : obstacles) {
            if(obstacle!=null) size++;
        }
        for(Ant ant : ants){
            if(ant!=null) size++;
        }
        Physical[] physicals = new Physical[size];

        physicals[i++]= nest;
        physicals[i++]= food;

        for(Obstacle obstacle : obstacles) {
            if(obstacle!=null){
                physicals[i++]= (Physical) obstacle;
            }
        }
        for(Ant ant : ants) {
            if(ant!=null){
                physicals[i++]= ant;
            }
        }

        return physicals;
    }

    /**
     * Applies the world's boundary rule to the given state.
     *
     * <p>If the position lies outside the world, it results in a new position
     * corresponding to the closest point on the boundary of the world and
     * a velocity vector whose affected component is inverted.</p>
     *
     * @param s the state to be adjusted; {@code s != null}
     * @return a new {@link State} whose position lies inside the world
     */
    public State enforceBoundary(State s) {

        Vector2D p = s.getPosition();
        Vector2D v = s.getVelocity();

        double x = p.getX();
        double y = p.getY();

        boolean bouncedX = false;
        boolean bouncedY = false;

        if (x < 0) {
            x = 0;
            bouncedX = true;
        } else if (x > width) {
            x = width;
            bouncedX = true;
        }

        if (y < 0) {
            y = 0;
            bouncedY = true;
        } else if (y > height) {
            y = height;
            bouncedY = true;
        }

        double vx = v.getX();
        double vy = v.getY();

        if (bouncedX) {
            vx = -vx;
        }
        if (bouncedY) {
            vy = -vy;
        }

        Vector2D newVelocity = new Vector2D(vx, vy);
        if (newVelocity.isZero()) {
            newVelocity = new Vector2D(1, 0);
        } else {
            newVelocity = newVelocity.normalize();
        }

        return new State(new Vector2D(x, y), newVelocity);
    }
}