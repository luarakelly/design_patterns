package flyweight.rpg_map_rendering;

public class BuildingTile implements Tile {

    @Override
    public char getCharacter() {
        return 'B';
    }

    @Override
    public String getType() {
        return "building";
    }

}
