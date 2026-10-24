/**
 * Doubly linked list with elements of type {@link Physical}.
 *
 * <p>The list is implemented as a (non-cyclic) doubly linked structure with
 * references to the first (head) and last node (last).</p>
 */
public class PhysicalDoublyLinkedList {

    private PhysicalDoublyLinkedListNode head;
    private PhysicalDoublyLinkedListNode last;
    private int size;

    /**
     * Creates an empty list.
     */
    public PhysicalDoublyLinkedList() {

        this.head = null;
        this.last = null;
        this.size = 0;
    }

    /**
     * Creates a new list that is a copy of the specified list. Later changes of
     * {@code this} (like adding or removing elements) will not affect {@code list} and
     * vice versa.
     *
     * <p>The new list contains the same elements and in the same order
     * as {@code list}.</p>
     *
     * @param list the list to copy; {@code list != null}
     */
    public PhysicalDoublyLinkedList(PhysicalDoublyLinkedList list) {

        this();
        PhysicalDoublyLinkedListNode current = list.head;

        while (current != null) {
            addLast(current.getValue());
            current = current.getNext();
        }
    }

    /**
     * Adds an element at the head of the list.
     *
     * @param v the element to add
     */
    public void addFirst(Physical v) {

        PhysicalDoublyLinkedListNode newNode = new PhysicalDoublyLinkedListNode(v, null, head);

        if (head == null) {
            head = newNode;
            last = newNode;
        } else {
            head.setPrev(newNode);
            head = newNode;
        }

        size++;
    }

    /**
     * Adds an element at the end of the list.
     *
     * @param p the element to add
     */
    public void addLast(Physical p) {

        PhysicalDoublyLinkedListNode newNode = new PhysicalDoublyLinkedListNode(p, last, null);

        if (last == null) {
            head = newNode;
            last = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }

        size++;
    }

    /**
     * Adds an element at the specified position in the list.
     *
     * <p>If {@code index == 0}, the element is inserted at the head of the list.
     * If {@code index == size()}, the element is inserted at the end of the list.
     * All elements currently stored at positions greater than or equal to
     * {@code index} are shifted by one position toward the end of the list.</p>
     *
     * @param index the position at which the element is to be inserted;
     *              {@code 0 <= index <= size()}
     * @param p the element to add
     */
    public void add(int index, Physical p) {

        if (index == 0) {
            addFirst(p);
            return;
        }
        if (index == size) {
            addLast(p);
            return;
        }

        PhysicalDoublyLinkedListNode nextNode = nodeAt(index);
        PhysicalDoublyLinkedListNode prevNode = nextNode.getPrev();
        PhysicalDoublyLinkedListNode newNode = new PhysicalDoublyLinkedListNode(p, prevNode, nextNode);

        prevNode.setNext(newNode);
        nextNode.setPrev(newNode);
        size++;
    }

    /**
     * Removes and returns the head element of the list.
     *
     * <p>This method removes the first element of the list.</p>
     *
     * @return the first element in the list, or {@code null} if {@code size() == 0}
     */
    public Physical pollFirst() {

        if (head == null) {
            return null;
        }

        Physical result = head.getValue();

        if (head == last) {
            head = null;
            last = null;
        } else {
            head = head.getNext();
            head.setPrev(null);
        }

        size--;
        return result;
    }

    /**
     * Removes and returns the last element of the list.
     *
     * <p>This method removes the last element of the list.</p>
     *
     * @return the last element in the list, or {@code null} if {@code size() == 0}
     */
    public Physical pollLast() {

        if (last == null) {
            return null;
        }

        Physical result = last.getValue();

        if (head == last) {
            head = null;
            last = null;
        } else {
            last = last.getPrev();
            last.setNext(null);
        }

        size--;
        return result;
    }

    /**
     * Returns the head element of the list without removing it.
     *
     * <p>The list remains unchanged after this operation.</p>
     *
     * @return the first element in the list, or {@code null} if {@code size() == 0}
     */
    public Physical peekFirst() {

        return head == null ? null : head.getValue();
    }

    /**
     * Returns the last element of the list without removing it.
     *
     * <p>The list remains unchanged after this operation.</p>
     *
     * @return the last element in the list, or {@code null} if {@code size() == 0}
     */
    public Physical peekLast() {

        return last == null ? null : last.getValue();
    }

    /**
     * Returns whether this list contains the specified element (identical to `p`).
     *
     * @param p the element to search for
     * @return {@code true} if present, otherwise {@code false}
     */
    public boolean contains(Physical p) {

        PhysicalDoublyLinkedListNode current = head;
        while (current != null) {
            if (current.getValue() == p) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Returns the element at the specified position in the list.
     *
     * <p>The first element has index {@code 0}.</p>
     *
     * @param index the index of the element to return;
     *              {@code 0 <= index < size()}
     * @return the element at the specified position
     */
    public Physical get(int index) {

        return nodeAt(index).getValue();
    }

    /**
     * Removes and returns the element at the specified position in the list.
     *
     * <p>All elements following the removed element are shifted by one position
     * toward the head of the list.</p>
     *
     * @param index the index of the element to remove;
     *              {@code 0 <= index < size()}
     * @return the removed element
     */
    public Physical remove(int index) {

        if (index == 0) {
            return pollFirst();
        }
        if (index == size - 1) {
            return pollLast();
        }

        PhysicalDoublyLinkedListNode removed = nodeAt(index);
        PhysicalDoublyLinkedListNode prevNode = removed.getPrev();
        PhysicalDoublyLinkedListNode nextNode = removed.getNext();

        prevNode.setNext(nextNode);
        nextNode.setPrev(prevNode);
        size--;

        return removed.getValue();
    }

    /**
     * Returns whether this list contains no elements.
     *
     * @return {@code true} if {@code size() == 0}, {@code false} otherwise
     */
    public boolean isEmpty() {

        return size == 0;
    }

    /**
     * Removes all elements from the list.
     */
    public void clear() {

        this.head = null;
        this.last = null;
        this.size = 0;
    }

    /**
     * Returns the number of elements currently stored in the list.
     *
     * @return the current size of the list
     */
    public int size() {

        return size;
    }

    private PhysicalDoublyLinkedListNode nodeAt(int index) {
        if (index < size / 2) {
            PhysicalDoublyLinkedListNode current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current;
        } else {
            PhysicalDoublyLinkedListNode current = last;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrev();
            }
            return current;
        }
    }
}