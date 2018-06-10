public class Entity {

    public String name;
    public int row;
    public int col;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    Entity(String name, int row, int col) {
        this.name = name;
        this.row = row;
        this.col = col;
    }
}