/**
 * A simple associative data structure (map) mapping {@link Vector2D} keys to {@link Ant} values.
 *
 * <p>This class represents a minimal key-value association structure.
 * Each {@code Vector2D} key is associated with exactly one {@code Ant} value.
 * Internally the associations are stored in arrays.</p>
 */
public class Vector2DAntMap {

    // TODO: all variables and additional methods are private.

    /**
     * Creates an empty map (with no associations).
     */
    private Vector2D[] ks;
    private Ant[] vs;
    private int size;

    public Vector2DAntMap() {
        //TODO: implement constructor.
        this.size = 0;
        this.ks = new Vector2D[32];
        this.vs = new Ant[32];
    }

    /**
     * Insert a new association into the map.
     *
     * <p>If the specified key already exists, its associated value
     * is replaced with the given value.</p>
     *
     * @param k the key (a {@link Vector2D} position)
     * @param v the value (an {@link Ant})
     * @return the previously associated value, or {@code null} if none existed
     */
    public Ant put(Vector2D k, Ant v) {

        //TODO: implement method.

        for (int i = 0; i < this.size; i++) {
            if (containsKey(k)) {
                Ant old = this.vs[i];
                this.vs[i] = v;
                return old;
            }
        }

        if (size == ks.length) {
            Ant[] newVs = new Ant[ks.length * 2];
            Vector2D[] newKs = new Vector2D[ks.length * 2];
            for (int i = 0; i < ks.length; i++) {
                newKs[i] = ks[i];
                newVs[i] = vs[i];
            }
            ks = newKs;
            vs = newVs;
        }

        this.ks[size] = k;
        this.vs[size] = v;
        size++;
        return null;
    }

    /**
     * Remove the association with the given key.
     *
     * @param k the key to remove
     * @return the value previously associated with the key,
     * or {@code null} if the key was not present
     */
    public Ant remove(Vector2D k) {

        //TODO: implement method.
        for (int i = 0; i < size; i++) {
            if (containsKey(k)) {
                Ant oldV = this.vs[i];

                this.vs[i] = this.vs[size - 1];
                this.ks[i] = this.ks[size - 1];

                this.vs[size - 1] = null;
                this.ks[size - 1] = null;

                size--;
                return oldV;
            }
        }
        return null;
    }

    /**
     * Return the value associated with the given key.
     *
     * @param k the key to search for
     * @return the associated {@link Ant}, or {@code null} if the key is not present
     */
    public Ant get(Vector2D k) {

        //TODO: implement method.
        for (int i = 0; i < size; i++) {
            if(containsKey(k)) {
                return this.vs[i];
            }
        }
        return null;
    }

    /**
     * Check whether the map contains the given key.
     *
     * @param k the key to check
     * @return {@code true} if the key exists in the map,
     * {@code false} otherwise
     */
    public boolean containsKey(Vector2D k) {

        //TODO: implement method.
        for (int i = 0; i < size; i++) {
            if (k == null) {
                if(this.ks[i] == null) return true;
            }
            else if(this.ks[i] != null && k.equals(this.ks[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether the map contains the given value.
     *
     * @param v the value to check
     * @return {@code true} if the value exists in the map,
     * {@code false} otherwise
     */
    public boolean containsValue(Ant v) {

        //TODO: implement method.
        for (int i = 0; i < size; i++) {
            if (v.equals(this.vs[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the number of stored associations.
     *
     * @return the number of key-value pairs stored within the map.
     */
    public int size() {

        //TODO: implement method.
        return size;
    }

    /**
     * Returns all keys stored within in this map.
     *
     * <p>
     * The returned stack contains all {@link Vector2D} keys currently stored
     * in the map. Each key appears exactly once in the stack.
     * The order is not specified.
     * </p>
     *
     * @return a {@link Vector2DStack} containing all stored keys
     */
    public Vector2DStack keys() {

        //TODO: implement method.
        Vector2DStack keys = new Vector2DStack();
        for (int i = 0; i < size; i++) {
            if (this.ks[i] != null) {
                keys.push(this.ks[i]);
            }
        }
        return keys;
    }

    /**
     * Returns all values stored in this map.
     *
     * <p>
     * The returned queue contains all {@link Ant} objects currently stored
     * within the map. Duplicate entries may occur if there is more than
     * one association with the same value. The order is not specified.
     * </p>
     *
     * @return an {@link AntQueue} containing all stored values
     */
    public AntQueue values() {

        //TODO: implement method.
        AntQueue values = new AntQueue(size);
        for (int i = 0; i < size; i++) {
            if (this.vs[i] != null) {
                values.add(this.vs[i]);
            }
        }
        return values;
    }
}