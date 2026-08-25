package src.main.java.factory.rpg_map_generator;

public abstract class Map {

    private static final int ROWS = 10;
    private static final int COLUMNS = 10;

    public abstract Tile createTile();

    public void display() {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                Tile tile = createTile();
                System.out.print(tile.getCharacter() + " ");
            }

            System.out.println();
        }
    }
}
