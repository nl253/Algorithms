package data_structures.graphs.trees.binary;

import data_structures.graphs.Node;
import data_structures.graphs.trees.BinaryNode;
import data_structures.graphs.trees.Tree;
import java.text.MessageFormat;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

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
public class BinaryTree<E extends Comparable<E>> implements data_structures.graphs.trees.BinaryNode<E>, Tree<E> {

    private BinaryTree<E> left;
    private BinaryTree<E> right;

    public BinaryTree(final E data) {
        super(data);
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
            if (focus.getId().equals(id)) return Optional.ofNullable(focus);
            getLeft().ifPresent((BinaryNode<E> x) -> queue
                    .add(((BinaryTree<E>) x)));
            getRight().ifPresent((BinaryNode<E> x) -> queue
                    .add(((BinaryTree<E>) x)));
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

    @SuppressWarnings({"MethodWithMultipleReturnPoints", "SwitchStatementWithoutDefaultBranch", "SwitchStatement"})
    public Optional<BinaryTree<E>> getDepthFirstSearch(final E id) {
        switch (getId().compareTo(id)) {
            case (-1):
                final Optional<BinaryNode<E>> maybeLeft = getLeft();
                return maybeLeft
                        .flatMap(eBinaryNode -> ((BinaryTree<E>) (eBinaryNode))
                                .getDepthFirstSearch(id));
            case 1:
                final Optional<BinaryNode<E>> maybeRight = getRight();
                return maybeRight
                        .flatMap(eBinaryNode -> ((BinaryTree<E>) (eBinaryNode))
                                .getDepthFirstSearch(id));
            default:
                return Optional.of(this);
        }
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

    @Override
    public Optional<BinaryNode<E>> getLeft() {
        return Optional.empty();
    }

    @Override
    public void setLeft() {

    }

    @Override
    public Optional<BinaryNode<E>> getRight() {
        return Optional.empty();
    }

    @Override
    public void setRight(final BinaryNode<E> node) {

    }

    @Override
    public Collection<Tree<E>> getChildren() {
        return null;
    }

    @Override
    public void setChildren(final Collection<Tree<E>> children) {

    }

    /**
     * Make a connection between two nodes. If they don't already exist, add
     * them.
     *
     * @param a the first node
     * @param b the second node
     * @param cost the cost of going from the first to the second node
     * @return the graph itself
     */
    @Override
    public void connect(final E a, final E b, final int cost) {

    }

    @Override
    public void disconnect(final E a, final E b) {

    }

    @Override
    public void eject(final E node) {

    }

    @Override
    public int getCost(final E start, final E dest) {
        return 0;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void insert(final E node) {

    }

    @Override
    public int setCost(final int cost) {
        return 0;
    }

    @Override
    public Collection<E> getAdjecentNodes(final E node) {
        return null;
    }

    @Override
    public E getId() {
        return null;
    }

    @Override
    public void setId(final E id) {

    }

    @Override
    public Collection<Node<E>> getAdjecentNodes() {
        return null;
    }

    @Override
    public int compareTo(@NotNull final Node<E> eNode) {
        return 0;
    }
}
