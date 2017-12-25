package data_structures.trees.trie;

import com.github.javafaker.Cat;
import com.github.javafaker.Faker;
import com.github.javafaker.GameOfThrones;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("ClassHasNoToStringMethod")
final class WordCompletionTrieTest {

    private static final int INITIAL_WORDS = 10000;
    private WordCompletionTrie trie;

    /** Logger for the class */
    private static final Logger log = Logger.getAnonymousLogger();

    @SuppressWarnings("OverlyLongLambda")
    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        // fake data generator
        final Faker faker = new Faker(new Locale("en-GB"));
        final GameOfThrones got = faker.gameOfThrones();
        final Cat cat = faker.cat();

        final Collection<String> words = new HashSet<>(400);

        IntStream.range(0, INITIAL_WORDS).forEach((int x) -> {
            words.add(got.dragon());
            words.add(got.city());
            words.add(cat.breed());
            words.add(got.house());
        });

        trie = new WordCompletionTrie(words);
    }

    @Test
    void gatherCandidates() {
        final String query = "Bri";
        final Set<String> candidates = trie.gatherCandidates(query);

        // @formatter:off
        Assertions.assertTrue(!candidates.isEmpty(),
                              "candidates for " + query + " could not be found");

        candidates.forEach((String candidate) -> Assertions
                .assertTrue(candidate.startsWith(query),
                            MessageFormat.format("candidate {0} did not start with required query: {1}",
                                                 candidate, query)));

        // print
        log.warning(MessageFormat.format("Candidates for {0}{2}{1}{2}{2}{3}",
                                         query,
                                         "------------------------------",
                                         System.lineSeparator(),
                                         candidates.stream()
                                                 .collect(Collectors.joining(System.lineSeparator()))));
        // @formatter:on
    }


    @Test
    void words() {
        Assertions.assertFalse(trie.words()
                                       .isEmpty(), "words could not be generated");
        // log.warning(trie.words().stream().collect(Collectors.joining(", ")));
    }
}

