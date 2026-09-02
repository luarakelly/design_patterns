package abstractfactory.ASCII_art;

public abstract class TextField extends UIElementText {
    public TextField(String text) {
        super(text);
    }

    public abstract void display();
}
