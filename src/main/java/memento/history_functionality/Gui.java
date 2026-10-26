package memento.history_functionality;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Gui extends Application {

    private Model model;
    private Controller controller;

    private Rectangle rectangle1;
    private Rectangle rectangle2;
    private Rectangle rectangle3;

    private CheckBox checkBox;

    @Override
    public void start(Stage stage) {
        model = new Model();
        controller = new Controller(model);

        rectangle1 = createRectangle();
        rectangle2 = createRectangle();
        rectangle3 = createRectangle();

        checkBox = new CheckBox("Checkbox");
        checkBox.setSelected(model.isCheckboxState());

        rectangle1.setOnMouseClicked(event -> controller.changeColor(1));

        rectangle2.setOnMouseClicked(event -> controller.changeColor(2));

        rectangle3.setOnMouseClicked(event -> controller.changeColor(3));

        checkBox.setOnAction(event -> controller.changeCheckboxState(checkBox.isSelected()));

        Button historyButton = new Button("History");
        historyButton.setOnAction(event -> new HistoryWindow(controller, this::updateView).show());

        HBox rectangles = new HBox(
                15, rectangle1, rectangle2, rectangle3);

        VBox root = new VBox(
                15, rectangles, checkBox, historyButton);

        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(root, 450, 250);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (!event.isControlDown()) {
                return;
            }

            if (event.getCode() == KeyCode.Z) {
                controller.undo();
                updateView();
                event.consume();
            } else if (event.getCode() == KeyCode.Y) {
                controller.redo();
                updateView();
                event.consume();
            }
        });

        stage.setTitle("Memento - Improved History");
        stage.setScene(scene);
        stage.show();

        updateView();
    }

    private Rectangle createRectangle() {
        return new Rectangle(100, 100);
    }

    public void updateView() {
        rectangle1.setFill(colorFromState(model.getColor1()));
        rectangle2.setFill(colorFromState(model.getColor2()));
        rectangle3.setFill(colorFromState(model.getColor3()));

        checkBox.setSelected(model.isCheckboxState());
    }

    private javafx.scene.paint.Color colorFromState(int state) {
        return switch (state) {
            case 0 -> javafx.scene.paint.Color.RED;
            case 1 -> javafx.scene.paint.Color.BLUE;
            case 2 -> javafx.scene.paint.Color.YELLOW;
            default -> throw new IllegalArgumentException(
                    "Invalid color state: " + state);
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}
