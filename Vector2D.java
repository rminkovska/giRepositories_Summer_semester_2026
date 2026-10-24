/**
 * The class {@code Vector2D} represents a two-dimensional mathematical vector.
 * <p>
 * TODO: Objects of this class are *immutable*,
 * i.e., they do not change their state after construction.
 */
public class Vector2D {
    // TODO: define this class such that it works with `ApplicationVector2D`.
    // TODO: define constructors.
    // TODO: write javadoc specifications for all methods.
    private final double x;
    private final double y;
    /**
     * Constructs a new {@code Vector2D} with the given x and y.
     *
     * @param x the x-coordinate of the vector;
     * @param y the y-coordinate of the vector;
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Returns the x-coordinate of the vector;
     *
     * @return the x-coordinate of the vector;
     */
    public double getX() {
        return x;
    }
    /**
     * Returns the y-coordinate of the vector;
     *
     * @return the y-coordinate of the vector;
     */
    public double getY() {
        return y;
    }

    /**
     * Returns the sum of this vector and another vector;
     *
     * @param other the vector to add
     * @return a new {@code Vector2D} which is the sum (never {@code null})
     */
    public Vector2D add(Vector2D other) {
        return new Vector2D(x + other.x, y + other.y);
    }
    /**
     * Returns the difference of this vector and another vector;
     *
     * @param other the vector to subtract
     * @return a new {@code Vector2D} which is the difference (never {@code null})
     */
    public Vector2D subtract(Vector2D other) {
        return new Vector2D(x - other.x, y - other.y);
    }
    /**
     * Returns the scaled with a factor vector;
     *
     * @param factor the multiplier of the components;
     * @return the vector scaled by the given factor (never {@code null});
     */
    public Vector2D scale(double factor) {
        return new Vector2D(x * factor, y * factor);
    }
    /**
     *
     * Returns the Euclidean length of the vector;
     *
     * @return the Euclidean length of the vector ;
     */
    public double length() {
        return Math.sqrt(x * x + y * y);
    }
    /**
     * Returns a normalized vector;
     *
     * If the vector has a length of 0, a zero-vector is returned to
     * avoid division by zero.
     *
     * @return a normalized {@code Vector2D} (never {@code null});
     */
    public Vector2D normalize() {
        if (length()==0) return new Vector2D(0, 0);
        return new Vector2D(x / length(), y / length());
    }

}