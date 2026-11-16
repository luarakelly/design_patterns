package flyweight.rpg_map_rendering;

public class RoadTile implements Tile {

    @Override
    public char getCharacter() {
        return 'R';
    }

    @Override
    public String getType() {
        return "road";
    }

}
