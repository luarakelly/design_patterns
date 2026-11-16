package flyweight.rpg_map_rendering;

import javafx.scene.canvas.GraphicsContext;

public class MapRenderer {

    private final TileGraphicFactory graphicFactory;
    private final double tileSize;

    public MapRenderer(TileGraphicFactory graphicFactory, double tileSize) {
        this.graphicFactory = graphicFactory;
        this.tileSize = tileSize;
    }

    public void render(Map map, GraphicsContext graphics) {
        for (int row = 0; row < map.getRows(); row++) {
            for (int column = 0; column < map.getColumns(); column++) {
                Tile tile = map.getTile(row, column);
                TileGraphic graphic = graphicFactory.getGraphic(tile.getType());
                graphic.draw(graphics, column * tileSize, row * tileSize, tileSize);
            }
        }
    }

    public double getTileSize() {
        return tileSize;
    }
}
