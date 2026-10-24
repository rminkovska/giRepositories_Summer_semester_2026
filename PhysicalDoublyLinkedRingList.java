/**
 * Doubly linked circular list with sentinel (NIL) node
 * and elements of type {@link Physical}.
 *
 * <p>The list is implemented as a doubly linked ring. A dedicated NIL node
 * is always present and simplifies all insert/remove operations:
 * <p>
 * for an empty list, {@code nil.next == nil} and {@code nil.prev == nil}.</p>
 */
public class PhysicalDoublyLinkedRingList {

    //TODO: all variables and additional methods and constructors must be private.

    /**
     * Creates an empty list.
     */
    private DLNode nil;
    private int size;

    private class DLNode {
        private Physical value;
        private DLNode next;
        private DLNode prev;

        public DLNode(Physical v) {
            value = v;
            prev = next = this;
        }

        private DLNode(Physical v, DLNode p, DLNode n) {
            value = v;
            prev = p;
            next = n;
        }

        public Physical value() {
            return value;
        }

        public DLNode next() {
            return next;
        }

        public DLNode prev() {
            return prev;
        }

        public void setValue(Physical v) {
            value = v;
        }

        public void setNext(DLNode n) {
            next = n;
        }

        public void setPrev(DLNode p) {
            prev = p;
        }

        public DLNode add(Physical v) {
            size++;
            return next = next.prev = new DLNode(v, this, next);
        }

        public DLNode remove() {
            next.prev = prev;
            prev.next = next;
            size--;
            return this;
        }
    }

    public PhysicalDoublyLinkedRingList() {
        //TODO: implement constructor.
        nil = new DLNode(null);
        size = 0;
    }

    /**
     * Creates a new list that is a copy of the specified list.
     * Later changes of {@code this} do not affect {@code list} and vice versa.
     *
     * @param list the list to copy; {@code list != null}
     */
    public PhysicalDoublyLinkedRingList(PhysicalDoublyLinkedRingList list) {

        //TODO: implement constructor.
        this.nil = new DLNode(null);
        this.size = 0;
        if (list == null) return;
        DLNode temp = list.nil.next;

        while (temp != list.nil) {
            this.addLast(temp.value());
            temp = temp.next;
        }

    }

    /**
     * Adds an element at the head of the list.
     *
     * @param p the element to add
     */
    public void addFirst(Physical p) {
        //TODO: implement method.
        nil.add(p);
    }

    /**
     * Adds an element at the end of the list.
     *
     * @param p the element to add
     */
    public void addLast(Physical p) {

        //TODO: implement method.
        nil.prev.add(p);
    }

    /**
     * Adds an element at the specified position in the list.
     *
     * <p>If {@code index == 0}, the element is inserted at the head of the list.
     * If {@code index == size()}, the element is inserted at the end of the list.</p>
     *
     * @param index the insertion index; {@code 0 <= index <= size()}
     * @param p     the element to add
     */
    public void add(int index, Physical p) {

        //TODO: implement method.
        if (index == 0) {
            addFirst(p);
            return;
        }
        if (index == size) {
            addLast(p);
            return;
        }
        DLNode temp = this.nil.next;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        DLNode newNode = new DLNode(p);
        temp.add(p);
    }

    /**
     * Removes and returns the head element of the list.
     *
     * @return the first element, or {@code null} if the list is empty
     */
    public Physical pollFirst() {

        //TODO: implement method.
        if (isEmpty()) return null;
        return nil.next().remove().value();
    }

    /**
     * Removes and returns the last element of the list.
     *
     * @return the last element, or {@code null} if the list is empty
     */
    public Physical pollLast() {

        //TODO: implement method.
        if (isEmpty()) return null;
        return nil.prev().remove().value();
    }

    /**
     * Returns the head element without removing it.
     *
     * @return the first element, or {@code null} if the list is empty
     */
    public Physical peekFirst() {

        //TODO: implement method.
        return nil.next().value();
    }

    /**
     * Returns the last element without removing it.
     *
     * @return the last element, or {@code null} if the list is empty
     */
    public Physical peekLast() {

        //TODO: implement method.
        return nil.prev().value();
    }

    /**
     * Returns whether this list contains the specified element (identical to `p`).
     *
     * @param p the element to search for
     * @return {@code true} if present, otherwise {@code false}
     */
    public boolean contains(Physical p) {

        //TODO: implement method.
        DLNode temp = nil;
        while (temp.next != nil) {
            if (temp.value() == p) return true;
            temp = temp.next;
        }
        return false;
    }

    /**
     * Returns the element at the specified position.
     *
     * @param index the index; {@code 0 <= index < size()}
     * @return the element at the specified position
     */
    public Physical get(int index) {

        //TODO: implement method.
        DLNode temp = nil.next;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.value();
    }

    /**
     * Removes and returns the element at the specified position.
     *
     * @param index the index; {@code 0 <= index < size()}
     * @return the removed element
     */
    public Physical remove(int index) {

        //TODO: implement method.
        DLNode temp = nil.next;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.remove().value();
    }

    /**
     * Removes all elements from the list.
     */
    public void clear() {
        while (!isEmpty()) {
            nil.next().remove();
        }
        size = 0;
        //TODO: implement method.
    }

    /**
     * Returns whether this list contains no elements.
     *
     * @return {@code true} if empty, otherwise {@code false}
     */
    public boolean isEmpty() {

        //TODO: implement method.
        return nil.next() == nil;
    }

    /**
     * Returns the number of elements currently stored in the list.
     *
     * @return the current size
     */
    public int size() {

        //TODO: implement method.
        return size;
    }
}

// TODO: define further classes, if needed (either here or in a separate file).

