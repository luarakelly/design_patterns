package abstractfactory.ASCII_art;

public class CheckboxA extends Checkbox {
    private String text;

    public CheckboxA(String text) {
        this.text = text;
    }

    @Override
    public void display() {
        System.out.println("[ ] " + text);
    }
}
