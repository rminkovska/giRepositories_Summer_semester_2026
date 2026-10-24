/**
 * Singly linked list with elements of type {@link Vector2D}.
 *
 * <p>The list is implemented as a recursive data structure with one node
 * for each element in the list.</p>
 */
// TODO: define further classes and methods for the implementation of the singly linked list,
//  if needed. Do NOT use the Java Collection Framework in your implementation.
//
public class Vector2DSinglyLinkedList {

    // TODO: all variables and additional methods must be private.
    /**
     * Creates an empty list.
     */

    private Node head;
    private int size;

    private static class Node {
        Vector2D data;
        Node next;

        Node(Vector2D data) {
            this.data = data;
            this.next = null;
        }
    }

    public Vector2DSinglyLinkedList() {

        // TODO: implement constructor.
        this.head = null;
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
    public Vector2DSinglyLinkedList(Vector2DSinglyLinkedList list) {

        // TODO: implement constructor.

        this.head = list.head;
        this.size = list.size;

        Node temp = list.head;
        while (temp.next != null) {
            this.addFirst(temp.data);
            temp = temp.next;
        }

    }

    /**
     * Adds an element at the head of the list.
     *
     * @param v the element to add
     */
    public void addFirst(Vector2D v) {

        // TODO: implement method.
        Node newNode = new Node(v);
        newNode.next = head;
        head = newNode;
        size++;

    }

    /**
     * Adds an element at the end of the list.
     *
     * @param v the element to add
     */
    public void addLast(Vector2D v) {

        // TODO: implement method.
        Node newNode = new Node(v);

        if (head == null) {  //if the head is empty, make the new node head
            head = newNode;
            size++;
            return;
        }

        Node temp = head;  //else we traverse through the whole list
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;   //the last node's next is now our new node
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
     * @param v     the element to add
     */
    public void add(int index, Vector2D v) {

        // TODO: implement method.
        Node newNode = new Node(v);
        if (index < 0 || index > size) {
            return;
        }
        if (index == 0) {
            this.addFirst(v);
            return;
        }
        Node temp = head;

        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }

        newNode.next = temp.next;
        temp.next = newNode;

        size++;

    }

    /**
     * Removes and returns the head element of the list.
     *
     * <p>This method removes the first element of the list.</p>
     *
     * @return the first element in the list, or {@code null} if {@code size() == 0}
     */
    public Vector2D pollFirst() {

        // TODO: implement method.
        if (isEmpty()) {
            return null;
        }

        Node oldHead = this.head;
        this.head = this.head.next;
        size--;
        return oldHead.data;
    }

    /**
     * Removes and returns the last element of the list.
     *
     * <p>This method removes the last element of the list.</p>
     *
     * @return the last element in the list, or {@code null} if {@code size() == 0}
     */
    public Vector2D pollLast() {

        // TODO: implement method.
        if (isEmpty()) {
            return null;
        }
        Node temp = this.head;
        Node prev = temp;
        while (temp.next != null) {
            prev = temp;
            temp = temp.next;
        }
        Node last = temp;
        prev.next = null;
        size--;
        return last.data;
    }

    /**
     * Returns the head element of the list without removing it.
     *
     * <p>The list remains unchanged after this operation.</p>
     *
     * @return the first element in the list, or {@code null} if {@code size() == 0}
     */
    public Vector2D peekFirst() {

        // TODO: implement method.
        if (isEmpty()) {
            return null;
        }
        return head.data;
    }

    /**
     * Returns the last element of the list without removing it.
     *
     * <p>The list remains unchanged after this operation.</p>
     *
     * @return the last element in the list, or {@code null} if {@code size() == 0}
     */
    public Vector2D peekLast() {

        // TODO: implement method.
        if (isEmpty()) {
            return null;
        }

        Node temp = this.head;
        while (temp.next != null) {
            temp = temp.next;
        }

        return temp.data;
    }

    /**
     * Returns whether this list contains an element equal to the specified element.
     *
     * <p>The list is searched from head to tail. Equality is determined
     * using {@code equals}.</p>
     *
     * @param v the element to search for
     * @return {@code true} if this list contains an element equal to {@code v},
     * {@code false} otherwise
     */
    public boolean contains(Vector2D v) {

        // TODO: implement method.

        Node temp = this.head;
        while (temp.next != null) {
            if (temp.data.equals(v)) {
                return true;
            }
            temp = temp.next;
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
    public Vector2D get(int index) {

        // TODO: implement method.
        if (index < 0 || index > size) {
            return null;
        }
        if (head == null) {
            return null;
        }
        if (index == 0) {
            return head.data;
        }
        Node temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        return temp.next.data;
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
    public Vector2D remove(int index) {

        // TODO: implement method.
        if (index < 0 || index > size) {
            return null;
        }
        if (index == 0) {
            size--;
            return this.head.data;
        }

        Node temp = this.head;
        Node previous = this.head;
        Node newNext;
        if (index == size - 1) {
            while (temp.next != null) {
                temp = temp.next;
            }
            size--;
            previous = temp;
            temp = null;
            return previous.data;
        }
        for (int i = 0; i < index; i++) {
            previous = temp;
            temp = temp.next;
        }
        newNext = temp.next;
        Node indexToRemove = temp;
        previous.next = newNext;
        size--;
        return indexToRemove.data;
    }

    /**
     * Returns whether this list contains no elements.
     *
     * @return {@code true} if {@code size() == 0}, {@code false} otherwise
     */
    public boolean isEmpty() {

        // TODO: implement method.
        return size() == 0;
    }

    /**
     * Returns the number of elements currently stored in the list.
     *
     * @return the current size of the list
     */
    public int size() {

        // TODO: implement method.
        return size;
    }
}

// TODO: define further classes, if needed (either here or in a separate file).