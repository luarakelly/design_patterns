package memento.history_functionality;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private final Model model;

    private final List<IMemento> undoList = new ArrayList<>();
    private final List<IMemento> redoList = new ArrayList<>();

    private final ObservableList<IMemento> historyList = FXCollections.observableArrayList();

    public Controller(Model model) {
        this.model = model;

        // Save the initial state.
        historyList.add(model.createMemento());
    }

    // Call before a model change.
    public void beforeChange() {
        undoList.add(model.createMemento());
        redoList.clear();
    }

    // Call after a model change.
    public void afterChange() {
        historyList.add(model.createMemento());
    }

    // Change the color of one rectangle.
    public void changeColor(int boxNumber) {
        beforeChange();

        switch (boxNumber) {
            case 1 -> model.setColor1((model.getColor1() + 1) % 3);
            case 2 -> model.setColor2((model.getColor2() + 1) % 3);
            case 3 -> model.setColor3((model.getColor3() + 1) % 3);
            default -> throw new IllegalArgumentException(
                    "Invalid rectangle number: " + boxNumber);
        }

        afterChange();
    }

    // Change the checkbox state.
    public void changeCheckboxState(boolean selected) {
        beforeChange();
        model.setCheckboxState(selected);
        afterChange();
    }

    public void undo() {
        if (undoList.isEmpty()) {
            return;
        }

        redoList.add(model.createMemento());

        IMemento previous = undoList.remove(undoList.size() - 1);

        model.restoreState(previous);
    }

    public void redo() {
        if (redoList.isEmpty()) {
            return;
        }

        undoList.add(model.createMemento());

        IMemento next = redoList.remove(redoList.size() - 1);

        model.restoreState(next);
    }

    public ObservableList<IMemento> getHistory() {
        return FXCollections.unmodifiableObservableList(
                historyList);
    }

    public void restoreFromHistory(IMemento selected) {
        if (selected == null) {
            return;
        }

        undoList.add(model.createMemento());
        redoList.clear();

        model.restoreState(selected);
    }
}
