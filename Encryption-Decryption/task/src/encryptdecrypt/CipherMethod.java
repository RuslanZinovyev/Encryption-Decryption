package encryptdecrypt;

interface CipherMethod {

    void encodeData(String data, int key);
    void decodeData(String data, int key);
    void encodeToFile(String from, int key, String to);
    void decodeToFile(String from, int key, String to);

}
