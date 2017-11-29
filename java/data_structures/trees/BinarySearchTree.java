package data_structures.trees;

/**
 * Unless specified otherwise all comments are direct quotations from
 * https://en.wikipedia.org/wiki/Binary_search_tree
 * <p>
 * Binary search trees (BST), sometimes called ordered or sorted binary trees,
 * are a particular type of container: data structures that store "items" (such
 * as numbers, names etc.) in memory.
 * <p>
 * They allow fast lookup, addition and removal of items, and can be used to
 * implement either dynamic sets of items, or lookup tables that allow finding
 * an item by its key (e.g., finding the phone number of a person by name).
 * <p>
 * Binary search trees keep their keys in sorted order, so that lookup and other
 * operations can use the principle of binary search: when looking for a key in
 * a tree (or a place to insert a new key), they traverse the tree from root to
 * leaf, making comparisons to keys stored in the nodes of the tree and
 * deciding, on the basis of the comparison, to continue searching in the left
 * or right subtrees.
 * <p>
 * On average, this means that each comparison allows the operations to skip
 * about half of the tree, so that each lookup, insertion or deletion takes time
 * proportional to the logarithm of the number of items stored in the tree. This
 * is much better than the linear time required to find items by key in an
 * (unsorted) array, but slower than the corresponding operations on hash
 * tables.
 * <p>
 * Several variants of the binary search tree have been studied in computer
 * science; this article deals primarily with the basic type, making references
 * to more advanced types when appropriate.
 *
 * @param <I>
 * @param <V>
 */

@SuppressWarnings({"unused", "ClassWithoutLogger", "PublicConstructor", "ParameterHidesMemberVariable", "ClassTooDeepInInheritanceTree", "InstanceMethodNamingConvention", "DesignForExtension"})
class BinarySearchTree<I extends Comparable<I>, V> extends BinaryTree<I, V> {

    /**
     * Call the parent constructor.
     *
     * @param id identifier that is Comparable
     * @param value object you want to store in the Node
     */

    @SuppressWarnings("WeakerAccess")
    BinarySearchTree(final I id, final V value) {
        super(id, value);
    }

    /**
     * Add a new element to the BinarySearchTree.
     *
     * @param id a unique identifier that is not already in the
     * BinarySearchTree
     * @param value a value you want to store in a newly created node
     * @return false if adding failed ie such an id is already present, true
     * otherwise
     */

    @SuppressWarnings({"ComparatorResultComparison", "ConstantConditions", "WeakerAccess", "MethodWithMultipleReturnPoints"})
    boolean add(final I id, final V value) {

        // an element with this id is already present and is the root
        if (id.compareTo(getId().get()) == 0) return false;

        // if less than root.value, focus on left node
        if (id.compareTo(getId().get()) == -1) {
            if (getLeft().isPresent())
                return ((BinarySearchTree<I, V>) getLeft().get())
                        .add(id, value);
                // on null
            else setLeft(new BinarySearchTree<>(id, value));

            // if greater than root.value, focus on right node
        } else {
            if (getRight().isPresent())
                return ((BinarySearchTree<I, V>) getRight().get())
                        .add(id, value);
                // on null
            else setRight(new BinarySearchTree<>(id, value));
        }
        // returned by recursive calls to add()
        return true;
    }
}
