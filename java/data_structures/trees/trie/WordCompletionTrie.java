package data_structures.trees.trie;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author norbert
 */

@SuppressWarnings({"OptionalContainsCollection", "ClassNamePrefixedWithPackageName", "NonBooleanMethodNameMayNotStartWithQuestion", "TooBroadScope", "MismatchedQueryAndUpdateOfCollection", "LocalVariableOfConcreteClass", "AccessingNonPublicFieldOfAnotherObject"})
final class WordCompletionTrie {

    private Map<String, WordCompletionTrie> nodes;
    private boolean word;
    private static Set<String> words = new HashSet<>(400);

    /** Logger for the class */
    private static final Logger log = Logger.getAnonymousLogger();

    WordCompletionTrie() {}

    private WordCompletionTrie(final boolean word) {
        this.word = word;
    }

    WordCompletionTrie(final Iterable<String> words) {
        words.forEach(this::add);
    }

    @SuppressWarnings({"ReturnOfNull", "OverlyLongLambda", "LocalVariableHidesMemberVariable"})
    public void add(final String s) {
        if (nodes == null) nodes = new HashMap<>(10);

        if (!s.isEmpty()) {

            final String firstChar = Character.toString(s.charAt(0));
            final String tail = s.substring(1);
            final boolean word = tail.isEmpty();

            nodes.putIfAbsent(firstChar, new WordCompletionTrie(word));

            if (!word) nodes.get(firstChar).add(tail);
        }
    }

    @SuppressWarnings("Convert2Diamond")
    public Set<String> gatherCandidates(final String word) {
        return findNode(word).map(WordCompletionTrie::words).get().stream()
                .map((String x) -> word + x).collect(Collectors.toSet());
    }

    private Optional<WordCompletionTrie> findNode(final String path) {
        if (path.isEmpty()) return Optional.of(this);
        else if (nodes.containsKey(Character.toString(path.charAt(0))))
            return nodes.get(Character.toString(path.charAt(0)))
                    .findNode(path.substring(1));
        else return Optional.empty();
    }

    @SuppressWarnings({"unchecked", "ConditionalExpression"})
    public Set<String> words() {
        if (words == null) words = new HashSet<>(400);
        else words.clear();
        words("");
        return words;
    }

    @SuppressWarnings("OverlyLongLambda")
    private void words(final String carryOver) {
        if (nodes != null)
            nodes.forEach((String key, WordCompletionTrie value) -> {
                final String potentialWord = carryOver + key;
                if (value.word) words.add(potentialWord);
                value.words(potentialWord);
            });
    }

    // @formatter:on
    // @formatter:off
    @SuppressWarnings("ConditionalExpression")
    @Override
    public final String toString() {
        return MessageFormat.format("{0}<nodes={1}>",
                                    getClass().getName(),
                                    ((nodes == null) || nodes.isEmpty()) ? "" : nodes
                                            .keySet()
                                            .stream()
                                            .map(Object::toString)
                                            .collect(Collectors.joining(", ")));
    // @formatter:on
    }
}
