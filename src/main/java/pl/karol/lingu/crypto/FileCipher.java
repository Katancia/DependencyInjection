package pl.karol.lingu.crypto;


public interface FileCipher {
    String encrypt(String text);
    String decrypt(String cipher);
}
