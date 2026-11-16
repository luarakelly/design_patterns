package flyweight.rpg_map_rendering;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class TileGraphicFactory {

    public static final int IMAGE_SIZE = 32;

    private final java.util.Map<String, TileGraphic> graphics = new HashMap<>();
    private int createdCount = 0;

    public TileGraphic getGraphic(String tileType) {
        TileGraphic graphic = graphics.get(tileType);
        if (graphic == null) {
            graphic = new TileGraphic(tileType, createImage(tileType));
            graphics.put(tileType, graphic);
            createdCount++;
            System.out.println("TileGraphicFactory: created graphic for \"" + tileType + "\"");
        }
        return graphic;
    }

    public int getCreatedCount() {
        return createdCount;
    }

    @FunctionalInterface
    private interface PixelPainter {
        Color colorAt(int x, int y);
    }

    private WritableImage createImage(String tileType) {
        PixelPainter painter = switch (tileType) {
            case "water" -> TileGraphicFactory::water;
            case "swamp" -> TileGraphicFactory::swamp;
            case "forest" -> TileGraphicFactory::forest;
            case "road" -> TileGraphicFactory::road;
            case "building" -> TileGraphicFactory::building;
            default -> (x, y) -> Color.MAGENTA;
        };

        WritableImage image = new WritableImage(IMAGE_SIZE, IMAGE_SIZE);
        PixelWriter writer = image.getPixelWriter();
        for (int y = 0; y < IMAGE_SIZE; y++) {
            for (int x = 0; x < IMAGE_SIZE; x++) {
                writer.setColor(x, y, painter.colorAt(x, y));
            }
        }
        return image;
    }

    private static double noise(int x, int y, int seed) {
        int h = x * 374761393 + y * 668265263 + seed * 1442695041;
        h = (h ^ (h >>> 13)) * 1274126177;
        h ^= h >>> 16;
        return (h & 0xFFFF) / 65536.0;
    }

    private static Color water(int x, int y) {
        double wave = Math.sin((x + 2 * Math.sin(y * 0.6)) * 0.4 + y * 0.9);
        if ((y % 8 == 2 || y % 8 == 3) && wave > 0.55) {
            return Color.rgb(170, 215, 245);
        }
        return Color.rgb(40, 110, 190).interpolate(Color.rgb(60, 140, 215), noise(x, y, 1) * 0.6);
    }

    private static Color swamp(int x, int y) {
        double d1 = Math.hypot(x - 9, y - 10);
        double d2 = Math.hypot(x - 23, y - 22);
        if (d1 < 6 || d2 < 5) {
            return Color.rgb(55, 80, 70).interpolate(Color.rgb(70, 100, 85), noise(x, y, 2));
        }
        if (noise(x, y, 3) > 0.93) {
            return Color.rgb(140, 150, 60);
        }
        return Color.rgb(85, 95, 45).interpolate(Color.rgb(100, 105, 55), noise(x, y, 4));
    }

    private static Color forest(int x, int y) {
        int[][] trees = {{8, 9}, {23, 7}, {15, 22}, {27, 25}, {4, 26}};
        for (int[] tree : trees) {
            if (Math.abs(x - tree[0]) <= 1 && y > tree[1] + 3 && y <= tree[1] + 6) {
                return Color.rgb(100, 65, 30);
            }
            double distance = Math.hypot(x - tree[0], y - tree[1]);
            if (distance < 5.5) {
                boolean highlight = x < tree[0] && y < tree[1];
                return highlight ? Color.rgb(60, 150, 60) : Color.rgb(25, 105, 40);
            }
        }
        return Color.rgb(75, 140, 55).interpolate(Color.rgb(90, 155, 65), noise(x, y, 5));
    }

    private static Color road(int x, int y) {
        if (y < 3 || y > 28) {
            return Color.rgb(110, 160, 70);
        }
        if (y == 3 || y == 28) {
            return Color.rgb(210, 210, 210);
        }
        if ((y == 15 || y == 16) && (x % 12) < 7) {
            return Color.rgb(245, 205, 50);
        }
        return Color.rgb(85, 85, 90).interpolate(Color.rgb(105, 105, 110), noise(x, y, 6));
    }

    private static Color building(int x, int y) {
        if (x >= 11 && x <= 20 && y >= 10 && y <= 20) {
            boolean frame = x == 11 || x == 20 || y == 10 || y == 20 || x == 15 || x == 16 || y == 15;
            return frame ? Color.rgb(240, 235, 220) : Color.rgb(120, 180, 220);
        }

        int rowIndex = y / 6;
        int offset = (rowIndex % 2 == 0) ? 0 : 6;
        if (y % 6 == 5 || (x + offset) % 12 == 11) {
            return Color.rgb(200, 190, 175);
        }
        return Color.rgb(165, 70, 50).interpolate(Color.rgb(185, 90, 65), noise(x, y, 7));
    }
}
