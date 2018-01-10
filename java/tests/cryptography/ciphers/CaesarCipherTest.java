package cryptography.ciphers;

import org.junit.jupiter.api.BeforeEach;

final class CaesarCipherTest extends BaseCipherTest {

    private BaseCipher cipher;

    @BeforeEach
    void setUp() {
        cipher = new cryptography.ciphers.CaesarCipher();
    }

    @Override
    BaseCipher getCipher() {
        return cipher;
    }
}
