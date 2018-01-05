package data_structures.graphs.trees;

import data_structures.graphs.Graph;
import data_structures.graphs.Node;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author nl253
 */

@SuppressWarnings("InterfaceWithOnlyOneDirectInheritor")
public interface Tree<E extends Comparable<E>, T extends Tree<E, T>> extends Graph<E, T>, Node<E, T> {

    Collection<T> getChildren();

    @SuppressWarnings("MethodCallInLoopCondition")
    default Set<T> getDescendants() {
        if (getChildren().isEmpty()) return new HashSet<>(getChildren());
        final Deque<T> descendants = new ArrayDeque<>(getOrder());
        descendants.addAll(getChildren());

        final Set<T> result = new HashSet<>(getOrder());

        while (!descendants.isEmpty()) {
            final T focus = descendants.pollFirst();
            result.add(focus);
            if (!focus.getDescendants().isEmpty())
                descendants.addAll(focus.getChildren());
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    default Set<T> getLeaves() {
        return getDescendants().stream().filter(x -> x.getChildren().isEmpty())
                .collect(Collectors.toSet());
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
