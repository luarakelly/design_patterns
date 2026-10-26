package memento.history_functionality;

public class Model {

    private int color1 = 0;
    private int color2 = 1;
    private int color3 = 2;
    private boolean checkboxState = false;

    public IMemento createMemento() {
        return new Memento(
                color1,
                color2,
                color3,
                checkboxState);
    }

    public void restoreState(IMemento memento) {
        if (!(memento instanceof Memento savedState)) {
            throw new IllegalArgumentException(
                    "Unsupported memento type");
        }

        color1 = savedState.getColor1();
        color2 = savedState.getColor2();
        color3 = savedState.getColor3();
        checkboxState = savedState.isCheckboxState();

        // Preserve your existing mechanism for updating the GUI.
    }

    public int getColor1() {
        return color1;
    }

    public int getColor2() {
        return color2;
    }

    public int getColor3() {
        return color3;
    }

    public boolean isCheckboxState() {
        return checkboxState;
    }

    public void setColor1(int color) {
        color1 = color;
    }

    public void setColor2(int color) {
        color2 = color;
    }

    public void setColor3(int color) {
        color3 = color;
    }

    public void setCheckboxState(boolean selected) {
        checkboxState = selected;
    }
}
