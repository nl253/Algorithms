package data_structures;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * In mathematics, two sets are said to be disjoint sets if they have no element
 * in common. Equivalently, disjoint sets are sets whose intersection is the
 * empty set. source: https://en.wikipedia.org/wiki/Disjoint_sets
 * <p>
 * Disjoint-set data structure, also called a union–find data structure or
 * merge–find set, is a data structure that keeps track of a set of elements
 * partitioned into a number of disjoint (non-overlapping) subsets.
 * <p>
 * It provides near-constant-time operations (bounded by the inverse Ackermann
 * function) to add new sets, to merge existing sets, and to determine whether
 * elements are in the same set. In addition to many other uses (see the
 * Applications section), disjoint-sets play a key role in Kruskal's algorithm
 * for finding the minimum spanning tree of a graph.
 */

@SuppressWarnings({"ReturnOfInnerClass", "MethodReturnOfConcreteClass", "rawtypes", "DesignForExtension", "unused", "LocalCanBeFinal", "ImplicitCallToSuper", "LocalVariableOfConcreteClass", "ClassWithoutLogger", "PackageVisibleField", "InstanceVariableNamingConvention", "ParameterHidesMemberVariable"})
class DisjointSet<E> {

    private static final int INITIAL_CAPACITY = 20;
    private Set<Element<E>> items;


    DisjointSet() {
        items = new HashSet<>(INITIAL_CAPACITY);
    }

    /**
     * @param newItems inital items in the Set, automatically passed to
     * makeSet().
     */

    @SuppressWarnings("ImplicitCallToSuper")
    DisjointSet(Iterable<E> newItems) {
        makeSet(newItems);
    }

    @SuppressWarnings({"LocalCanBeFinal", "ReturnOfNull", "MethodWithMultipleReturnPoints"})
    Element<E> find(E item) {
        Element<E> result = null;
        for (Element<E> element : items)
            if (item.equals(element.data)) result = element.find();
        return result;
    }

    /**
     * The MakeSet operation makes a new set by creating a new element with a
     * unique id, a rank of 0, and a parent pointer to itself.
     * <p>
     * The parent pointer to itself indicates that the element is the
     * representative member of its own set.
     * <p>
     * The MakeSet operation has O(1) time complexity.
     *
     * @param items you want to insert
     */

    private void makeSet(Iterable<E> items) {
        items.forEach(x -> this.items.add(new Element<>(x)));
    }

    /**
     * A disjoint-set forest consists of a number of elements each of which
     * stores an id, a parent pointer, and, in efficient algorithms, a value
     * called the "rank".
     * <p>
     * An element is a singleton ie an item by itself. Element is just a wrapper
     * for object of type E that allows to store a reference to the parent and
     * optionally the rank.
     *
     * @param <E>
     */

    @SuppressWarnings({"NonStaticInnerClassInSecureContext", "PackageVisibleField", "InstanceVariableOfConcreteClass", "InstanceVariableNamingConvention", "FieldNotUsedInToString", "MethodParameterOfConcreteClass", "InnerClassMayBeStatic", "PackageVisibleInnerClass", "ClassNamingConvention", "TypeParameterHidesVisibleType"})
    class Element<E> {

        final E data;
        Element<E> parent;
        int rank;

        Element(E element) {
            data = element;
            parent = this;
        }

        /**
         * Pretty printing.
         *
         * @return String
         */

        @Override
        public String toString() {
            return String.format("Element<%s>", data.toString());
        }

        /**
         * If an element's parent pointer points to no other element, then the
         * element is the root of a tree and is the representative member of its
         * set.
         *
         * @return boolean
         */

        boolean isRoot() {
            return parent.equals(this);
        }

        /**
         * Union by rank always attaches the shorter tree to the root of the
         * taller tree. Thus, the resulting tree is no taller than the originals
         * unless they were of equal height, in which case the resulting tree is
         * taller by one node.
         *
         * @param element with which you want to join this Element
         */

        @SuppressWarnings("MethodWithMultipleReturnPoints")
        void union(Element<E> element) {

            Element<E> elRoot = element.find();
            Element<E> thisRoot = find();

            // x and y are already in the same set
            if (elRoot.equals(thisRoot)) return;

            if (elRoot.rank >= thisRoot.rank) {
                thisRoot.parent = elRoot.parent;
            } else {
                elRoot.parent = thisRoot.parent;
            }
        }

        /**
         * Find(x) follows the chain of parent pointers from x upwards through
         * the tree until an element is reached whose parent is itself. This
         * element is the root of the tree and is the representative member of
         * the set to which x belongs, and may be x itself.
         */

        @SuppressWarnings("MethodCallInLoopCondition")
        Element<E> find() {
            Element<E> focus = this;
            while (!focus.parent.equals(focus)) focus = focus.parent;
            return focus;
        }
    }

    @Override
    public String toString() {
        return MessageFormat.format("DisjointSet'{'items={0}'}'", items);
    }
}

