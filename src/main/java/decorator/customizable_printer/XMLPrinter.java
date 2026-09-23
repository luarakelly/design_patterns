package decorator.customizable_printer;

public class XMLPrinter implements Printer {

    private Printer printer;

    public XMLPrinter(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void print(String message) {
        printer.print("<message>" + message + "</message>");
    }
}
