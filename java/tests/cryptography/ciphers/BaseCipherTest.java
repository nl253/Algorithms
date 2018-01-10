package cryptography.ciphers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"ClassHasNoToStringMethod", "FeatureEnvy", "CallToSuspiciousStringMethod", "AbstractClassWithOnlyOneDirectInheritor"})
abstract class BaseCipherTest {

    abstract BaseCipher getCipher();

    // @formatter:off

    @Test
    final void encode() {

        final String randomMsg = BaseCipher.randomMsg();

        Assertions.assertEquals(
                getCipher().decode(getCipher().encode(randomMsg)),
                randomMsg,
                "Encoding / Decoding failed");
    }

    @Test
    final void decode() {

        final String encoded = getCipher().encode();

        Assertions.assertEquals(
                getCipher().encode(getCipher().decode(encoded)),
                encoded, "Encoding / Decoding failed");
    }

}
