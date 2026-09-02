package factory.rpg_map_generator;

public class WaterTile implements Tile {

    @Override
    public char getCharacter() {
        return 'W';
    }

    @Override
    public String getType() {
        return "water";
    }

}
