/**
 * Hash-table implementation of {@link PhysicalPhysicalMap}.
 *
 * <p>This implementation uses open addressing with linear probing.
 * Keys are compared using {@code equals}; hash positions are computed
 * using {@code hashCode}.</p>
 *
 * <p>This map does not store {@code null} keys.</p>
 */
public class PhysicalPhysicalHashMap implements PhysicalPhysicalMap {

    // TODO: edit according to `angabe.md`.

    private Physical[][] keysBox = new Physical[1][];
    private Physical[] values;
    private int size;

    /**
     * Shared mutable modification counter used to detect concurrent structural
     * modifications during iteration.
     * <p>
     * The counter value is incremented whenever the structure of this map changes.
     * Wrapping the counter inside a single-element array allows the counter to be
     * shared by reference between the map, its views, and active iterators.
     * </p>
     */
    private int[] modCounterBox = new int[1];

    //TODO: edit according to `angabe.md`: update modCounterBox where necessary.

    /**
     * Creates an empty map.
     */
    public PhysicalPhysicalHashMap() {

        keysBox[0] = new Physical[64];
        values = new Physical[64];
        size = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Physical put(Physical key, Physical value) {

        int index = find(key);

        if (keysBox[0][index] != null) {
            Physical old = values[index];
            values[index] = value;
            return old;
        }

        keysBox[0][index] = key;
        values[index] = value;
        size++;

        modCounterBox[0]++;

        if (size * 4 >= keysBox[0].length * 3) {
            grow();
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Physical get(Physical key) {

        int index = find(key);
        return keysBox[0][index] == null ? null : values[index];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(Physical key) {

        int index = find(key);
        return keysBox[0][index] != null;
    }

    @Override
    public int size() {

        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {

        return size == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {

        keysBox[0] = new Physical[64];
        values = new Physical[64];
        size = 0;

        modCounterBox[0]++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PhysicalSet keySetView() {

        //TODO: implement method.
        return new PhysicalPhysicalHashMapSetView(this, keysBox, modCounterBox);
    }

    /**
     * Returns the array index at which the specified key is stored or
     * should be inserted.
     *
     * <p>The search uses linear probing beginning at the hash position
     * derived from {@code key.hashCode()}.</p>
     *
     * @param key the key to search for; {@code key != null}
     * @return the array index of the matching key or the first free slot
     */
    private int find(Physical key) {

        int index = key.hashCode() & (keysBox[0].length - 1);

        while (keysBox[0][index] != null && !keysBox[0][index].equals(key)) {
            index = (index + 1) & (keysBox[0].length - 1);
        }

        return index;
    }

    /**
     * Enlarges the internal hash table and reinserts all currently
     * stored associations.
     *
     * <p>The capacity of the internal arrays is doubled.</p>
     */
    private void grow() {

        Physical[] oldKeys = keysBox[0];
        Physical[] oldValues = values;

        keysBox[0] = new Physical[oldKeys.length * 2];
        values = new Physical[oldValues.length * 2];
        size = 0;

        for (int i = 0; i < oldKeys.length; i++) {
            if (oldKeys[i] != null) {
                put(oldKeys[i], oldValues[i]);
            }
        }
    }
}