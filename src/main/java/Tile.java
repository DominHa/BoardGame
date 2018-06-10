import java.util.ArrayList;
import java.util.List;

public class Tile {

    final int xPos,yPos;

    List<Entity> entities = new ArrayList<>();

    public Tile(int xPosition, int yPosition) {
        xPos = xPosition;
        yPos = yPosition;
    }

    public void addItem(Item item){
        entities.add(item);
    }

    public void addKnight(Knight k){
        entities.add(k);
    }
}