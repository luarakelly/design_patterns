package flyweight.rpg_map_rendering;

import java.util.Random;

public class WildernessMap extends Map {

    private final Random random = new Random();

    public WildernessMap(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    public Tile createTile() {
        int tileType = random.nextInt(3);

        if (tileType == 0) {
            return new SwampTile();
        } else if (tileType == 1) {
            return new WaterTile();
        } else {
            return new ForestTile();
        }
    }
}
