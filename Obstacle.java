/**
 * A static physical obstacle in the two-dimensional simulation world.
 *
 * <p>An {@code Obstacle} represents an immovable object with a fixed position
 * and a circular spatial extent. Obstacles can be used to model walls,
 * barriers, or regions that agents (such as ants) should avoid or cannot
 * pass through.</p>
 */
public class Obstacle //implements Physical //TODO: activate clause
{

    //TODO: define missing parts of this class.
    private Vector2D position;
    private double radius;

    /**
     * Creates a new obstacle with the specified position and radius.
     *
     * @param position the center position of the obstacle;
     *                 {@code position != null}
     * @param radius the radius of the obstacle;
     *               {@code radius >= 0.0}
     */
    public Obstacle(Vector2D position, double radius) {

        //TODO: implement constructor.
        this.position= position;
        this.radius = radius;
    }
}