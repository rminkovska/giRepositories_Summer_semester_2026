/**
 * Hash-table implementation of {@link PhysicalPhysicalMap}.
 *
 * <p>This implementation uses open addressing with linear probing.
 * Keys are compared using {@code equals}; hash positions are computed
 * using {@code hashCode}.</p>
 *
 * <p>This map does not store {@code null} keys or {@code null} values.</p>
 */
public class PhysicalPhysicalHashMap implements PhysicalPhysicalMap {

    private Physical[] keys;
    private Physical[] values;
    private int size;

    /**
     * Creates an empty map.
     */
    public PhysicalPhysicalHashMap() {

        keys = new Physical[64];
        values = new Physical[64];
        size = 0;

    }

    @Override
    public Physical put(Physical key, Physical value) {

        int index = find(key);

        if (keys[index] != null) {
            Physical old = values[index];
            values[index] = value;
            return old;
        }

        keys[index] = key;
        values[index] = value;
        size++;

        if (size * 4 >= keys.length * 3) {
            grow();
        }

        return null;
    }

    @Override
    public Physical get(Physical key) {

        int index = find(key);
        return keys[index] == null ? null : values[index];
    }

    @Override
    public boolean containsKey(Physical key) {

        int index = find(key);
        return keys[index] != null;
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public boolean isEmpty() {

        return size == 0;
    }

    @Override
    public void clear() {

        keys = new Physical[64];
        values = new Physical[64];
        size = 0;
    }

    private int find(Physical key) {

        int index = key.hashCode() & (keys.length - 1);

        while (keys[index] != null && !keys[index].equals(key)) {
            index = (index + 1) & (keys.length - 1);
        }

        return index;
    }

    private void grow() {

        Physical[] oldKeys = keys;
        Physical[] oldValues = values;

        keys = new Physical[oldKeys.length * 2];
        values = new Physical[oldValues.length * 2];
        size = 0;

        for (int i = 0; i < oldKeys.length; i++) {
            if (oldKeys[i] != null) {
                put(oldKeys[i], oldValues[i]);
            }
        }
    }
}