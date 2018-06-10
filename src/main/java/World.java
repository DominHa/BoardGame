import java.util.List;
import java.util.ArrayList;

public class World {

    Tile[][] coordinates;
    List<Tile> tiles = new ArrayList<>();

    public World(int width, int height) {
        coordinates = new Tile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                coordinates[i][j] = new Tile(i, j);
                tiles.add(coordinates[i][j]);
            }
        }
    }

    public void addItem(Item item) {
        int x = item.getRow();
        int y = item.getCol();
        coordinates[x][y].addItem(item);
    }

    public void addKnight(Knight k) {
        int x = k.getRow();
        int y = k.getCol();
        coordinates[x][y].addKnight(k);
    }
}