package data_structures.graphs.trees.binary;

import data_structures.graphs.Graph;
import data_structures.graphs.trees.BinaryNode;
import data_structures.graphs.trees.Tree;
import java.text.MessageFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * From now on, unless specified otherwise, all comments are quotations from
 * https://en.wikipedia.org/wiki/Binary_tree
 * <p>
 * A binary tree is a tree data structure in which each node has at most two
 * children, which are referred to as the left child and the right child. A
 * recursive definition using just set theory notions is that a (non-empty)
 * binary tree is a triple (L, S, R), where L and R are binary trees or the
 * empty set and S is a singleton set.[1] Some authors allow the binary tree to
 * be the empty set as well.
 *
 * @param <E>
 * @author nl253
 */

@SuppressWarnings({"MethodReturnOfConcreteClass", "PublicMethodNotExposedInInterface", "MethodParameterOfConcreteClass", "unused", "DesignForExtension", "PublicConstructor", "ParameterHidesMemberVariable", "InstanceVariableNamingConvention", "InstanceVariableOfConcreteClass", "ClassNamingConvention", "ClassWithoutLogger", "WeakerAccess", "InstanceVariableMayNotBeInitialized", "ClassNamePrefixedWithPackageName", "ClassWithTooManyMethods"})
public class BinaryTree<E extends Comparable<E>> implements BinaryNode<E, BinaryTree<E>>, Tree<E> {

    private BinaryTree<E> left, right;
    private E id;

    private BinaryTree() {}

    private BinaryTree(final E data) {
        id = data;
    }

    public BinaryTree(final Iterable<E> data) {
        final BinaryTree<E> t = new BinaryTree<>();
        data.forEach(t::add);
    }

    public boolean add(final E e) {
        final int comp = id.compareTo(e);
        if (comp == 0) return false;
        else if (comp <= (-1)) {
            if (getLeft().isPresent()) left.add(e);
            else left = new BinaryTree<>(e);
        } else if (comp >= 1) {
            if (getRight().isPresent()) right.add(e);
            else right = new BinaryTree<>(e);
        }
        return true;
    }

    private BinaryTree(final E data, final BinaryTree<E> leftChild, final BinaryTree<E> rightChild) {
        id = data;
        left = leftChild;
        right = rightChild;
    }

    @SuppressWarnings("ConditionalExpression")
    @Override
    public Optional<BinaryTree<E>> getLeft() {
        return (left == null) ? Optional.empty() : Optional.of(left);
    }

    @Override
    public void setLeft(final BinaryTree<E> node) {
        left = node;
    }

    @SuppressWarnings("ConditionalExpression")
    @Override
    public Optional<BinaryTree<E>> getRight() {
        return (right == null) ? Optional.empty() : Optional.of(right);
    }

    @SuppressWarnings("ConditionalExpression")
    @Override
    public void setRight(final BinaryTree<E> node) {
        right = node;
    }

    @Override
    public E getId() {
        return id;
    }


    @Override
    public void setId(final E id) {
        this.id = id;
    }

    @Override
    public Collection<BinaryTree<E>> getChildren() {
        final List<BinaryTree<E>> children = new ArrayList<>(2);
        getLeft().ifPresent(children::add);
        getRight().ifPresent(children::add);
        return children;
    }

    @Override
    public int getOrder() {
        final AtomicInteger order = new AtomicInteger(1); // this node
        getRight().ifPresent((Graph<E> rightNode) -> order
                .getAndAdd(rightNode.getOrder()));
        getLeft().ifPresent((Graph<E> rightNode) -> order
                .getAndAdd(rightNode.getOrder()));
        return order.get();
    }

    /**
     * Find an element in the tree using id as lookup key.
     * <p>
     * Breadth-first traversal.
     *
     * @param id lookup key
     * @return The value
     */

    @SuppressWarnings({"LocalVariableOfConcreteClass", "MethodCallInLoopCondition", "AccessingNonPublicFieldOfAnotherObject", "DesignForExtension", "LocalCanBeFinal", "ParameterHidesMemberVariable", "InstanceMethodNamingConvention", "MethodWithMultipleReturnPoints", "MultiplyOrDivideByPowerOfTwo", "LawOfDemeter", "ConstantConditions", "EqualsReplaceableByObjectsCall"})
    public Optional<BinaryTree<E>> getBreadthFirstSearch(final E id) {

        final Queue<BinaryTree<E>> queue = new ArrayDeque<>(getOrder() + 10);

        queue.add(this);

        while (!queue.isEmpty()) {
            final BinaryTree<E> focus = queue.remove();
            if (focus.id.equals(id)) return Optional.ofNullable(focus);
            getLeft().ifPresent(queue::add);
            getRight().ifPresent(queue::add);
        }
        return Optional.empty();
    }

    /**
     * Find an element in the tree using id as lookup key.
     * <p>
     * Depth-first traversal.
     *
     * @param id lookup key
     * @return The value
     */

    @SuppressWarnings({"MethodWithMultipleReturnPoints", "SwitchStatementWithoutDefaultBranch", "SwitchStatement", "ConditionalExpression"})
    public Optional<? extends BinaryTree<E>> getDepthFirstSearch(final E id) {

        final Optional<BinaryTree<E>> l = getLeft()
                .flatMap((BinaryTree<E> x) -> x.getDepthFirstSearch(id));

        if (l.isPresent()) return l;

        final Optional<BinaryTree<E>> r = getRight()
                .flatMap((BinaryTree<E> x) -> x.getDepthFirstSearch(id));

        if (r.isPresent()) return r;

        return this.id.equals(id) ? Optional.of(this) : null;
    }

    @SuppressWarnings({"MethodWithMultipleReturnPoints", "SwitchStatementWithoutDefaultBranch", "SwitchStatement", "ConditionalExpression"})
    public Optional<? extends BinaryTree<E>> get(final E id) {
        final int comp = this.id.compareTo(id);
        if (comp <= -1) return getLeft().isPresent() ? getLeft()
                .flatMap((BinaryTree<E> x) -> x.get(id)) : Optional.empty();
        else if (comp == 0) return Optional.of(this);
        else if (comp >= 1) return getRight().isPresent() ? getRight()
                .flatMap((BinaryTree<E> x) -> x.get(id)) : Optional.empty();
        else return Optional.empty();
    }


    @SuppressWarnings("CompareToUsesNonFinalVariable")
    @Override
    public int compareTo(final BinaryTree<E> t) {
        return id.compareTo(t.id);
    }

    @SuppressWarnings("ConditionalExpression")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if ((obj == null) || (!getClass().equals(obj.getClass()))) return false;

        final BinaryNode<?, ?> that = (BinaryNode<?, ?>) obj;

        return ((getLeft() != null) ? getLeft().equals(that.getLeft()) : (that
                .getLeft() == null)) && ((getRight() != null) ? getRight()
                .equals(that.getRight()) : (that.getRight() == null));
    }

    @SuppressWarnings("ConditionalExpression")
    @Override
    public int hashCode() {
        int result = (getLeft() != null) ? getLeft().hashCode() : 0;
        result = (31 * result) + ((getRight() == null) ? 0 : getRight()
                .hashCode());
        return result;
    }


    /**
     * Pretty print.
     *
     * @return void
     */

    @SuppressWarnings("ConditionalExpression")
    @Override
    public String toString() {
        // @formatter:off
        return MessageFormat.format("{0}<{1}>",
                                    getClass().getName(),
                                    getChildren().isEmpty()? "" :
                                    getChildren().stream()
                                            .map(Object::toString)
                                            .collect(Collectors.joining(
                                                    ", ")));
        // @formatter:on
    }

}
