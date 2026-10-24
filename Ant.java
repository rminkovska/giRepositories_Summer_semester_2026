import java.util.Random;

/**
 * Single ant agent.
 * <p>
 * An ant moves through the world by combining its current direction,
 * a random turn, and a weak attraction towards its current target.
 * <p>
 * If it is not carrying food, the target is the food source.
 * If it is carrying food, the target is the nest.
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
     * @param world the simulation world the ant operates in; {@code world != null}
     */
    public Ant(World world) {
        this.state = new State(world.getNest().getPosition(), randomDirection());
        this.world = world;
        this.carryingFood = false;
    }

    /**
     * Returns the current position of the ant.
     *
     * @return the current position
     */
    @Override
    public Vector2D getPosition() {
        return state.getPosition();
    }

    /**
     * Returns the radius of the ant.
     *
     * @return the radius
     */
    @Override
    public double getRadius() {
        return RADIUS;
    }

    /**
     * Indicates whether the ant is currently carrying food.
     *
     * @return {@code true} if this ant is carrying food, otherwise {@code false}
     */
    public boolean isCarryingFood() {
        return carryingFood;
    }

    /**
     * Performs one simulation step.
     *
     * <p>The ant moves by combining:</p>
     * <ul>
     *   <li>part of its previous direction,</li>
     *   <li>a random turn,</li>
     *   <li>and a weak attraction toward its current target.</li>
     * </ul>
     *
     * <p>If the ant is not carrying food, the target is the food source.
     * If the ant is carrying food, the target is the nest.</p>
     *
     * <p>Hint: You may use the implementation from AB2.1 as a starting point
     * and adapt it accordingly.</p>
     */
    @Override
    public void step() {

        //TODO: implement method.
        if (world.getPhysicals() == null) {
            return;
        }

        Physical target = carryingFood ? world.getNest() : world.getFood();

        Vector2D randomTurn = randomDirection().scale(0.35);
        Vector2D directionToTarget = subtract(target.getPosition(), state.getPosition());
        directionToTarget = directionToTarget.normalize().scale(0.05);
        Vector2D newDirection = state.getVelocity().scale(0.5).add(randomTurn).add(directionToTarget).normalize();

        Vector2D nextPosition = state.getPosition().add(newDirection);
        nextPosition = avoidIntersections(nextPosition, target);

        state = world.enforceBoundary(new State(nextPosition, newDirection));

        if (!carryingFood && world.getFood().intersects(state.getPosition(), getRadius())) {
            carryingFood = true;
        } else if (carryingFood && world.getNest().intersects(state.getPosition(), getRadius())) {
            carryingFood = false;
        }
    }

    private Vector2D subtract(Vector2D a, Vector2D b) {
        return new Vector2D(a.getX() - b.getX(), a.getY() - b.getY());
    }

    // ---- private helper methods ----

    /**
     * Adjusts the specified candidate position so that this ant does not overlap
     * with blocking physical objects in the world.
     * <p>
     * The current target may be intersected.
     *
     * @param candidatePosition the intended next position
     * @param allowedTarget     the object that may be intersected
     * @return a corrected position
     */
    private Vector2D avoidIntersections(Vector2D candidatePosition, Physical allowedTarget) {

        Vector2D adjusted = candidatePosition;
        int safetyCounter = 0;
        int maxCorrections = 200;

        while (safetyCounter < maxCorrections) {
            Physical collision = firstBlockingIntersection(adjusted, allowedTarget);

            if (collision == null) {
                return adjusted;
            }

            adjusted = moveOutside(adjusted, collision);
            adjusted = world.enforceBoundary(
                    new State(adjusted, state.getVelocity())
            ).getPosition();

            safetyCounter++;
        }

        return adjusted;
    }

    /**
     * Returns the first blocking physical object for the specified position.
     * <p>
     * The current target is allowed to be intersected.
     *
     * @param position      the candidate position
     * @param allowedTarget the target that is allowed to be touched
     * @return the first blocking intersecting object, or {@code null} if none exists
     */
    private Physical firstBlockingIntersection(Vector2D position, Physical allowedTarget) {

        Physical[] physicals = world.getPhysicals();

        for (int i = 0; i < physicals.length; i++) {
            Physical other = physicals[i];

            if (other == null || other == this || other == allowedTarget) {
                continue;
            }

            if (other.intersects(position, getRadius())) {
                return other;
            }
        }

        return null;
    }

    /**
     * Moves the specified intersecting position to a nearby position outside the
     * specified physical object.
     *
     * @param position the intersecting position
     * @param other    the physical object causing the overlap
     * @return a corrected position outside {@code other}
     */
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

    /**
     * Returns a normalized random vector.
     *
     * @return a normalized random vector
     */
    private static Vector2D randomDirection() {
        Vector2D v;

        do {
            v = new Vector2D(R.nextDouble() * 2 - 1, R.nextDouble() * 2 - 1);
        } while (v.isZero());

        return v.normalize();
    }
}