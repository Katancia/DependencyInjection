package pl.karol.lingu.crypto;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class CezarFileCipher implements FileCipher {

    private static final int SHIFT = 3;

    @Override
    public String encrypt(String text) {
        return text.chars()
                .map(character -> character + SHIFT)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Override
    public String decrypt(String cipher) {
        return cipher.chars()
                .map(character -> character - SHIFT)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
