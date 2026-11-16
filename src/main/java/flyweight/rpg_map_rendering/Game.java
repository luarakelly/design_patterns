package flyweight.rpg_map_rendering;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class Game extends Application {

    private static final int ROWS = 20;
    private static final int COLUMNS = 25;
    private static final double TILE_SIZE = 32;

    private final TileGraphicFactory graphicFactory = new TileGraphicFactory();
    private final MapRenderer renderer = new MapRenderer(graphicFactory, TILE_SIZE);

    private Canvas canvas;
    private Label infoLabel;

    public static Map createMap() {
        Random random = new Random();

        int mapType = random.nextInt(2);

        if (mapType == 1) {
            System.out.println("City map");
            return new CityMap(ROWS, COLUMNS);
        } else {
            System.out.println("wilderness map");
            return new WildernessMap(ROWS, COLUMNS);
        }
    }

    @Override
    public void start(Stage stage) {
        canvas = new Canvas(COLUMNS * TILE_SIZE, ROWS * TILE_SIZE);
        infoLabel = new Label();

        Button newMapButton = new Button("Generate new map");
        newMapButton.setOnAction(event -> showNewMap());

        HBox toolbar = new HBox(12, newMapButton, infoLabel);
        toolbar.setAlignment(Pos.CENTER_LEFT);

        VBox root = new VBox(8, toolbar, canvas);
        root.setPadding(new Insets(8));

        showNewMap();

        stage.setTitle("RPG Map - Flyweight rendering");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    private void showNewMap() {
        Map map = createMap();
        map.generate();
        System.out.println("Generated map:");
        map.display();

        renderer.render(map, canvas.getGraphicsContext2D());

        String mapName = (map instanceof CityMap) ? "City map" : "Wilderness map";
        int tileCount = map.getRows() * map.getColumns();
        infoLabel.setText(mapName + ": " + tileCount + " tiles drawn  |  tile graphics created so far: "
                + graphicFactory.getCreatedCount());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
