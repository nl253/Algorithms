package data_structures.graphs;

import com.sun.istack.internal.Nullable;
import java.text.MessageFormat;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

@SuppressWarnings({"MethodReturnOfConcreteClass", "PublicMethodNotExposedInInterface", "MethodParameterOfConcreteClass", "unused", "DesignForExtension", "PublicConstructor", "ParameterHidesMemberVariable", "InstanceVariableNamingConvention", "InstanceVariableOfConcreteClass", "ClassNamingConvention", "ClassWithoutLogger", "WeakerAccess"})
public class Tree<I extends Comparable<I>, V> extends Node<I, V> {

    private Tree<I, V> left;
    private Tree<I, V> right;

    public Tree(final I id, final V value) {
        super(id, value);
    }

    public Tree<I, V> getLeft() {
        return left;
    }

    @SuppressWarnings({"LocalCanBeFinal", "ParameterHidesMemberVariable"})
    public void setLeft(Tree<I, V> left) {
        this.left = left;
    }

    /**
     * Find an element in the tree using id as lookup key.
     *
     * @param id lookup key
     * @return The value
     */

    @SuppressWarnings({"LocalVariableOfConcreteClass", "MethodCallInLoopCondition", "ObjectEqualsNull", "AccessingNonPublicFieldOfAnotherObject", "DesignForExtension", "LocalCanBeFinal", "ParameterHidesMemberVariable", "InstanceMethodNamingConvention", "MethodWithMultipleReturnPoints", "MultiplyOrDivideByPowerOfTwo"})
    @Nullable
    public Optional<V> get(final I id) {
        Queue<Tree<I, V>> queue = new ArrayDeque<>(height() * 2);
        queue.add(this);

        while (!queue.isEmpty()) {
            Tree<I, V> focus = queue.remove();
            if (focus.id.equals(id)) return Optional.of(focus.value);
            if (!left.equals(null)) queue.add(left);
            if (!right.equals(null)) queue.add(right);
        }
        return Optional.empty();
    }

    public Tree<I, V> getRight() {
        return right;
    }

    @SuppressWarnings({"LocalCanBeFinal", "ParameterHidesMemberVariable"})
    public void setRight(Tree<I, V> right) {
        this.right = right;
    }

    /**
     * Return the height of the list.
     *
     * @return
     */

    @SuppressWarnings({"ConditionalExpression", "ObjectEqualsNull"})
    public int height() {
        return (left.equals(null) && right.equals(null)) ? 1 : Math
                .max(1 + left.height(), 1 + right.height());
    }

    // public boolean balanced() {
    //     if (left.equals(null) && right.equals(null)) return true;
    //     else if (!left.equals(null) && !right.equals(null)) return true;
    //     return left.balanced() && right.balanced();
    // }

    @Override
    public String toString() {
        return MessageFormat.format("Tree<{0} <- {1} -> <{2}>", left
                .toString(), getValue(), right.toString());
    }
}
