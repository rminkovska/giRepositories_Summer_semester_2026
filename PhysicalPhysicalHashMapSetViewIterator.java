/**
 * Iterator over the keys stored in a {@link PhysicalPhysicalHashMapSetView}.
 *
 * <p>The iterator scans the internal hash table of the backing map and
 * returns the key of each occupied table entry.</p>
 *
 * <p>The iteration order is determined by the internal table layout and is
 * therefore unspecified.</p>
 */
public class PhysicalPhysicalHashMapSetViewIterator implements PhysicalIterator //TODO: acivate clause.
{

    // TODO: all variables and additional constructors and methods are private,
    //  unless a method overrides or implements an inherited public method.

    /**
     * Creates a new iterator over the specified hash table key array.
     *
     * @param keys the internal key array of the hash map;
     * {@code keys != null}
     * @param modCounterBox shared modification counter reference
     */

    private Physical[] keys;
    private int[] modCounterBox;

    private int index;
    private int expectedModCount;

    public PhysicalPhysicalHashMapSetViewIterator(Physical[] keys, int[] modCounterBox) {

        //TODO: implement constructor.
        this.keys = keys;
        this.modCounterBox = modCounterBox;
        this.expectedModCount = modCounterBox[0];
        this.index = 0;
    }

    /**
     * Returns whether another physical object is available.
     *
     * @return {@code true} if a subsequent call to {@link #next()} can return
     * another object, otherwise {@code false}
     */
    public boolean hasNext() {
        while(index < keys.length && keys[index] == null){
            index++;
        }
        return index < keys.length;
    }

    /**
     * Returns the next physical object of the traversal.
     *
     * <p>If no further object is available, this method returns {@code null}.</p>
     *
     * @return the next physical object, or {@code null} if no further object exists
     * @throws ConcurrentModificationException if the underlying collection
     *                                         was structurally modified during iteration
     */
    public Physical next() {
        if (modCounterBox[0] != expectedModCount) {
            int diff =  modCounterBox[0] - expectedModCount;
            throw new ConcurrentModificationException(diff);
        }
        if (hasNext()) {
            return keys[index++];
        }
        return null;
    }
}