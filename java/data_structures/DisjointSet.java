package data_structures;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;

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
    private Collection<Element<E>> elements;

    DisjointSet() {
        elements = new ArrayList<>(INITIAL_CAPACITY);
    }

    /**
     * @param initalElements initial items in the Set, automatically passed to
     * makeSet().
     */

    @SuppressWarnings("ImplicitCallToSuper")
    DisjointSet(Collection<E> initalElements) {
        elements = new ArrayList<>(initalElements.size());
        makeSet(initalElements);
    }

    @SuppressWarnings({"LocalCanBeFinal", "ReturnOfNull", "MethodWithMultipleReturnPoints"})
    Element<E> find(E id) {
        Element<E> result = null;
        for (Element<E> element : elements)
            if (element.equals(id)) result = element.find();
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
        items.forEach(x -> elements.add(new Element<>(x)));
    }


    /**
     * Pretty printing.
     *
     * @return string representation of this object
     */

    @Override
    public String toString() {
        return MessageFormat.format("DisjointSet<{0}>", elements.toString());
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

    @SuppressWarnings({"NonStaticInnerClassInSecureContext", "PackageVisibleField", "InstanceVariableOfConcreteClass", "InstanceVariableNamingConvention", "FieldNotUsedInToString", "MethodParameterOfConcreteClass", "InnerClassMayBeStatic", "PackageVisibleInnerClass", "ClassNamingConvention", "TypeParameterHidesVisibleType", "EqualsAndHashcode"})
    private class Element<E> {

        final E id;
        Element<E> parent;
        int rank;

        Element(E id) {
            this.id = id;
            // initially the parent is set to itself
            parent = this;
        }

        /**
         * Pretty printing.
         *
         * @return String
         */

        @Override
        public String toString() {
            return String.format("Element<%s>", id.toString());
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

            Element<E> xRoot = element.find();
            Element<E> yRoot = find();

            // x and y are already in the same set
            if (yRoot.equals(xRoot)) return;

            // attach the shorter to longer

            // xRoot is shorter, yRoot is longer
            if (xRoot.rank < yRoot.rank) yRoot.parent = xRoot.parent;
                // yRoot is shorter, xRoot is longer
            else if (xRoot.rank > yRoot.rank) xRoot.parent = yRoot.parent;
            else {
                xRoot.parent = yRoot.parent;
                xRoot.parent.rank++;
            }
        }

        /**
         * Find follows the chain of parent pointers from x upwards through the
         * tree until an element is reached whose parent is itself.
         * <p>
         * This element is the root of the tree and is the representative member
         * of the set to which the item belongs, and may be the item itself.
         */

        @SuppressWarnings("MethodCallInLoopCondition")
        Element<E> find() {
            Element<E> focus = this;
            while (!focus.parent.equals(focus)) focus = focus.parent;
            return focus;
        }

        /**
         * When you compare Elements you are actually comparing their ids.
         *
         * @param o object you want to compare this to
         * @return boolean
         */

        @Override
        public boolean equals(final Object o) {
            return id.equals(o);
        }
    }
}

