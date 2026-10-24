/**
 * Node of a binary search tree storing {@link Physical} key-value pairs.
 *
 * <p>The subtree is ordered according to a {@link PhysicalComparator}.
 * Search and insertion decisions use this order relation.</p>
 *
 * TODO: change this file according to `angabe.md`.
 */
public class PhysicalPhysicalTreeMapNode implements PhysicalIterableTreeNode //TODO: activate clause.
{

    private Physical key;
    private Physical value;

    private PhysicalPhysicalTreeMapNode left;
    private PhysicalPhysicalTreeMapNode right;
    private PhysicalComparator comparator;

    /**
     * Creates a new node with the specified key-value pair. The key order relation at this node
     * is according to {@link XComparator}.
     *
     * @param key the key stored in this node; {@code key != null}
     * @param value the associated value; {@code value != null}
     */
    public PhysicalPhysicalTreeMapNode(Physical key, Physical value) {
        this.key = key;
        this.value = value;
        this.comparator = new XComparator();
    }

    /**
     * Creates a new node with the specified key-value pair and a specified comparator.
     *
     * @param key the key stored in this node; {@code key != null}
     * @param value the associated value; {@code value != null}
     * @param comparator the comparator defining the key order; {@code comparator != null}
     */
    public PhysicalPhysicalTreeMapNode(Physical key, Physical value, PhysicalComparator comparator) {
        this.key = key;
        this.value = value;
        this.comparator = comparator;
    }

    /**
     * Inserts a key-value pair into this subtree or updates an existing entry.
     *
     * <p>If a key object equal to the specified key is already stored in this subtree,
     * its associated value is replaced by
     * {@code value}, and the previous value is returned.</p>
     *
     * <p>Otherwise, a new node is inserted according to the comparator-defined
     * search tree order.</p>
     *
     * @param key the key to insert; {@code key != null}
     * @param value the associated value; {@code value != null}
     * @return the previously associated value if the same key object already existed,
     *         otherwise {@code null}
     */
    public Physical put(Physical key, Physical value) {

        if (this.key.equals(key)) {
            Physical old = this.value;
            this.value = value;
            return old;
        }

        int cmp = this.comparator.compare(key, this.key);

        if (cmp < 0) {
            if (left == null) {
                left = new PhysicalPhysicalTreeMapNode(key, value, comparator);
                return null;
            } else {
                return left.put(key, value);
            }
        } else {
            if (right == null) {
                right = new PhysicalPhysicalTreeMapNode(key, value, comparator);
                return null;
            } else {
                return right.put(key, value);
            }
        }
    }

    /**
     * Returns the value associated with the given key object.
     *
     * @param key the key to search for; {@code key != null}
     * @return the associated value if the same key object is found,
     *         otherwise {@code null}
     */
    public Physical get(Physical key) {

        if (this.key.equals(key)) {
            return value;
        }

        int cmp = this.comparator.compare(key, this.key);

        if (cmp < 0) {
            return left == null ? null : left.get(key);
        } else {
            return right == null ? null : right.get(key);
        }
    }

    /**
     * Returns whether this subtree contains a key equal to the specified key object.
     *
     * @param key the key to search for; {@code key != null}
     * @return {@code true} iff the same key object occurs in this subtree
     */
    public boolean containsKey(Physical key) {

        if (this.key.equals(key)) {
            return true;
        }

        int cmp = this.comparator.compare(key, this.key);

        if (cmp < 0) {
            return left != null && left.containsKey(key);
        } else {
            return right != null && right.containsKey(key);
        }
    }

    /**
     * Returns whether the given physical object is equal to a value in this subtree.
     *
     * @param value the value to search for; {@code value != null}
     * @return {@code true} iff the same value object occurs in this subtree
     */
    public boolean containsValue(Physical value) {

        if (this.value.equals(value)) {
            return true;
        }

        return (left != null && left.containsValue(value))
                || (right != null && right.containsValue(value));
    }

    /**
     * Adds all keys in ascending order to the given list.
     *
     * <p>The keys are added according to an in-order traversal of this subtree,
     * resulting in ascending order with respect to the key order.</p>
     *
     * @param result the list to which keys are appended; {@code result != null}
     */
    public void fillKeysInOrder(PhysicalDoublyLinkedList result) {

        if (left != null) {
            left.fillKeysInOrder(result);
        }

        result.addLast(key);

        if (right != null) {
            right.fillKeysInOrder(result);
        }
    }

    /**
     * Writes all values of this subtree into the specified array in ascending key order.
     *
     * <p>The values are written according to an in-order traversal. Writing starts at
     * the specified index and proceeds sequentially.</p>
     *
     * @param result the target array; {@code result != null}
     * @param index the index at which to start writing; {@code 0 <= index <= result.length}
     * @return the index of the first array position that was not written by this method
     */
    public int fillValues(Physical[] result, int index) {

        if (left != null) {
            index = left.fillValues(result, index);
        }

        if (index < result.length) {
            result[index++] = value;
        }

        if (right != null) {
            index = right.fillValues(result, index);
        }

        return index;
    }

    /**
     * Returns a string representation of this subtree using the specified indentation.
     *
     * @param indent the indentation prefix for this node
     * @return a string representation of this subtree
     */
    public String toIndentedString(String indent) {

        String result = indent + key.toString() + " -> " + value.toString() + "\n";

        if (left != null) {
            result += left.toIndentedString(indent + "  ");
        }

        if (right != null) {
            result += right.toIndentedString(indent + "  ");
        }

        return result;
    }

    public Physical iter(PhysicalTreeIterator iterator, boolean next){
        if(!next){
            PhysicalPhysicalTreeMapNode node = this;
            while (node != null) {
                new PhysicalTreeIterator(node, iterator);
                node = node.right;
            }
            return null;
        }
        else{
            PhysicalPhysicalTreeMapNode node = this.left;
            while (node != null) {
                new PhysicalTreeIterator(node, iterator);
                node = node.right;
            }
        }
        return this.key;
    }
}