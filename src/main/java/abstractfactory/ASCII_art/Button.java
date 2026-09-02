package abstractfactory.ASCII_art;

public abstract class Button extends UIElementText {
    public Button(String text) {
        super(text);
    }

    public abstract void display();
}
