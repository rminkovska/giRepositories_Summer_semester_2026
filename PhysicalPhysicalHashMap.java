/**
 * Hash-table implementation of {@link PhysicalPhysicalMap}.
 *
 * <p>This implementation uses open addressing with linear probing.
 * Keys are compared using {@code equals}; hash positions are computed
 * using {@code hashCode}.</p>
 *
 * <p>This map does not store {@code null} keys or {@code null} values.</p>
 */
public class PhysicalPhysicalHashMap implements PhysicalPhysicalMap, PhysicalIterable {

    private Physical[] keys;
    private Physical[] values;
    private int size;

    //TODO: override `equals` and `hashCode`.
    //TODO: iterator()

    /**
     * Creates an empty map.
     */
    public PhysicalPhysicalHashMap() {

        keys = new Physical[64];
        values = new Physical[64];
        size = 0;

    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Physical get(Physical key) {

        int index = find(key);
        return keys[index] == null ? null : values[index];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(Physical key) {

        int index = find(key);
        return keys[index] != null;
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

        keys = new Physical[64];
        values = new Physical[64];
        size = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PhysicalSet keySet() {

        //TODO: implement method.
        PhysicalTreeSet set = new PhysicalTreeSet();
        PhysicalIterator iter = this.iterator();
        while (iter.hasNext()) {
            set.add(iter.next());
        }

        return set;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PhysicalIterator iterator() {

        //TODO: implement method.
        return new PhysicalPhysicalHashMapIterator(keys);
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

        int index = key.hashCode() & (keys.length - 1);

        while (keys[index] != null && !keys[index].equals(key)) {
            index = (index + 1) & (keys.length - 1);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof PhysicalPhysicalMap)) return false;

        PhysicalPhysicalMap other = (PhysicalPhysicalMap) o;

        if (this.size() != other.size()) return false;

        PhysicalIterator iter = this.iterator();

        while (iter.hasNext()) {
            Physical key = iter.next();

            if (!other.containsKey(key)) return false;

            Physical thisValue = this.get(key);
            Physical otherValue = other.get(key);

            if (!thisValue.equals(otherValue)) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        PhysicalIterator iter = this.iterator();

        while (iter.hasNext()) {
            Physical key = iter.next();
            Physical value = this.get(key);

            int keyHash = (key == null) ? 0 : key.hashCode();
            int valueHash = (value == null) ? 0 : value.hashCode();

            hash += (keyHash ^ valueHash);
        }

        return hash;
    }
}