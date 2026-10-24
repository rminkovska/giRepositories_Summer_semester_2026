/**
 * A simulation of flying birds.
 * <p>
 * TODO: change this class according to 'angabe.md'.
 */

import codedraw.CodeDraw;
import codedraw.Palette;

import java.util.Arrays;

public class ApplicationBird {

    /**
     * The method {@code ApplicationBird} serves as an entry point for the simulation of flying birds.
     * <p>
     * It demonstrates basic usage of the class {@link Bird}.
     */
    static void main() {

        Bird bird1 = new Bird();
        bird1.position(1, 3);
        Bird bird2 = new Bird();
        bird2.position(300, 200);

        CodeDraw cd = new CodeDraw(400, 400);

        bird1.velocity(36, 8);
        bird2.velocity(42, 5);

        bird1.move(2);
        bird2.move(3);

        while (!cd.isClosed()) {
            cd.clear(Palette.DEEP_SKY_BLUE);

            bird1.move(1);
            bird2.move(1);

            bird1.avoidCollision(bird2, 50);
            bird2.avoidCollision(bird1, 50);

            cd.setColor(Palette.CRIMSON);
            cd.fillCircle(bird1.currentX(), bird1.currentY(), 8);

            cd.setColor(Palette.YELLOW);
            cd.fillCircle(bird2.currentX(), bird2.currentY(), 8);

            cd.show(5);
        }
    }
}
