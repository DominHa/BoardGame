public class Item extends Entity {

    private double attackBoost;
    private double defenceBoost;
    private boolean isEquipped;

    public double getAttackBoost() {
        return attackBoost;
    }

    public double getDefenceBoost() {
        return defenceBoost;
    }

    public boolean getIsEquipped() {
        return isEquipped;
    }

    public void setIsEquipped(boolean isEquipped) {
        this.isEquipped = isEquipped;
    }

    Item(String name, int row, int col, double attackBoost, double defenceBoost, boolean isEquipped) {
        super(name, row, col);
        this.attackBoost = attackBoost;
        this.defenceBoost = defenceBoost;
        this.isEquipped = isEquipped;
    }

    public int moveItem(char direction) {

        if (direction == 'N') {
            return this.row -= 1;
        } else if (direction == 'S') {
            return this.row += 1;
        } else if (direction == 'E') {
            return this.col += 1;
        } else if (direction == 'W') {
            return this.col -= 1;
        }
        return 0;
    }
}