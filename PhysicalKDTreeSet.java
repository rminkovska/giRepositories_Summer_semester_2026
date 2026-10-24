/**
 * A two-dimensional k-d tree for spatial indexing of {@link Physical} objects.
 *
 * <p>This data structure organizes physical objects according to their positions
 * in a two-dimensional space. It allows efficient spatial queries such as
 * finding all objects within a given radius or region.
 * The structure alternates between splitting along the x-axis and y-axis at
 * successive levels.</p>
 *
 * <p>The tree does not store {@code null} elements.</p>
 */
public class PhysicalKDTreeSet {

    private KDNode root;
    private int size;

    /**
     * Creates an empty k-d tree.
     */
    public PhysicalKDTreeSet() {
        root = null;
        size = 0;
    }

    /**
     * Creates a new k-d tree containing all elements of the given array.
     *
     * @param physicals the source array;
     *                  {@code physicals != null && physicals[i] != null} for all valid i
     */
    public PhysicalKDTreeSet(Physical[] physicals) {

        add(physicals);
    }

    /**
     * Inserts the specified physical object into this k-d tree. If `p` is already
     * contained (see method `contains`) in this tree, the method does nothing.
     *
     * <p>The new object is inserted according to the recursive splitting rule
     * of the k-d tree. At odd depths, objects are compared by x-coordinate;
     * at even depths, they are compared by y-coordinate.</p>
     *
     * @param p the object to insert; {@code p != null}
     */
    public void add(Physical p) {

        //TODO: add check for duplicates according to `contains`.
        if (contains(p)) return;
        if (root == null) {
            root = new KDNode(p);
        } else {
            root.add(p, 1);
        }
        size++;
    }

    /**
     * Adds the physical objects of the specified array to this tree. Objects that are already
     * contained in this tree (see method `contains`) are not added.
     *
     * @param physicals the source array;
     *                  {@code physicals != null && physicals[i] != null} for all valid i
     */
    public void add(Physical[] physicals) {

        for (Physical p : physicals) {
            add(p);
        }
    }

    /**
     * Returns whether this tree contains the specified physical object.
     *
     * <p>This method checks for object identity, not structural equality.
     * That is, it returns {@code true} if and only if the exact same object
     * reference (using {@code ==}) is stored in this tree.</p>
     *
     * @param p the object to search for; {@code p != null}
     * @return {@code true} if {@code p} is contained in this tree,
     * otherwise {@code false}
     */
    public boolean contains(Physical p) {

        //TODO: implement method.
        if (p == null) return false;
        if (root == null) return false;
        return root.contains(p, 1);
    }

    /**
     * Returns the number of elements currently stored in this tree.
     *
     * @return the number of stored elements
     */
    public int size() {
        return size;
    }

    /**
     * Removes all elements from this tree.
     *
     * <p>After this operation, {@link #isEmpty()} returns {@code true}.</p>
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns whether this tree contains no elements.
     *
     * @return {@code true} if {@code size() == 0}, otherwise {@code false}
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns all physical objects whose center lies within the specified radius
     * around the given position.
     *
     * <p>This method performs a circular range query. Only objects whose center
     * is within the given distance from {@code center} are returned.</p>
     *
     * <p>The returned list is independent of the internal structure of the tree.
     * Modifying the list does not affect the tree.</p>
     *
     * @param center the query center; {@code center != null}
     * @param radius the query radius; {@code radius >= 0}
     * @return a list containing all matching objects (possibly empty)
     */
    public PhysicalDoublyLinkedRingList rangeQuery(Vector2D center, double radius) {

        PhysicalDoublyLinkedRingList result = new PhysicalDoublyLinkedRingList();
        if (root != null) {
            root.rangeQuery(center, radius, radius * radius, 1, result);
        }
        return result;
    }

    /**
     * Returns all physical objects whose center lies within the specified
     * axis-aligned rectangle.
     *
     * <p>The rectangle is defined by its minimum and maximum coordinates.</p>
     *
     * @param minX the minimum x-coordinate
     * @param minY the minimum y-coordinate
     * @param maxX the maximum x-coordinate
     * @param maxY the maximum y-coordinate
     * @return a list containing all matching objects (possibly empty)
     */
    public PhysicalDoublyLinkedRingList rangeQuery(double minX, double minY, double maxX, double maxY) {

        PhysicalDoublyLinkedRingList result = new PhysicalDoublyLinkedRingList();
        if (root != null) {
            root.rangeQueryRectangle(minX, minY, maxX, maxY, 1, result);
        }
        return result;
    }

    /**
     * Returns all physical objects that intersect a hypothetical circle
     * defined by the specified position and radius.
     *
     * <p>This method first performs a spatial range query to collect nearby
     * objects, and then filters them using geometric intersection.</p>
     *
     * <p>This method is useful for collision detection at a candidate position.</p>
     *
     * @param position the center of the hypothetical object; {@code position != null}
     * @param radius   the radius of the hypothetical object; {@code radius >= 0}
     * @return a list of all intersecting objects (possibly empty)
     */
    public PhysicalDoublyLinkedRingList intersectingAt(Vector2D position, double radius) {

        PhysicalDoublyLinkedRingList nearby = rangeQuery(position, radius + getMaxStoredRadius());

        PhysicalDoublyLinkedRingList result = new PhysicalDoublyLinkedRingList();

        for (int i = 0; i < nearby.size(); i++) {
            Physical p = nearby.get(i);
            if (p.intersects(position, radius)) {
                result.addLast(p);
            }
        }

        return result;
    }

    /**
     * Returns the maximum radius among all stored physical objects.
     */
    public double getMaxStoredRadius() {

        //TODO: implement method.
        if (root == null) return 0;
        return root.maxRadius();
    }

    /**
     * Node of a k-d tree storing a {@link Physical} object.
     */
    class KDNode {

        private final Physical value;
        private KDNode left;
        private KDNode right;

        //TODO: additional public methods or changes of existing methods if needed.

        public boolean contains(Physical p, int depth) {
            if (value == p) return true;
            int cmp = compare(p, value, depth % 2);
            if (cmp == -1) {
                return left != null && left.contains(p, depth + 1);
            } else return right != null && right.contains(p, depth + 1);
        }

        public double maxRadius() {
            double maximal = value.getRadius();
            if (left != null) {
                maximal = Math.max(maximal, left.maxRadius());
            } if(right != null) maximal = Math.max(maximal, right.maxRadius());
            return maximal;
        }

        /**
         * Creates a new node storing the specified physical object.
         *
         * @param value the object to store; {@code value != null}
         */
        public KDNode(Physical value) {
            this.value = value;
        }

        /**
         * Inserts the specified physical object into the subtree rooted at this node.
         *
         * @param p     the object to insert; {@code p != null}
         * @param depth the current depth in the tree (root starts at depth 1)
         */
        public void add(Physical p, int depth) {

            int axis = depth % 2;

            if (compare(p, value, axis) < 0) {
                if (left == null) {
                    left = new KDNode(p);
                } else {
                    left.add(p, depth + 1);
                }
            } else {  //includes compare(p, value, axis) == 0
                if (right == null) {
                    right = new KDNode(p);
                } else {
                    right.add(p, depth + 1);
                }
            }
        }

        /**
         * Performs a circular range query on the subtree rooted at this node.
         *
         * <p>All {@link Physical} objects whose center lies within the specified
         * radius around the given query center are added to {@code result}.</p>
         *
         * @param center        the center of the query circle; {@code center != null}
         * @param radius        the radius of the query circle; {@code radius >= 0}
         * @param radiusSquared the squared radius (used to avoid computing square roots)
         * @param depth         the current depth in the tree (root starts at depth 1)
         * @param result        the list to which matching objects are added; {@code result != null}
         */
        public void rangeQuery(Vector2D center, double radius, double radiusSquared, int depth, PhysicalDoublyLinkedRingList result) {

            Vector2D p = value.getPosition();

            double dx = p.getX() - center.getX();
            double dy = p.getY() - center.getY();
            double dist2 = dx * dx + dy * dy;

            if (dist2 <= radiusSquared) {
                result.addLast(value);
            }

            int axis = depth % 2;

            double centerCoord = axis == 1 ? center.getX() : center.getY();
            double nodeCoord = axis == 1 ? p.getX() : p.getY();

            if (centerCoord - radius <= nodeCoord && left != null) {
                left.rangeQuery(center, radius, radiusSquared, depth + 1, result);
            }

            if (centerCoord + radius >= nodeCoord && right != null) {
                right.rangeQuery(center, radius, radiusSquared, depth + 1, result);
            }
        }

        /**
         * Performs an axis-aligned rectangular range query on the subtree rooted at this node.
         *
         * <p>All {@link Physical} objects whose center lies within the specified
         * rectangle are added to {@code result}. The rectangle is defined by its
         * minimum and maximum coordinates.</p>
         *
         * @param minX   the minimum x-coordinate of the query rectangle
         * @param minY   the minimum y-coordinate of the query rectangle
         * @param maxX   the maximum x-coordinate of the query rectangle
         * @param maxY   the maximum y-coordinate of the query rectangle
         * @param depth  the current depth in the tree (root starts at depth 1)
         * @param result the list to which matching objects are added; {@code result != null}
         */
        public void rangeQueryRectangle(double minX, double minY, double maxX, double maxY, int depth, PhysicalDoublyLinkedRingList result) {

            Vector2D p = value.getPosition();
            double x = p.getX();
            double y = p.getY();

            if (x >= minX && x <= maxX && y >= minY && y <= maxY) {
                result.addLast(value);
            }

            int axis = depth % 2;

            if (axis == 1) {
                if (minX <= x && left != null) {
                    left.rangeQueryRectangle(minX, minY, maxX, maxY, depth + 1, result);
                }
                if (maxX >= x && right != null) {
                    right.rangeQueryRectangle(minX, minY, maxX, maxY, depth + 1, result);
                }
            } else {
                if (minY <= y && left != null) {
                    left.rangeQueryRectangle(minX, minY, maxX, maxY, depth + 1, result);
                }
                if (maxY >= y && right != null) {
                    right.rangeQueryRectangle(minX, minY, maxX, maxY, depth + 1, result);
                }
            }
        }

        /**
         * Compares two {@link Physical} objects with respect to the specified axis.
         *
         * <p>The comparison is based on the coordinate of the objects' positions
         * along the selected axis:</p>
         * <ul>
         *   <li>If {@code axis == 1}, the x-coordinate is compared.</li>
         *   <li>Otherwise, the y-coordinate is compared.</li>
         * </ul>
         *
         * <p>The method returns a negative value if {@code a} is smaller than {@code b},
         * a positive value if {@code a} is greater than {@code b}, and {@code 0} if both
         * coordinates are equal.</p>
         *
         * @param a    the first object; {@code a != null}
         * @param b    the second object; {@code b != null}
         * @param axis the axis used for comparison (1 for x-axis, otherwise y-axis)
         * @return a negative value if {@code a < b}, a positive value if {@code a > b},
         * or {@code 0} if both are equal with respect to the selected axis
         */
        private int compare(Physical a, Physical b, int axis) {

            double a1 = axis == 1 ? a.getPosition().getX() : a.getPosition().getY();
            double b1 = axis == 1 ? b.getPosition().getX() : b.getPosition().getY();

            if (a1 < b1) return -1;
            if (a1 > b1) return 1;

            return 0;
        }
    }
}


