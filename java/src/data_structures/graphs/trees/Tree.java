package data_structures.graphs.trees;

import data_structures.graphs.Graph;
import data_structures.graphs.Node;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;

/**
 * @author nl253
 */

@SuppressWarnings("InterfaceWithOnlyOneDirectInheritor")
public interface Tree<E extends Comparable<E>> extends Graph<E>, Node<E> {

    Collection<Tree<E>> getChildren();

    @SuppressWarnings("MethodCallInLoopCondition")
    default Collection<Tree<E>> getDescendants() {
        if (getChildren().isEmpty()) return getChildren();
        final Deque<Tree<E>> descendants = new ArrayDeque<>(getOrder());
        descendants.addAll(getChildren());

        final Collection<Tree<E>> result = new HashSet<>(getOrder());

        while (!descendants.isEmpty()) {
            final Tree<E> focus = descendants.pollFirst();
            result.add(focus);
            if (!focus.getDescendants().isEmpty())
                descendants.addAll(focus.getChildren());
        }
        return result;
    }

    void setChildren(Collection<Tree<E>> children);

    @SuppressWarnings("unchecked")
    default Collection<Tree<E>> getLeaves() {
        return (Collection<Tree<E>>) getDescendants().stream()
                .filter(x -> x.getChildren().isEmpty());
    }

    @SuppressWarnings({"ConditionalExpression", "WeakerAccess", "ConstantConditions"})
    default int getHeight() {
        // @formatter:off
        if (getChildren().isEmpty()) return 0;
        return 1 + getChildren()
                .stream()
                .map(Tree::getHeight)
                .reduce(Math::max).get();
        // @formatter:on
    }
}
