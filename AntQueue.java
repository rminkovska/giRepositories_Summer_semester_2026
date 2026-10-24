/**
 * Simple FIFO queue for ants.
 *
 * <p>This queue stores {@link Ant} objects and follows the
 * <em>First-In-First-Out (FIFO)</em> principle: the first element
 * inserted into the queue is the first one removed.</p>
 *
 * <p>The queue is implemented using a circular buffer, based on an array that grows
 * automatically when its capacity is exceeded.</p>
 */
public class AntQueue {

    // TODO: all variables and additional methods are private.

    /**
     * Creates an empty queue with a specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the queue; {@code initialCapacity > 0}
     */
    private Ant[] ants;
    private int size;
    private int head;
    private int tail;

    public AntQueue(int initialCapacity) {

        //TODO: implement constructor.
        this.ants = new Ant[initialCapacity];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    /**
     * Creates a new queue that is a copy of the specified queue.
     * <p>
     * The new queue contains the same elements as {@code queue}.
     *
     * @param queue the queue to copy; {@code queue != null}
     */
    public AntQueue(AntQueue queue) {

        // TODO: implement constructor.
        this.ants = new Ant[queue.ants.length];
        this.size = queue.size;
        for (int i = 0; i < queue.size; i++) {
            this.ants[i] = queue.ants[(queue.head + i) % queue.ants.length];
        }
        this.head = 0;
        this.tail = queue.size % ants.length;
    }

    /**
     * Adds an ant to the end of the queue.
     *
     * <p>If the internal array is full, its capacity is doubled
     * before inserting the new element.</p>
     *
     * @param a the {@link Ant} to add to the queue; {@code a != null}
     */
    public void add(Ant a) {
        //TODO: implement method.
        if (size == ants.length) {
            Ant[] newAnts = new Ant[size * 2];
            for (int i = 0; i < size; i++) {
                newAnts[i] = ants[(head + i) % ants.length];
            }
            ants = newAnts;
            this.head = 0;
            this.tail = size;
        }
        this.ants[this.tail] = a;
        this.tail = (this.tail + 1) % ants.length;
        size++;
    }

    /**
     * Removes and returns the first element of the queue.
     *
     * <p>This method removes the element at the head of the queue
     * and advances the head position.</p>
     *
     * @return the first {@link Ant} in the queue or {@code null} if {@code size() == 0}.
     */
    public Ant poll() {

        //TODO: implement method.
        if (size == 0) return null;
        Ant firstAnt = this.ants[this.head];
        this.ants[this.head] = null;
        this.head = (this.head + 1) % ants.length;
        size--;
        return firstAnt;
    }

    /**
     * Returns the first element of the queue without removing it.
     *
     * <p>The queue remains unchanged after this operation.</p>
     *
     * @return the first {@link Ant} in the queue or {@code null} if {@code size() == 0}.
     */
    public Ant peek() {

        //TODO: implement method.
        if (size == 0) return null;
        return this.ants[this.head];
    }

    /**
     * Returns the number of elements currently stored within the queue.
     *
     * @return the current queue size.
     */
    public int size() {

        //TODO: implement method.
        return size;
    }
}