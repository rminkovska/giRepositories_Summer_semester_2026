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

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getPosition() {

        return position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getRadius() {

        return radius;
    }

    /**
     * Returns a string representation of this nest.
     *
     * <p>The returned string contains the class name, the position of the nest,
     * and its radius.</p>
     *
     * <p>Example:
     * {@code Nest@(100.0, 200.0), r=12.0}</p>
     *
     * @return a string representation of this nest
     */
    @Override
    public String toString() {
        return "Nest@("
                + getPosition().getX() + ", "
                + getPosition().getY() + "), r="
                + getRadius();
    }
}