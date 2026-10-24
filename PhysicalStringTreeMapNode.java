/**
 * Node of a binary search tree storing key-value pairs.
 * <p>
 * TODO: change this file according to `Teilaufgabe 3` in `angabe.md`.
 */
public class PhysicalStringTreeMapNode {

    private Physical key;
    private String value;
    private PhysicalComparator comparator;

    private PhysicalStringTreeMapNode left;
    private PhysicalStringTreeMapNode right;

    /**
     * Creates a new node with the specified key-value pair. The key order relation at this node
     * is according to the x-coordinate of the {@link Physical} object. If the x-coordinates are equal,
     * the y-coordinate is used as a tie-breaker.
     *
     * @param key   the key stored in this node; {@code key != null}
     * @param value the associated value; {@code value != null}
     */
    public PhysicalStringTreeMapNode(Physical key, String value) {
        this(key, value, new XComparator());
    }

    /**
     * Returns a string representation of this subtree in the same format as
     * described in {@link PhysicalStringTreeMap#toString()}, using the specified
     * indentation.
     *
     * @param indent the indentation prefix for this node
     * @return a string representation of this subtree
     */
    public String toIndentedString(String indent) {

        //TODO: implement method.
        StringBuilder sb = new StringBuilder();
        sb.append(indent);
        sb.append(key.toString()).append(" -> ").append(this.value).append("\n");
        if (this.left != null) {
            sb.append(this.left.toIndentedString(indent + "  "));
        }
        if (this.right != null) {
            sb.append(this.right.toIndentedString(indent + "  "));
        }
        return sb.toString();
    }

    /**
     * Creates a new node with the specified key-value pair and a specified comparator.
     *
     * @param key        the key stored in this node; {@code key != null}
     * @param value      the associated value; {@code value != null}
     * @param comparator the comparator defining the key order; {@code comparator != null}
     */
    public PhysicalStringTreeMapNode(Physical key, String value, PhysicalComparator comparator) {
        this.key = key;
        this.value = value;
        this.comparator = comparator;
    }

    /**
     * Compares two keys lexicographically by position (x, then y).
     */
    private int compare(Physical a, Physical b) {
        /*int cmpX = Double.compare(a.getPosition().getX(), b.getPosition().getX());
        if (cmpX != 0) {
            return cmpX;
        }
        return Double.compare(a.getPosition().getY(), b.getPosition().getY());*/
        return comparator.compare(a, b);
    }

    /**
     * Inserts a key-value pair into this subtree or updates an existing entry.
     *
     * <p>If the exact same key object is already stored in this subtree
     * (that is, if {@code k == key}), its associated value is replaced by {@code a},
     * and the previous value is returned.</p>
     *
     * <p>If no such key object exists, a new node is created and inserted at the
     * appropriate position according to the comparator.</p>
     *
     * <p>If the comparator determines that {@code k} and {@code key} have the same
     * coordinates, but the object references differ, the new key is treated as a
     * distinct entry and is inserted into the right subtree.</p>
     *
     * @param k the key to insert; {@code k != null}
     * @param a the value to associate with the key; {@code a != null}
     * @return the previously associated value if the same key object was already stored,
     * otherwise {@code null}
     */
    public String put(Physical k, String a) {

        if (k == key) {
            String toReturn = value;
            value = a;
            return toReturn;
        }

        int cmp = compare(k, key);

        if (cmp < 0) {
            if (left == null) {
                left = new PhysicalStringTreeMapNode(k, a, this.comparator);
                return null;
            } else {
                return left.put(k, a);
            }
        } else {
            if (right == null) {
                right = new PhysicalStringTreeMapNode(k, a, this.comparator);
                return null;
            } else {
                return right.put(k, a);
            }
        }
    }

    /**
     * Returns the value associated with the given key object.
     *
     * @param k the key to search for; {@code k != null}
     * @return the associated value if the same key object is found,
     * otherwise {@code null}
     */
    public String get(Physical k) {

        if (k == key) {
            return value;
        }

        int cmp = compare(k, key);

        if (cmp < 0) {
            return left == null ? null : left.get(k);
        } else {
            return right == null ? null : right.get(k);
        }
    }

    /**
     * Returns whether this subtree contains the specified key object.
     *
     * <p>This method checks object identity, not coordinate equality.
     * That is, it returns {@code true} if and only if the exact same
     * key object reference (using {@code ==}) is stored in this subtree.</p>
     *
     * <p>If multiple keys share the same coordinates, only the matching
     * object reference is considered a match.</p>
     *
     * @param k the key to search for; {@code k != null}
     * @return {@code true} if the same key object exists in this subtree,
     * otherwise {@code false}
     */
    public boolean containsKey(Physical k) {

        if (k == key) {
            return true;
        }

        int cmp = comparator.compare(k, key);

        if (cmp < 0) {
            return left != null && left.containsKey(k);
        } else {
            return right != null && right.containsKey(k);
        }
    }

    /**
     * Returns whether the given string exists as a value in this subtree.
     *
     * @param v the value to search for; {@code v != null}
     * @return {@code true} if a node in this subtree stores a value equal to {@code v},
     * otherwise {@code false}
     */
    public boolean containsValue(String v) {
        if (value.equals(v)) {
            return true;
        }
        return (left != null && left.containsValue(v))
                || (right != null && right.containsValue(v));
    }

    /**
     * Adds all keys in ascending order to the given list.
     *
     * <p>The keys are added according to an in-order traversal of this subtree,
     * resulting in ascending order with respect to the key order.</p>
     *
     * @param result the list to which keys are appended; {@code result != null}
     */
    public void fillKeysInOrder(PhysicalSinglyLinkedList result) {
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
     * <p>This method ensures that no write operation exceeds the bounds of the array.
     * If the array does not provide enough space to store all remaining values,
     * only as many values as fit into the array are written.</p>
     *
     * @param result the target array; {@code result != null}
     * @param index  the index at which to start writing; {@code 0 <= index <= result.length}
     * @return the index of the first array position that was not written by this method
     */
    public int fillValues(String[] result, int index) {
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
}