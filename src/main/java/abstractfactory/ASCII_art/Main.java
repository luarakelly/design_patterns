package abstractfactory.ASCII_art;

public class Main {

    public static void main(String[] args) {

        Button buttonA = new ButtonA("OK");
        Button buttonB = new ButtonB("Not OK");

        Checkbox checkboxA = new CheckboxA("Enable");
        Checkbox checkboxB = new CheckboxB("Disable");

        TextField textFieldA = new TextFieldA("Text a");
        TextField textFieldB = new TextFieldB("Text b");

        buttonA.display();
        buttonB.display();
        checkboxA.display();
        checkboxB.display();
        textFieldA.display();
        textFieldB.display();
    }
}
