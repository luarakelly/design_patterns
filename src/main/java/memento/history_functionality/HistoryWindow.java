
package memento.history_functionality;

import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class HistoryWindow {

    private final Controller controller;
    private final Runnable onRestore;

    public HistoryWindow(Controller controller, Runnable onRestore) {
        this.controller = controller;
        this.onRestore = onRestore;
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Model History");

        ListView<IMemento> listView = new ListView<>();
        listView.setItems(controller.getHistory());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        listView.setCellFactory(view -> new ListCell<>() {
            @Override
            protected void updateItem(IMemento item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getTimestamp().format(formatter));
                }
            }
        });

        listView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, selected) -> {
                    if (selected != null) {
                        controller.restoreFromHistory(selected);
                        onRestore.run();
                    }
                });

        VBox root = new VBox(listView);
        root.setStyle("-fx-padding: 10;");

        Scene scene = new Scene(root, 450, 300);
        stage.setScene(scene);
        stage.show();
    }
}
