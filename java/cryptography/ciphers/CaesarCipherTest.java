package cryptography.ciphers;

import org.junit.jupiter.api.BeforeEach;

final class CaesarCipherTest extends BaseCipherTest {

    @BeforeEach
    void setUp() {
        cipher = new CaesarCipher();
    }
}
