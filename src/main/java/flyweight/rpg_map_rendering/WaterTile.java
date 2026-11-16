package flyweight.rpg_map_rendering;

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
