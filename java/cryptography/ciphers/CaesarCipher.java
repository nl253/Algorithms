package cryptography.ciphers;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author norbert
 */

@SuppressWarnings({"CallToSuspiciousStringMethod", "AssignmentToLambdaParameter", "WeakerAccess", "JavaDoc", "LambdaBodyCanBeCodeBlock"})
public final class CaesarCipher extends BaseCipher {

    private static final int SHIFTLEN = 10;

    /**
     * Encode the message.
     *
     * @param message
     * @return
     */

    static CharSequence encode(final CharSequence message, final int shift) {
        return message.chars().map(Character::getNumericValue)
                .map((int x) -> x + shift)
                .collect(String::new, (String str, int i) -> str += String
                        .valueOf(i), (String str1, String str2) -> str1 += str2);
    }

    static CharSequence encode(final CharSequence message) {
        return encode(message, SHIFTLEN);
    }


    /**
     * Decode a message.
     *
     * @param message
     * @return
     */

    private static CharSequence decode(final CharSequence message, final int shift) {
        return message.chars().map(Character::getNumericValue)
                .map((int x) -> x - shift)
                .collect(String::new, (String str, int i) -> str += String
                        .valueOf(i), (String str1, String str2) -> str1 += str2);
    }

    private static CharSequence decode(final CharSequence message) {
        return decode(message, SHIFTLEN);
    }

    /**
     * Shortcuts.
     *
     * @param args
     */

    @SuppressWarnings({"AssertionCanBeIf", "AssertStatement", "ConditionalExpression"})
    public static void main(final String... args) {
        assert message
                .equals(decode(encode((args.length > 0) ? Arrays.stream(args)
                        .collect(Collectors.joining()) : message)));
    }
}
