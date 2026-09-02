package abstractfactory.ASCII_art;

public class CheckboxB extends Checkbox {
    private String text;

    public CheckboxB(String text) {
        this.text = text;
    }

    @Override
    public void display() {
        System.out.println("( ) " + text);
    }
}
