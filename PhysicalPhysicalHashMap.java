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

    // TODO: all variables and additional methods must be private.

    /**
     * Creates an empty map.
     */
    private Physical[] ks;
    private Physical[] vs;
    private int count;
    int INITIAL_CAPACITY = 64;

    public PhysicalPhysicalHashMap() {

        // TODO: implement constructor.
        ks = new Physical[INITIAL_CAPACITY];
        vs = new Physical[INITIAL_CAPACITY];
        count = 0;

    }

    private int find(Physical k) {
        int i = k.hashCode() & (ks.length - 1);
        while (ks[i] != null && !ks[i].equals(k)) {
            i = (i + 1) & (ks.length - 1);
        }
        return i;
    }

    @Override
    public Physical put(Physical key, Physical value) {

        // TODO: implement method.
        if (key == null) return null;
        int i = find(key);
        Physical old = vs[i];
        vs[i] = value;
        if (ks[i] == null) {
            ks[i] = key;
            if (++count >= 0.75 * ks.length) {
                Physical[] oks = ks, ovs = vs;
                ks = new Physical[oks.length << 1];
                vs = new Physical[oks.length << 1];
                for (int j = 0; j < oks.length; j++) {
                    if (oks[j] != null) {
                        ks[i = find(oks[j])] = oks[j];
                        vs[i] = ovs[j];
                    }
                }
            }
        }
        return old;
    }

    @Override
    public Physical get(Physical key) {

        // TODO: implement method.
        return vs[find(key)];
    }

    @Override
    public boolean containsKey(Physical key) {

        // TODO: implement method.
        return ks[find(key)] != null;
    }

    @Override
    public int size() {

        // TODO: implement method.
        return count;
    }

    @Override
    public boolean isEmpty() {

        // TODO: implement method.
        return count == 0;
    }

    @Override
    public void clear() {
        // TODO: implement method.
        ks = new Physical[INITIAL_CAPACITY];
        vs = new Physical[INITIAL_CAPACITY];
        count = 0;
    }
}