/**
 * Describes a physical object in the two-dimensional simulation world.
 *
 * <p>A physical object has
 * <ul>
 *   <li>a position in the world, and</li>
 *   <li>a non-negative spatial extent represented by a radius.</li>
 * </ul>
 *
 * <p>The position specifies the center of the object. The radius specifies
 * how far the object extends from its center in all directions. A radius
 * of {@code 0.0} means that the object is point-like.</p>
 *
 * <p>This interface is intended for objects that can participate in
 * geometric operations such as distance checks, intersection tests,
 * collision handling, or neighborhood queries.</p>
 *
 * <p>Implementations should ensure that repeated calls to the methods of this
 * interface return values that consistently describe the current physical state
 * of the object.</p>
 *
 * Please do not change this file!
 */
public interface Physical {

    /**
     * Returns the current position of this object.
     *
     * <p>The returned vector represents the center of the object in the
     * two-dimensional world.</p>
     *
     * @return the position of this object; never {@code null}
     */
    Vector2D getPosition();

    /**
     * Returns the radius of this object.
     *
     * <p>The radius describes the spatial extent of the object around its
     * center position. The returned value must not be negative.</p>
     *
     * @return the radius of this object; {@code getRadius() >= 0.0}
     */
    double getRadius();

    /**
     * Returns whether this object fully contains the specified physical object.
     *
     * <p>This is the case if the entire circle of {@code other} lies inside or on
     * the boundary of this object. Formally, the distance between the centers plus
     * the radius of {@code other} must be less than or equal to the radius of this object.</p>
     *
     * @param other the other physical object; {@code other != null}
     * @return {@code true} if {@code other} is fully contained in {@code this},
     *         {@code false} otherwise
     */
    default boolean fullyContains(Physical other) {

        return getPosition().distanceTo(other.getPosition())
                + other.getRadius()
                <= getRadius();
    }

    /**
     * Returns whether this object intersects the specified physical object.
     *
     * <p>Two physical objects intersect if the distance between their centers
     * is less than or equal to the sum of their radii.</p>
     *
     * @param other the other physical object; {@code other != null}
     * @return {@code true} if the two objects intersect, {@code false} otherwise
     */
    default boolean intersects(Physical other) {

        return getPosition().distanceTo(other.getPosition())
                <= getRadius() + other.getRadius();
    }

    /**
     * Returns whether this object intersects the specified physical object
     * without one fully containing the other.
     *
     * <p>This is the case if the two circles overlap partially:
     * they intersect, but neither circle lies completely inside the other.</p>
     *
     * @param other the other physical object; {@code other != null}
     * @return {@code true} if the objects intersect without full containment,
     *         {@code false} otherwise
     */
    default boolean intersectsWithoutContainment(Physical other) {

        return intersects(other)
                && !fullyContains(other)
                && !other.fullyContains(this);
    }

    /**
     * Returns whether a circle at the specified position with the specified radius
     * intersects {@code this}.
     *
     * <p>This method can be used to test a hypothetical physical object without
     * a {@code Physical} instance. {@code this} and the hypothetical physical object (the circle)
     * intersect if the distance between their centers is less than or equal to the sum of their radii.</p>
     *
     * @param position the center position of the hypothetical object;
     *                 {@code position != null}
     * @param radius the radius of the hypothetical object; {@code radius >= 0.0}
     * @return {@code true} if the hypothetical object intersects {@code this},
     *         {@code false} otherwise
     */
    default boolean intersects(Vector2D position, double radius) {

        return position.distanceTo(this.getPosition())
                <= radius + this.getRadius();
    }


    /**
     * Returns whether this physical object is equal to the specified object.
     *
     * <p>Two physical objects are considered equal iff</p>
     * <ul>
     *   <li>they have the same runtime type,</li>
     *   <li>their center positions have equal x- and y-coordinates, and</li>
     *   <li>they have equal radii.</li>
     * </ul>
     *
     * <p>Thus two distinct objects may be equal if they describe the same
     * geometric physical object.</p>
     *
     * @param o the object to compare with
     * @return {@code true} iff this object is equal to {@code o}
     */
    @Override
    boolean equals(Object o);

    /**
     * Returns a hash code consistent with the definition of equality.
     *
     * @return a hash code for this object
     */
    @Override
    int hashCode();

}