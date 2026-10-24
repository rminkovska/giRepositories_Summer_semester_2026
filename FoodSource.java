/**
 * Physical food source object.
 */
public class FoodSource implements Physical {

    private final Vector2D position;
    private final double radius;

    /**
     * Creates a food source with the specified position and radius.
     *
     * @param position the food position; {@code position != null}
     * @param radius the radius of the food source; {@code radius >= 0.0}
     */
    public FoodSource(Vector2D position, double radius) {

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