package encryptdecrypt;

public class Context {

    private CipherMethod method;

    public void encodeData(String data, int key) {
        this.method.encodeData(data, key);
    }

    public void decodeData(String data, int key) {
        this.method.decodeData(data, key);
    }

    public void encodeToFile(String from, int key, String to) {
        this.method.encodeToFile(from, key, to);
    }

    public void decodeToFile(String from, int key, String to) {
        this.method.decodeToFile(from, key, to);
    }

    public void setStrategy(CipherMethod algorithm) {
        method = algorithm;
    }
}
