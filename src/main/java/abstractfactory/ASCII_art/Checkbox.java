package abstractfactory.ASCII_art;

public abstract class Checkbox extends UIElementText {
    public Checkbox(String text) {
        super(text);
    }

    public abstract void display();
}
