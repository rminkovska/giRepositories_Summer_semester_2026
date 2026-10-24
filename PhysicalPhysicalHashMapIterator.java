import java.sql.Array;

/**
 * Iterator over the keys stored in a {@link PhysicalPhysicalHashMap}.
 *
 * <p>The iterator scans the internal hash table and
 * returns the key of each occupied table entry.</p>
 *
 * <p>The iteration order is determined by the internal table layout and is
 * therefore unspecified.</p>
 */
public class PhysicalPhysicalHashMapIterator implements PhysicalIterator //TODO: acivate clause.
{

    // TODO: all variables and additional constructors and methods are private,
    //  unless a method overrides or implements an inherited public method.

    /**
     * Creates a new iterator over the specified hash table key array.
     *
     * @param keys the internal key array of the hash map;
     * {@code keys != null}
     */
    private Physical[] keys;
    private int index;
    private int size;

    public PhysicalPhysicalHashMapIterator(Physical[] keys) {

        //TODO: implement constructor.
        this.keys = keys;
        this.index = 0;
        this.size = keys.length;
    }

    /**
     * Returns whether another physical object is available.
     *
     * @return {@code true} if a subsequent call to {@link #next()} can return
     * another object, otherwise {@code false}
     */
    public boolean hasNext() {
        while(index < size && keys[index]==null) {
            index++;
        }
        return index < size;
    }

    /**
     * Returns the next physical object of the traversal.
     *
     * <p>If no further object is available, this method returns {@code null}.</p>
     *
     * @return the next physical object, or {@code null} if no further object exists
     */
    public Physical next() {
        if (hasNext()) {
            return keys[index++];
        }
        else return null;
    }
}