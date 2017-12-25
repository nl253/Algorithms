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
 * Application of the Trie data structure.
 * Here it allows to find matching words that share given prefix in O(log(n)) time which is slightly better than running a linear search on a {@link java.util.Collection} of {@link String}s.
 *
 * @author norbert
 */

@SuppressWarnings({"OptionalContainsCollection", "ClassNamePrefixedWithPackageName", "NonBooleanMethodNameMayNotStartWithQuestion", "TooBroadScope", "MismatchedQueryAndUpdateOfCollection", "LocalVariableOfConcreteClass", "AccessingNonPublicFieldOfAnotherObject", "AlibabaAvoidCommentBehindStatement"})
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

    void remove(final String word) {
        findNode(word).ifPresent((WordCompletionTrie x) -> x.word = false);
    }

    /**
     * Add a word to the completion system.
     *
     * @param wordToComplete a {@link String} - word to complete
     */

    @SuppressWarnings({"ReturnOfNull", "OverlyLongLambda", "LocalVariableHidesMemberVariable", "WeakerAccess"})
    public void add(final String wordToComplete) {
        if (nodes == null) nodes = new HashMap<>(10);

        if (!wordToComplete.isEmpty()) {

            final String firstChar = Character
                    .toString(wordToComplete.charAt(0));
            final String tail = wordToComplete.substring(1);
            final boolean word = tail.isEmpty();

            nodes.putIfAbsent(firstChar, new WordCompletionTrie(word));

            if (!word) nodes.get(firstChar).add(tail);
        }
    }

    /**
     * The core of {@link WordCompletionTrie}. Produce a {@link Set} of {@link String}s that have the prefix.
     *
     * @param prefix
     * @return {@link Set} of {@link String}s that have the prefix.
     */

    // @formatter:off
    @SuppressWarnings({"Convert2Diamond", "ConstantConditions"})
    public Set<String> gatherCandidates(final String prefix) {
        return findNode(prefix)
                .map(WordCompletionTrie::words)
                .get() // unwrap Optional to Set<String>
                .stream()
                .map((String x) -> prefix + x)
                .collect(Collectors.toSet());
    }
    // @formatter:on

    private Optional<WordCompletionTrie> findNode(final String path) {
        if (path.isEmpty()) return Optional.of(this);
        else if (nodes.containsKey(Character.toString(path.charAt(0))))
            return nodes.get(Character.toString(path.charAt(0)))
                    .findNode(path.substring(1));
        else return Optional.empty();
    }

    /**
     * Produce all words that can be formed from this node recursively.
     *
     * @return words that can be formed from this node recursively
     */

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
