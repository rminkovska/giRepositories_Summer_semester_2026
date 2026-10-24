/**
 * The class {@code Bird} models a simple moving object in a two-dimensional space.
 * <p>
 * A bird has a position and a velocity vector. The position determines the
 * current location of the bird, while the velocity describes direction and
 * speed of movement.
 * <p>
 * Birds can update their position over time and may adjust their velocity vector
 * in order to avoid other objects and maintain a minimum distance.
 */
public class Bird {
    Vector2D bird = new Vector2D();
    double x, y;
    double vx, vy;
    double v;

    public void position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void velocity(double angleDeg, double velocity) {
        this.v = velocity;
        double angleRad = Math.toRadians(angleDeg);
        this.vx = velocity * Math.cos(angleRad);
        this.vy = velocity * Math.sin(angleRad);
    }

    public void move(double distance) {
        x += vx * distance;
        y += vy * distance;
        if (x > 400 || x < 0) vx *= -1;
        if (y > 400 || y < 0) vy *= -1;
    }

    public void avoidCollision(Bird another, double safeDistance) {
        double actualDistance=Math.sqrt((this.x-another.x)*(this.x-another.x)+(this.y-another.y)*(this.y-another.y));
        if(actualDistance<safeDistance) {
            this.vx *= -1;
            this.vy *= -1;
        }
    }

    double currentX() {
        return this.x;
    }

    double currentY() {
        return this.y;
    }

    //TODO: define this class according to `angabe.md`.
}
