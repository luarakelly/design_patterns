package abstractfactory.ASCII_art;

public class ButtonB extends Button {
    public ButtonB(String text) {
        super(text);
    }

    @Override
    public void display() {
        System.out.println("<< " + getText() + " >>");
    }
}
