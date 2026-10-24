import java.util.*;

/**
 * Single ant agent.
 *
 * <p>An ant moves through the world by combining its current direction,
 * a random turn, and a weak attraction towards its current target.</p>
 */
public class Ant implements Physical, Steppable {

    private static final Random R = new Random();
    private static final double RADIUS = 4.0;

    private State state;
    private boolean carryingFood;
    private final World world;

    /**
     * Creates a new ant at the position of the nest of the specified world.
     *
     * @param world the simulation world; {@code world != null}
     */
    public Ant(World world) {
        this.world = world;
        this.state = new State(world.getNest().getPosition(), randomDirection());
        this.carryingFood = false;
    }

    /**
     * Performs one simulation step.
     *
     * @throws UnresolvedCollisionException if this ant cannot resolve a
     *         collision within the maximum number of correction attempts
     */
    @Override
    public void step() throws UnresolvedCollisionException {

        //TODO: adapt method according to `angabe.md` if needed.

        Physical target = carryingFood ? world.getNest() : world.getFood();

        Vector2D randomTurn = randomDirection().scale(0.30);

        Vector2D directionToTarget = target.getPosition()
                .subtract(state.getPosition())
                .normalize()
                .scale(0.10);

        Vector2D newDirection = state.getVelocity()
                .scale(0.60)
                .add(randomTurn)
                .add(directionToTarget);

        if (newDirection.isZero()) {
            newDirection = randomDirection();
        } else {
            newDirection = newDirection.normalize();
        }

        Vector2D nextPosition = state.getPosition().add(newDirection);

        nextPosition = avoidIntersections(nextPosition, target);

        state = world.enforceBoundary(new State(nextPosition, newDirection));

        List<UnresolvedCollisionException> collisions = new ArrayList<>();
        try {
            this.step();
        } catch (UnresolvedCollisionException e) {
            collisions.add(e);
        }

        if (!carryingFood && this.intersects(world.getFood())) {
            carryingFood = true;
        } else if (carryingFood && this.intersects(world.getNest())) {
            carryingFood = false;
        }
    }

    /**
     * Adjusts the specified candidate position so that this ant does not overlap
     * with blocking physical objects in the world.
     *
     * <p>The current target may be intersected. If no valid position can be
     * found within the maximum number of correction attempts, an
     * {@link UnresolvedCollisionException} is thrown.</p>
     *
     * @param candidatePosition the intended next position
     * @param allowedTarget the object that may be intersected
     * @return a corrected position
     * @throws UnresolvedCollisionException if the collision cannot be resolved
     */
    private Vector2D avoidIntersections(
            Vector2D candidatePosition,
            Physical allowedTarget) throws UnresolvedCollisionException {

        //TODO: adapt method according to `angabe.md`.

        Vector2D adjusted = candidatePosition;
        int safetyCounter = 0;
        int maxCorrections = 500;
        Physical lastObstacle = null;

        while (safetyCounter < maxCorrections) {
            Physical collision = firstBlockingIntersection(adjusted, allowedTarget);
            if (collision == null) {
                return adjusted;
            }

            lastObstacle = collision;

            adjusted = moveOutside(adjusted, collision);
            adjusted = world.enforceBoundary(
                    new State(adjusted, state.getVelocity())
            ).getPosition();
            safetyCounter++;
        }

        if (safetyCounter >= maxCorrections) {
            throw new UnresolvedCollisionException(world.getCurrentStep(), this, lastObstacle);
        }

        return adjusted;
    }

    private Physical firstBlockingIntersection(
            Vector2D position,
            Physical allowedTarget) {

        Collection<Physical> physicals = world.getPhysicals();

        for (Physical other: physicals) {

            if (other == null || other == this || other == allowedTarget) {
                continue;
            }

            if (other.intersects(position, getRadius())) {
                return other;
            }
        }

        return null;
    }

    private Vector2D moveOutside(Vector2D position, Physical other) {

        Vector2D center = other.getPosition();
        Vector2D direction = position.subtract(center);

        if (direction.isZero()) {
            direction = randomDirection();
        } else {
            direction = direction.normalize();
        }

        double distance = getRadius() + other.getRadius() + 0.5;
        return center.add(direction.scale(distance));
    }

    private static Vector2D randomDirection() {
        Vector2D v;

        do {
            v = new Vector2D(R.nextDouble() * 2 - 1, R.nextDouble() * 2 - 1);
        } while (v.isZero());

        return v.normalize();
    }

    public boolean isCarryingFood() {
        return carryingFood;
    }

    @Override
    public Vector2D getPosition() {
        return state.getPosition();
    }

    @Override
    public double getRadius() {
        return RADIUS;
    }
}