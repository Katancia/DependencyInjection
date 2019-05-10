package pl.karol.lingu.crypto;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DefaultFileCipher implements FileCipher {

    @Override
    public String encrypt(String text) {
        return text;
    }

    @Override
    public String decrypt(String cipher) {
        return cipher;
    }
}
