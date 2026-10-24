/**
 * Iterator over a {@link PhysicalDoublyLinkedList}.
 *
 * <p>The iterator traverses the list from the first element to the last
 * element.</p>
 */
public class PhysicalDoublyLinkedListIterator implements PhysicalIterator {

    //TODO: all variables and additional methods and constructors are private.
    private PhysicalDoublyLinkedListNode n;

    /**
     * Creates a new iterator starting at the specified node.
     *
     * @param start the first node to be returned, or {@code null} if the
     *              iteration is empty
     */
    public PhysicalDoublyLinkedListIterator(PhysicalDoublyLinkedListNode start) {

        //TODO: implement constructor.
        this.n = start;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {

        //TODO: implement method.
        return n != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Physical next() {

        //TODO: implement method.
        if(n == null){
            return null;
        }
        Physical result = n.getValue();
        n= n.getNext();
        return result;
    }
}