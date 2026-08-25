package src.main.java.factory.rpg_map_generator;

import java.util.Random;

public class WildernessMap extends Map {

    private final Random random = new Random();

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