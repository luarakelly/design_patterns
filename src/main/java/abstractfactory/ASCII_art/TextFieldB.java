package abstractfactory.ASCII_art;

public class TextFieldB extends TextField {
    private String text;

    public TextFieldB(String text) {
        this.text = text;
    }

    @Override
    public void display() {
        System.out.println(" '" + text + "' ");
    }
}
