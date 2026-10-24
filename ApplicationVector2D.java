import codedraw.CodeDraw;
import java.util.Random;

/**
 * A simulation akin to https://doi.org/10.1145/37402.37406.
 */
public class ApplicationVector2D {

    public static void main(String[] args) {

        final Random R = new Random(2026); // fixed seed helps debugging

        // parameters of the simulation:
        final int W = 900, H = 600, // size of world
                  N = 250;          // number of birds
        final double VIEW = 100;    // how many pixels far can a bird see?
        final double SEP_DIST = 18; // tries to keep specific minimum distance
        final double MAX_V = 3.0;   // max speed (max length of velocity vector)
        final double MAX_F = 0.1;   // max steering power

        // weights that control behavior
        final double W_SEP = 0.7, W_ALI = 0.8, W_COH = 0.6;

        CodeDraw cd = new CodeDraw(W, H);

        // a state (position, velocity) for each bird
        Vector2D[] position = new Vector2D[N]; // Position of each of the N birds
        Vector2D[] velocity = new Vector2D[N]; // Velocity for each of the of N birds

        // TODO: activate this block:
        // initialize randomly
        for (int i = 0; i < N; i++) {
            position[i] = new Vector2D(R.nextDouble() * W, R.nextDouble() * H);
            velocity[i] = new Vector2D(R.nextDouble() * 2 - 1, R.nextDouble() * 2 - 1).normalize().scale(1 + R.nextDouble() * 2);
        }

        // simulation loop (1 iteration == 1 time step)
        while (true) {
            cd.clear();

            for (int i = 0; i < N; i++) {
                Vector2D positionCurrentBird = position[i];
                Vector2D velocityCurrentBird = velocity[i];

                // compute the separation, alignment and cohesion vector for i-th bird
                Vector2D separation = new Vector2D(0, 0);
                Vector2D alignment = new Vector2D(0, 0);
                Vector2D cohesion = new Vector2D(0, 0);

                // how many birds are in viewing range if the current bird?
                int cnt = 0;

                for (int j = 0; j < N; j++) if (j != i) {
                    double dx = position[j].getX() - positionCurrentBird.getX();
                    double dy = position[j].getY() - positionCurrentBird.getY();
                    double d2 = dx * dx + dy * dy;
                    if (d2 < VIEW * VIEW) {
                        cnt++;
                        alignment = alignment.add(velocity[j]);
                        cohesion = cohesion.add(position[j]);

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

                // compute new position
                positionCurrentBird = positionCurrentBird.add(velocityCurrentBird);

                // wrap-around (Torus world)
                double x = positionCurrentBird.getX(), y = positionCurrentBird.getY();
                if (x < 0) x += W;
                if (y < 0) y += H;
                if (x >= W) x -= W;
                if (y >= H) y -= H;
                positionCurrentBird = new Vector2D(x, y);

                // update state
                position[i] = positionCurrentBird; velocity[i] = velocityCurrentBird;

                // draw (small circle + "nose" in direction of movement)
                cd.fillCircle(positionCurrentBird.getX(), positionCurrentBird.getY(), 2.2);
                Vector2D nose = positionCurrentBird.add(velocityCurrentBird.normalize().scale(7));
                cd.drawLine(positionCurrentBird.getX(), positionCurrentBird.getY(), nose.getX(), nose.getY());
            }

            cd.show();
        }
        //TODO: end of block to activate
    }
}

