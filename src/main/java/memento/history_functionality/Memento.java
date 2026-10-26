package memento.history_functionality;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Memento implements IMemento {

    private final int color1;
    private final int color2;
    private final int color3;
    private final boolean checkboxState;
    private final LocalDateTime timestamp;

    public Memento(
            int color1,
            int color2,
            int color3,
            boolean checkboxState) {

        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.checkboxState = checkboxState;
        this.timestamp = LocalDateTime.now();
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

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return timestamp.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
