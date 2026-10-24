/**
 * Physical nest object.
 */
public class Nest implements Physical {

    private final Vector2D position;
    private final double radius;

    /**
     * Creates a nest with the specified position and radius.
     *
     * @param position the nest position; {@code position != null}
     * @param radius the radius of the nest; {@code radius >= 0.0}
     */
    public Nest(Vector2D position, double radius) {

        this.position = position;
        this.radius = radius;
    }

    @Override
    public Vector2D getPosition() {

        return position;
    }

    @Override
    public double getRadius() {

        return radius;
    }
}