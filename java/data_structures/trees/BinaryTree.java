package data_structures.trees;

import java.text.MessageFormat;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

@SuppressWarnings({"MethodReturnOfConcreteClass", "PublicMethodNotExposedInInterface", "MethodParameterOfConcreteClass", "unused", "DesignForExtension", "PublicConstructor", "ParameterHidesMemberVariable", "InstanceVariableNamingConvention", "InstanceVariableOfConcreteClass", "ClassNamingConvention", "ClassWithoutLogger", "WeakerAccess", "InstanceVariableMayNotBeInitialized"})
public class BinaryTree<I extends Comparable<I>, V> extends Node<I, V> {

    private BinaryTree<I, V> left;
    private BinaryTree<I, V> right;

    public BinaryTree(final I id, final V value) {
        super(id, value);
    }

    public Optional<BinaryTree<I, V>> getLeft() {
        return Optional.of(left);
    }

    /**
     * @return the right node.
     */

    public Optional<BinaryTree<I, V>> getRight() {
        return Optional.of(right);
    }

    @SuppressWarnings({"LocalCanBeFinal", "ParameterHidesMemberVariable"})
    public void setRight(BinaryTree<I, V> newRight) {
        right = newRight;
    }

    @SuppressWarnings("ParameterHidesMemberVariable")
    public void setLeft(final BinaryTree<I, V> newLeft) {
        left = newLeft;
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
    public Optional<V> getBreadthFirstSearch(final I id) {
        Queue<BinaryTree<I, V>> queue = new ArrayDeque<>(getHeight() * 2);
        queue.add(this);

        while (!queue.isEmpty()) {
            BinaryTree<I, V> focus = queue.remove();
            if (focus.getId().get().equals(id)) return focus.getValue();
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

    @SuppressWarnings("MethodWithMultipleReturnPoints")
    public Optional<V> getDepthFirstSearch(final I id) {
        if (this.id.equals(id)) return Optional.of(value);

        final Optional<V> vLeft = left.getDepthFirstSearch(id);
        final Optional<V> vRight = right.getDepthFirstSearch(id);

        //noinspection ConditionalExpression
        return vLeft.isPresent() ? vLeft : vRight;
    }

    /**
     * Return the height of the list.
     *
     * @return height of the tree
     */

    @SuppressWarnings({"ConditionalExpression", "IfStatementWithTooManyBranches", "MethodWithMultipleReturnPoints"})
    public int getHeight() {
        return Math.max(getLeft().isPresent() ? (1 + left
                .getHeight()) : 1, getRight().isPresent() ? (1 + right
                .getHeight()) : 1);
    }

    /**
     * Pretty print.
     *
     * @return void
     */

    @SuppressWarnings("ConditionalExpression")
    @Override
    public String toString() {
        return MessageFormat.format("BinaryTree<{0} <- {1} -> <{2}>", getLeft()
                                                                              .isPresent() ? left
                                                                              .toString() : "", getValue(), getRight()
                                                                                                                    .isPresent() ? right
                                                                                                                    .toString() : "");
    }
}
