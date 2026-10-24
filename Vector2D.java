/**
 * The class {@code Vector2D} represents a two-dimensional mathematical vector.
 *
 * <p>Instances of this class are immutable. All vector operations return
 * new {@code Vector2D} objects and do not modify {@code this}.</p>
 *
 * Please do not change this file!
 */
public class Vector2D {

    private final double x;
    private final double y;

    /**
     * Creates a new two-dimensional vector.
     *
     * @param x the x-coordinate of the vector
     * @param y the y-coordinate of the vector
     */
    public Vector2D(double x, double y) {

        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of this vector.
     *
     * @return the x component
     */
    public double getX() {

        return x;
    }

    /**
     * Returns the y-coordinate of this vector.
     *
     * @return the y component
     */
    public double getY() {

        return y;
    }

    /**
     * Returns the vector sum of this vector and another vector.
     *
     * @param other the vector to add; {@code other != null}
     * @return a new {@code Vector2D} representing {@code this + other}
     */
    public Vector2D add(Vector2D other) {

        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    /**
     * Returns the vector difference between this vector and another vector.
     *
     * @param other the vector to subtract; {@code other != null}
     * @return a new {@code Vector2D} representing {@code this - other}
     */
    public Vector2D subtract(Vector2D other) {

        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    /**
     * Returns a scaled version of this vector.
     *
     * @param factor the scaling factor
     * @return a new {@code Vector2D} representing this vector multiplied by {@code factor}
     */
    public Vector2D scale(double factor) {

        return new Vector2D(this.x * factor, this.y * factor);
    }

    /**
     * Returns the Euclidean length (magnitude) of this vector.
     *
     * @return the vector length
     */
    public double length() {

        return Math.sqrt(x * x + y * y);
    }

    /**
     * Computes the Euclidean distance between this vector and another vector.
     *
     * @param other the other vector; {@code other != null}
     * @return the distance between the two vectors
     */
    public double distanceTo(Vector2D other) {

        return this.subtract(other).length();
    }

    /**
     * Checks whether this vector is approximately the zero vector.
     *
     * <p>Due to floating-point precision, the vector is considered zero if its
     * length is smaller than a small threshold.</p>
     *
     * @return {@code true} if the vector is approximately zero, otherwise {@code false}
     */
    public boolean isZero() {

        return length() < 1e-12;
    }

    /**
     * Returns a normalized version of this vector or a new zero vector if {@code isZero() == true}.
     *
     * <p>The returned vector has length 1 and points in the same direction
     * as this vector. If {@code isZero() == true} a new vector (0, 0) is returned.</p>
     *
     * @return a normalized vector with unit length or a new vector (0, 0) if {@code isZero() == true}.
     */
    public Vector2D normalize() {

        double len = length();
        if (isZero()) {
            return new Vector2D(0,0);
        }
        return scale(1.0 / len);
    }

    /**
     * Converts this vector into an independent array representation.
     *
     * @return a new array containing {@code [x, y]}
     */
    public double[] toArray() {

        return new double[]{x, y};
    }
}