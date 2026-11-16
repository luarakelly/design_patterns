package flyweight.rpg_map_rendering;

public abstract class Map {

    private final int rows;
    private final int columns;
    private final Tile[][] tiles;

    protected Map(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.tiles = new Tile[rows][columns];
    }

    public abstract Tile createTile();

    public void generate() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                tiles[row][column] = createTile();
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Tile getTile(int row, int column) {
        return tiles[row][column];
    }

    public void display() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                System.out.print(tiles[row][column].getCharacter() + " ");
            }
            System.out.println();
        }
    }
}
