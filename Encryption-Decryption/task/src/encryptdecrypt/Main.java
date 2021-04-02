package encryptdecrypt;

public class Main {
    public static final String DECRYPTED = "dec";
    public static final String ENCRYPTED = "enc";
    public static final String SHIFT = "shift";
    public static final String UNICODE = "unicode";

    public static void main(String[] args) {
        String alg = "";
        String mode = "";
        String data = "";
        String in = "";
        String out = "";
        int key = 0;

        Context context = new Context();
        ShiftCipherMethod shiftAlgorithm = new ShiftCipherMethod();
        UnicodeCipherMethod unicodeAlgorithm = new UnicodeCipherMethod();
        context.setStrategy(shiftAlgorithm);


        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-alg")) {
                alg = args[i + 1];
            }
            if (args[i].startsWith("-key")) {
                key = Integer.parseInt(args[i + 1]);
            }
            if (args[i].startsWith("-mode")) {
                mode = args[i + 1];
            }
            if (args[i].startsWith("-data")) {
                data = args[i + 1];
            }
            if (args[i].startsWith("-in")) {
                in = args[i + 1];
            }
            if (args[i].startsWith("-out")) {
                out = args[i + 1];
            }
        }

        if (alg.equals(SHIFT)) {
            context.setStrategy(shiftAlgorithm);
        } else if (alg.equals(UNICODE)) {
            context.setStrategy(unicodeAlgorithm);
        } else {
            context.setStrategy(shiftAlgorithm);
        }

        if (mode.equals(ENCRYPTED)) {
            if (in.isEmpty()) {
                context.encodeData(data, key);
            }
            context.encodeToFile(in, key, out);
        } else if (mode.equals(DECRYPTED)) {
            if (in.isEmpty()) {
                context.decodeData(data, key);
            }
            context.decodeToFile(in, key, out);
        }
    }
}
