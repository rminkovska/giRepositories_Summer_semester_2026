/**
 * Stack storing {@link Vector2D} objects.
 *
 * <p>This stack stores {@link Vector2D} objects. It follows the
 * <em>Last-In-First-Out (LIFO)</em> principle: the most recently pushed
 * element is the first one removed.</p>
 *
 * <p>The stack is implemented using a dynamically growing array.</p>
 */

public class Vector2DStack {

    // TODO: all variables and additional methods are private.

    /**
     * Creates an empty stack with an initial capacity of 32 elements.
     */
    private Vector2D[] vector;
    private int size;

    public Vector2DStack() {

        //TODO: implement constructor.
        this.vector = new Vector2D[32];
        this.size = 0;
    }

    /**
     * Pushes a new vector on top of the stack.
     *
     * <p>If the internal array is full, its capacity is doubled before
     * inserting the new element.</p>
     *
     * @param v the {@link Vector2D} value to push onto the stack
     */
    public void push(Vector2D v) {

        //TODO: implement method.
        if (size == vector.length) {
            Vector2D[] doubleVector = new Vector2D[size * 2];
            for (int i = 0; i < size; i++) {
                doubleVector[i] = vector[i];
            }
            vector = doubleVector;
        }
        vector[size] = v;
        size++;
    }

    /**
     * Removes and returns the topmost element. Returns {@code null} if the stack is empty.
     *
     * @return the top {@link Vector2D} element, or {@code null} if the stack is empty.
     */
    public Vector2D pop() {

        //TODO: implement method.
        if (isEmpty()) return null;
        size--;
        Vector2D topMost = vector[size];
        vector[size] = null;
        return topMost;
    }

    /**
     * Returns the element currently at the top of the stack
     * without removing it. Returns {@code null} if the stack is empty.
     *
     * @return the top {@link Vector2D} element, or {@code null} if the stack is empty.
     */
    public Vector2D peek() {

        //TODO: implement method.
        if (isEmpty()) return null;
        Vector2D topMost = vector[size - 1];
        return topMost;
    }

    /**
     * Checks whether the stack is empty.
     *
     * @return {@code true} if the stack contains no elements,
     * {@code false} otherwise
     */
    public boolean isEmpty() {

        //TODO: implement method.
        if (size == 0) return true;
        else return false;
    }

    /**
     * Removes all elements from the stack. After this operation the stack is empty.
     */
    public void clear() {
        //TODO: implement method.
        for (int i = 0; i < size; i++) {
            vector[i] = null;
        }
        size = 0;
    }

    /**
     * Returns the number of elements currently stored in the stack.
     *
     * @return the current stack size
     */
    public int size() {

        //TODO: implement method.
        return size;
    }
}