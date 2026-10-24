import java.util.Objects;

/**
 * Immutable value object representing the kinematic state of an object.
 *
 * A {@code State} consists of:
 * <ul>
 *   <li>a position vector</li>
 *   <li>a velocity vector</li>
 * </ul>
 *
 * <p>
 * This class is immutable: once created, its position and velocity
 * cannot be changed. Any modification of a bird's movement must be
 * expressed by creating a new {@code State} instance.
 * </p>
 *
 * Please do not change this file!
 */
public class State {

    private final Vector2D position;
    private final Vector2D velocity;

    /**
     * Constructs a new {@code State} with the given position and velocity.
     *
     * @param position the position vector; {@code position != null}
     * @param velocity the velocity vector; {@code velocity != null}
     */
    public State(Vector2D position, Vector2D velocity) {

        this.position = position;
        this.velocity = velocity;
    }

    /**
     * Returns the position component of this state.
     *
     * @return the position vector (never {@code null})
     */
    public Vector2D getPosition() {

        return position;
    }

    /**
     * Returns the velocity component of this state.
     *
     * @return the velocity vector (never {@code null})
     */
    public Vector2D getVelocity() {

        return velocity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof State)) {
            return false;
        }

        State state = (State) o;

        return Objects.equals(position, state.position)
                && Objects.equals(velocity, state.velocity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(position, velocity);
    }
}
