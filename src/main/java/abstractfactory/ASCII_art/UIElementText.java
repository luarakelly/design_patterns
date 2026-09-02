package abstractfactory.ASCII_art;

public abstract class UIElementText {
    private String text;

    public UIElementText(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
