import java.util.Random;

/**
 * Single ant agent.
 *
 * An ant searches for food, stores its path in a stack, and after finding food
 * returns to the nest by retracing exactly that path in reverse order.
 */
public class Ant {

    private static final Random R = new Random();

    private State state;
    private boolean carryingFood;

    private final World world;
    private final Vector2DStack path;

    /**
     * Creates a new ant at the position of the nest of the specified world.
     *
     * <p>The ant is initialized with a random movement direction and an empty
     * path memory. The provided {@link World} instance is used for all
     * environment interactions such as detecting food, nest location, world boundaries, etc.</p>
     *
     * @param world the simulation world the ant operates in; {@code world != null}
     */
    public Ant(World world) {

        this.state = new State(world.getNest(), randomDirection());
        this.world = world;
        this.carryingFood = false;
        this.path = new Vector2DStack();
    }

    /**
     * Returns the current position of the ant.
     *
     * @return the current position as a {@link Vector2D}
     */
    public Vector2D getPosition() {

        return state.getPosition();
    }

    /**
     * Indicates whether the ant is currently carrying food.
     *
     * @return {@code true} if the ant has found food and is returning to the nest,
     *         {@code false} if it is currently searching for food
     */
    public boolean isCarryingFood() {

        return carryingFood;
    }

    /**
     * Performs one simulation step of the ant.
     *
     * <p>If the ant is not carrying food, it continues searching by exploring the
     * environment. While searching, the ant stores visited positions in its
     * path memory. The ant's movements are partly random and partly steered by sniffing the
     * location of food (use a normalized combination of two vectors).</p>
     *
     * <p>If the ant is carrying food, it retraces the stored path in reverse order
     * until it reaches the nest. As soon as it reaches the nest it is not carrying food
     * any longer.</p>
     */
    public void step() {

        if (!carryingFood) { // make a step searching for food

            // TODO: store current position in your path here
            path.push(state.getPosition());

            Vector2D randomTurn = randomDirection().scale(0.35);
            Vector2D directionToFood = world.getFood().subtract(state.getPosition()).normalize().scale(0.05);
            Vector2D newDirection = state.getVelocity().scale(0.5).add(randomTurn).add(directionToFood).normalize();
            Vector2D nextPosition = state.getPosition().add(newDirection);

            state = world.enforceBoundary(new State(nextPosition, newDirection));

            if (state.getPosition().distanceTo(world.getFood()) < 1.0) {
                carryingFood = true;
            }

        } else { // make a step back on path

            //TODO: complete the method implementation.
            if (path.isEmpty())
            {
                carryingFood = false;
                return;
            }

            Vector2D nextPosition = path.pop();
            Vector2D nextDirection = nextPosition.subtract(state.getPosition());

            state= world.enforceBoundary(new State(nextPosition, nextDirection));

            if (state.getPosition().distanceTo(world.getNest()) < 1.0) {
                carryingFood = false;
                path.clear();
            }
        }
    }

    /**
     * Returns a normalized random vector.
     *
     * @return a normalized random vector.
     */
    private static Vector2D randomDirection() {

        return new Vector2D(R.nextDouble() * 2 - 1, R.nextDouble() * 2 - 1).normalize();
    }
}