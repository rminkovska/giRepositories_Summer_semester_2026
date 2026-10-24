import codedraw.CodeDraw;

/**
 * The class {@code Bird} models a simple moving object in a two-dimensional space.
 * <p>
 * A bird has a state (consisting of position and velocity vector).
 * The position determines the current location of the bird, while the velocity describes
 * direction and speed of movement.
 * <p>
 * Birds can update their position over time and may adjust their velocity vector
 * in order to avoid other objects and maintain a minimum distance.
 */
public class Bird {

    //TODO: all variables and additional methods must be private.

    private State state;
    private final World birdsWorld;


    /**
     * Creates a new bird with an initial position and velocity in the given world.
     *
     * @param position the initial position; {@code position != null}
     * @param velocity the initial velocity; {@code velocity != null}
     * @param livesIn  the world the bird lives in; {@code livesIn != null}
     */

    public Bird(Vector2D position, Vector2D velocity, World livesIn) {
        //TODO: implement constructor.

        this.state = new State(position, velocity);
        this.birdsWorld = livesIn;
    }

    /**
     * Computes the steering force based on the given array of birds and updates
     * this bird's position and velocity (use {@link ApplicationVector2D} to see how this works).
     *
     * <p>This method modifies {@code this}. It does not modify other birds.</p>
     *
     * @param birds the array of all birds in the simulation including {@code this};
     *              {@code birds != null}
     */
    public void calculateSteering(Bird[] birds) {

        //TODO: implement this method.

        final double VIEW = 100;    // how many pixels far can a bird see?
        final double SEP_DIST = 18; // tries to keep specific minimum distance
        final double MAX_V = 3.0;   // max speed (max length of velocity vector)
        double MAX_F = 0.1;   // max steering power

        // weights that control behavior
        final double W_SEP = 0.7, W_ALI = 0.8, W_COH = 0.6;

        Vector2D positionCurrentBird = this.state.getPosition();
        Vector2D velocityCurrentBird = this.state.getVelocity();

        // compute the separation, alignment and cohesion vector for i-th bird
        Vector2D separation = new Vector2D(0, 0);
        Vector2D alignment = new Vector2D(0, 0);
        Vector2D cohesion = new Vector2D(0, 0);

        // how many birds are in viewing range if the current bird?
        int cnt = 0;

        for (Bird b: birds) {
            if (b==this) continue;


            double dx = b.state.getPosition().getX() - positionCurrentBird.getX();
            double dy = b.state.getPosition().getY() - positionCurrentBird.getY();
            double d2 = dx * dx + dy * dy;
            if (d2 < VIEW * VIEW) {
                cnt++;
                alignment = alignment.add(b.state.getVelocity());
                cohesion = cohesion.add(b.state.getPosition());

                if (d2 < SEP_DIST * SEP_DIST) { // bird too close
                    // push away by using separation vector, stronger when closer (1/d)
                    double d = Math.sqrt(d2) + 1e-12; // avoid d == 0
                    separation = separation.subtract(new Vector2D(dx, dy).scale(1.0 / d));
                }
            }
        }

        // separation, alignment and cohesion vector for i-th bird ready

        // now compute the overall steering vector for i-th bird from separation, alignment and cohesion
        Vector2D steer = new Vector2D(0, 0);
        if (cnt > 0) {
            // alignment to direction of neighbors
            Vector2D alignmentDir = alignment.scale(1.0 / cnt).normalize().scale(MAX_V).subtract(velocityCurrentBird);
            // Cohesion: movement to gravitational center
            Vector2D center = cohesion.scale(1.0 / cnt);
            Vector2D cohesionDir = center.subtract(positionCurrentBird).normalize().scale(MAX_V).subtract(velocityCurrentBird);
            // Separation: keep distance
            Vector2D separationDir = separation.normalize().scale(MAX_V).subtract(velocityCurrentBird);

            steer = steer
                    .add(separationDir.scale(W_SEP))
                    .add(alignmentDir.scale(W_ALI))
                    .add(cohesionDir.scale(W_COH));
        }
        // overall steering vector for i-th bird ready

        // limit steer force (scale it to length of MAX_F)
        if (steer.length() > MAX_F) steer = steer.normalize().scale(MAX_F);

        // compute and limit velocity (scale it to length of MAX_V)
        velocityCurrentBird = velocityCurrentBird.add(steer);
        if (velocityCurrentBird.length() > MAX_V) velocityCurrentBird = velocityCurrentBird.normalize().scale(MAX_V);



        Vector2D newPos= positionCurrentBird.add(velocityCurrentBird);

        State newState = new State(newPos, velocityCurrentBird);

        this.state = this.birdsWorld.enforceBoundary(newState);
        //update state

    }

    /**
     * Draws this bird onto the given {@link CodeDraw} canvas (use {@link ApplicationVector2D} to see how this works).
     *
     * <p>The bird is rendered as a small filled circle at its position and a short line
     * ("nose") indicating its current direction of movement.</p>
     *
     * @param cd the CodeDraw canvas to draw on; {@code cd != null}
     */
    public void draw(CodeDraw cd) {
        //TODO: implement this method.

        cd.fillCircle(this.state.getPosition().getX(), this.state.getPosition().getY(), 2.2);
        Vector2D nose = this.state.getPosition().add(this.state.getVelocity().normalize().scale(7));
        cd.drawLine(this.state.getPosition().getX(), this.state.getPosition().getY(), nose.getX(), nose.getY());
    }
}
