/**
 * Physical food source object.
 */
public class FoodSource implements Physical {

    private final Vector2D position;
    private final double radius;

    // TODO: override `equals` and `hashCode`

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodSource)) return false;
        FoodSource other = (FoodSource) o;
        if (Double.compare(other.radius, radius) != 0) return false;
        return position != null ? position.equals(other.position) : other.position == null;
    }
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(radius);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
    /**
     * Creates a food source with the specified position and radius.
     *
     * @param position the food position; {@code position != null}
     * @param radius   the radius of the food source; {@code radius >= 0.0}
     */
    public FoodSource(Vector2D position, double radius) {

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
     * Returns a string representation of this food source.
     *
     * <p>The returned string contains the class name, the position of the food source,
     * and its radius.</p>
     *
     * <p>Example:
     * {@code FoodSource@(100.0, 200.0), r=3.0}</p>
     *
     * @return a string representation of this food source
     */
    @Override
    public String toString() {
        return "FoodSource@("
                + getPosition().getX() + ", "
                + getPosition().getY() + "), r="
                + getRadius();
    }
}