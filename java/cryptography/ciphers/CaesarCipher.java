package cryptography.ciphers;

/**
 * @author norbert
 */

@SuppressWarnings({"CallToSuspiciousStringMethod", "AssignmentToLambdaParameter", "WeakerAccess", "JavaDoc"})
public final class CaesarCipher extends BaseCipher {

    private static final int SHIFTLEN = 10;

    /**
     * Encode the message.
     *
     * @param message
     * @return
     */

    static CharSequence encode(final CharSequence message) {
        return message.chars().map(Character::getNumericValue)
                .map((int x) -> x + SHIFTLEN)
                .collect(String::new, (String str, int i) -> str += String
                        .valueOf(i), (String str1, String str2) -> str1 += str2);
    }

    /**
     * Decode a message.
     *
     * @param message
     * @return
     */

    private static CharSequence decode(final CharSequence message) {
        return message.chars().map(Character::getNumericValue)
                .map((int x) -> x - SHIFTLEN)
                .collect(String::new, (String str, int i) -> str += String
                        .valueOf(i), (String str1, String str2) -> str1 += str2);
    }

    /**
     * Test.
     *
     * @param args
     */

    @SuppressWarnings({"AssertionCanBeIf", "AssertStatement"})
    public static void main(final String... args) {
        assert message.equals(decode(encode(message)));
    }
}
