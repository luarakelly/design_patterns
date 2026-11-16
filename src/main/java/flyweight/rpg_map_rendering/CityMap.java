package flyweight.rpg_map_rendering;

import java.util.Random;

public class CityMap extends Map {

    private final Random random = new Random();

    public CityMap(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    public Tile createTile() {
        int tileType = random.nextInt(3);

        if (tileType == 0) {
            return new RoadTile();
        } else if (tileType == 1) {
            return new ForestTile();
        } else {
            return new BuildingTile();
        }
    }
}
