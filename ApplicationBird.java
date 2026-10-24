import codedraw.CodeDraw;

import java.util.Random;

public class ApplicationBird {
    static void main(String[] args) {


        final Random R = new Random(2026);
        final int W = 900, H = 600, N = 250;

        World myWorld = new World(W, H);
        Bird[] birds = new Bird[N];
        CodeDraw cd = new CodeDraw(W, H);


        for (int i = 0; i < N; i++) {
            Vector2D position = new Vector2D(R.nextDouble() * W, R.nextDouble() * H);
            Vector2D velocity = new Vector2D(R.nextDouble() * 2 - 1, R.nextDouble() * 2 - 1).normalize().scale(1 + R.nextDouble() * 2);
            birds[i] = new Bird(position, velocity, myWorld);
        }

        while (true) {
            cd.clear();

            for (Bird b : birds) {
                b.calculateSteering(birds);
            }

            for (Bird b : birds) {
                b.draw(cd);
            }
            cd.show();
        }
    }
}
