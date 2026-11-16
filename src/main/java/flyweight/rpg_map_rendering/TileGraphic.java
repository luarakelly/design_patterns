package flyweight.rpg_map_rendering;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class TileGraphic {

    private final String type;
    private final Image image;

    public TileGraphic(String type, Image image) {
        this.type = type;
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public Image getImage() {
        return image;
    }

    public void draw(GraphicsContext graphics, double x, double y, double size) {
        graphics.drawImage(image, x, y, size, size);
    }
}
