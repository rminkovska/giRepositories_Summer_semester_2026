/**
 * Empty subtree of a {@link PhysicalTreeSet}.
 *
 * <p>This class represents an empty binary-search-tree subtree.</p>
 */
public class PhysicalTreeSetNodeEmpty implements PhysicalTreeSetNode // TODO: activate clause.
{

    // TODO: all object variables and additional constructors and methods are private,
    //  unless a method overrides or implements an inherited public method.


    public Physical getValue() {
        return null;
    }
    public PhysicalTreeSetNode getLeft() {
        return null;
    }
    public PhysicalTreeSetNode getRight() {
        return null;
    }
    public boolean contains(Physical p, PhysicalComparator comparator) {
        return false;
    }
    public PhysicalTreeSetNode insert(Physical p, PhysicalComparator comparator) {
        return new PhysicalTreeSetNodeNonEmpty(p, comparator);
    }
    public int size() {
        return 0;
    }
    public Physical iter(PhysicalTreeIterator iterator, boolean next){
        return null;
    }
}