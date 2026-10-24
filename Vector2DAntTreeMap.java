/**
 * A simple associative data structure (map) mapping {@link Vector2D} keys
 * to {@link Ant} values.
 *
 * <p>This class represents a minimal key-value association structure.
 * Each {@code Vector2D} key is associated with exactly one {@code Ant} value.
 * Internally, the associations are stored in a binary search tree.</p>
 *
 * <p>The tree is ordered lexicographically by the coordinates of the keys:
 * first by x-coordinate, and if these are equal, by y-coordinate.
 * Example: (1.0,2.0) < (1.0,3.0) < (1.1, 0.0)</p>
 */
public class Vector2DAntTreeMap {

    // TODO: all variables and additional methods must be private.

    /**
     * Creates an empty map (with no associations).
     */

    private int size;
    private Node root;

    private static class Node {
        Vector2D key;
        Ant value;
        Node left;
        Node right;

        private Node(Vector2D key, Ant value) {
            this.key = key;
            this.value = value;
        }
    }

    public Vector2DAntTreeMap() {

        // TODO: implement constructor.
        this.size = 0;
        this.root = null;
    }

    /**
     * Inserts a new association into the map.
     *
     * <p>If the specified key already exists, its associated value
     * is replaced by the given value.</p>
     *
     * @param k the key; {@code k != null}
     * @param a the value; {@code a != null}
     * @return the previously associated value, or {@code null} if none existed
     */
    public Ant put(Vector2D k, Ant a) {

        // TODO: implement method.
        Ant oldValue = get(k);
        this.root = put(this.root, k, a);
        if(oldValue == null) size++;
        return oldValue;
    }

    private Node put(Node n, Vector2D key, Ant value) {
        if (n == null) {
            return new Node(key, value);
        }
        if (compare(key, n.key) == -1) {
            n.left = put(n.left, key, value);
        }
        if (compare(key, n.key) == 1) {
            n.right = put(n.right, key, value);
        }
        if (compare(key, n.key) == 0) {
            n.value = value;
        }
        return n;
    }


    private int compare(Vector2D v1, Vector2D v2) {
        if (v1.getX() == v2.getX()) {
            if (v1.getY() < v2.getY()) {
                return -1;
            }
            if (v1.getY() > v2.getY()) {
                return 1;
            } else {
                return 0;
            }
        }
        if (v1.getX() < v2.getX()) {
            return -1;
        }
        return 1;
    }

    /**
     * Returns the value associated with the given key.
     *
     * @param k the key to search for; {@code k != null}
     * @return the associated {@link Ant}, or {@code null} if the key is not present
     */
    public Ant get(Vector2D k) {

        // TODO: implement method.
        if (!containsKey(k)) return null;
        Node searchedNode = findNodeByKey(root, k);
        return searchedNode.value;
    }

    private Node findNodeByKey(Node n, Vector2D key) {
        if (n == null) {
            return null;
        }
        if (compare(key, n.key) == 0) {
            return n;
        }
        if (compare(key, n.key) == -1) {
            return findNodeByKey(n.left, key);
        }
        if (compare(key, n.key) == 1) {
            return findNodeByKey(n.right, key);
        }
        return null;
    }


    /**
     * Returns whether this map contains the given key, meaning it contains a key with
     * the same coordinates as the specified {@code k}.
     *
     * @param k the key to check; {@code k != null}
     * @return {@code true} if the key exists in the map,
     * {@code false} otherwise
     */
    public boolean containsKey(Vector2D k) {

        // TODO: implement method.
        return containsKey(root, k);
    }

    private boolean containsKey(Node n, Vector2D key) {  //helper method, which uses a node
        if (n == null) {
            return false;
        }
        if (compare(key, n.key) == 0) {
            return true;
        }
        if (compare(key, n.key) == -1) {
            return containsKey(n.left, key);
        }
        if (compare(key, n.key) == 1) {
            return containsKey(n.right, key);
        }
        return false;
    }

    /**
     * Returns whether this map contains the given value.
     *
     * <p>The tree is searched recursively.</p>
     *
     * @param v the value to check; {@code v != null}
     * @return {@code true} if the value exists in the map,
     * {@code false} otherwise
     */

    public boolean containsValue(Ant v) {

        // TODO: implement method.
        return containsValue(root, v);
    }

    private boolean containsValue(Node n, Ant v) {   //help method to check recursively
        if (n == null) {
            return false;
        }
        if (n.value.equals(v)) {
            return true;
        }
        return containsValue(n.left, v) || containsValue(n.right, v);
    }

    /**
     * Returns the number of stored associations.
     *
     * @return the number of key-value pairs stored in this map
     */
    public int size() {

        // TODO: implement method.
        return size;
    }

    /**
     * Returns all keys stored in this map.
     *
     * <p>The returned list contains all {@link Vector2D} keys currently
     * stored in the map. Each key appears exactly once in the list.
     * The keys are returned in ascending order according to the key order
     * of this tree map. The smallest key appears at index {@code 0}
     * of the returned list.</p>
     *
     * @return a list containing all stored keys
     */
    public Vector2DSinglyLinkedList keys() {

        // TODO: implement method.
        Vector2DSinglyLinkedList keys = new Vector2DSinglyLinkedList();
        keysList(keys, root);
        return keys;

    }

    private void keysList(Vector2DSinglyLinkedList keys, Node root) {   //helper method to add into the list
        if (root == null) {
            return;
        }
        keysList(keys, root.left);
        keys.addLast(root.key);
        keysList(keys, root.right);
    }

    /**
     * Returns all values stored in this map.
     *
     * <p>The returned array contains all values currently stored in the map.
     * Duplicate entries may occur if different keys are associated with equal
     * values. The order is not specified.</p>
     *
     * @return an array containing all stored values
     */
    public Ant[] values() {

        // TODO: implement method.
        Ant[] ans = new Ant[size()];
        filler(root, ans, 0);
        return ans;
    }

    private int filler(Node n, Ant[] a, int index) {
        if (n == null) {
            return index;
        }
        index = filler(n.left, a, index);
        a[index] = n.value;
        index++;
        return filler(n.right, a, index);
    }
}

// TODO: define further classes, if needed (either here or in a separate file).