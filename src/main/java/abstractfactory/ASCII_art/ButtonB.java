package abstractfactory.ASCII_art;

public class ButtonB extends Button {
    private String text;

    public ButtonB(String text) {
        this.text = text;
    }

    @Override
    public void display() {
        System.out.println("<< " + text + " >>");
    }
}
