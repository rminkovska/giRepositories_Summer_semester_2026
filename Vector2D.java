/**
 * The class {@code Vector2D} represents a two-dimensional mathematical vector.
 * This class replaces the former array-based representation of vectors.
 *
 * A vector consists of an x- and a y-coordinate and is used to model
 * positions and directions in a two-dimensional space.
 *
 * This class is intended to encapsulate both, data (coordinates)
 * and the operations that can be performed on vectors, such as
 * addition, distance computation and normalization.
 *
 * A {@code Vector2D} object provides all operations for vector manipulation
 * via instance methods (no direct manipulation of the coordinates from outside
 * the class).
 *
 * Objects of this class are mutable, i.e., they can change their
 * state (coordinates) upon a method call.
 */
public class Vector2D {
    public double x;
    public double y;
    public void newCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vector2D add(Vector2D another){
        Vector2D sum= new Vector2D();
        sum.newCoordinates(this.x+another.x, this.y+another.y);
        return sum;
    }
    public Vector2D substract(Vector2D another){
        Vector2D sub= new Vector2D();
        sub.newCoordinates(this.x-another.x, this.y-another.y);
        return sub;
    }
    public void scale(double factor)
    {
        this.x*=factor;
        this.y*=factor;
    }
    public double distance(Vector2D another)
    {
        return Math.sqrt((this.x-another.x)*(this.x-another.x)+(this.y-another.y)*(this.y-another.y));
    }
    public double length()
    {
        return Math.sqrt((this.x*this.x)+(this.y*this.y));
    }
    public void normalize()
    {
        if(length()!=0)
        {
            this.x /= length();
            this.y /= length();
        }
    }
    public boolean Nullvektor()
    {
        if(this.x==0 && this.y==0) return true;
        else return false;
    }
    public double [] toArray()
    {
        return new double[]{this.x,this.y};
    }
    // TODO: define this class.
}