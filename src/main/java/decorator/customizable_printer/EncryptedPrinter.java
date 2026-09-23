package decorator.customizable_printer;

public class EncryptedPrinter implements Printer {

    private Printer printer;

    public EncryptedPrinter(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void print(String message) {
        String encryptedMessage = encrypt(message);
        printer.print(encryptedMessage);
    }

    private String encrypt(String message) {
        StringBuilder result = new StringBuilder();

        for (char c : message.toCharArray()) {
            result.append((char) (c + 1));
        }

        return result.toString();
    }
}
