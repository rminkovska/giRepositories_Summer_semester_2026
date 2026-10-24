/**
 * Represents the simulation world in which birds move.
 *
 * <p>
 * The world is defined as a rectangular region with width {@code w}
 * and height {@code h}. It applies boundary rules to states that
 * leave this region.
 * </p>
 */
public class World {

    //TODO: object variables and additional methods must be private.

    /**
     * Creates a new world with the given dimensions.
     *
     * @param w the width of the world; {@code w > 0}
     * @param h the height of the world; {@code h > 0}
     */
    private final int W;
    private final int H;


    public World(int w, int h) {

        //TODO: implement constructor.
        this.W = w;
        this.H = h;
    }


    /**
     * Applies the world's boundary rule to the given state.
     *
     * <p>
     * If the position lies outside the world, it is wrapped to the
     * opposite side (torus topology). The velocity remains unchanged
     * (see also {@link ApplicationVector2D}).
     * </p>
     *
     * @param s the state to be adjusted; {@code s != null}
     * @return a new {@link State} whose position lies inside the world
     */
    public State enforceBoundary(State s) {
        //TODO: implement method.
        double x = s.getPosition().getX();
        double y = s.getPosition().getY();

        if (x < 0) x += W;
        if (y < 0) y += H;
        if (x >= W) x -= W;
        if (y >= H) y -= H;

        Vector2D velocity = s.getVelocity();
        Vector2D position = new Vector2D(x, y);

        return new State(position, velocity);
    }
}