import java.util.List;

public class Knight extends Entity {

    private String status;
    private int attackScore;
    private int defenseScore;
    private boolean hasItem;
    private Item item;

    public Item getItem() {
        return this.item;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAttackScore() {
        return attackScore;
    }

    public void setAttackScore(int attackScore) {
        this.attackScore = attackScore;
    }

    public int getDefenseScore() {
        return defenseScore;
    }

    public void setDefenseScore(int defenseScore) {
        this.defenseScore = defenseScore;
    }

    public boolean getHasItem() {
        return hasItem;
    }

    Knight(String name, int row, int col, String status, int attackScore, int defenseScore, Boolean hasItem) {
        super(name, row, col);
        this.status = status;
        this.attackScore = attackScore;
        this.defenseScore = defenseScore;
        this.hasItem = hasItem;
    }

    public void checkForItems(List<Item> itemEntities) {
        for (int i = 0; i < itemEntities.size(); i++) {
            if (this.getCol() == itemEntities.get(i).getCol() && (this.getRow() == itemEntities.get(i).getRow())) {
                if (!this.hasItem) {
                    equipItem(itemEntities.get(i));
                }
            }
        }
    }

    public void checkForKnights(List<Knight> knightEntities) {
        for (int i = 0; i < knightEntities.size(); i++) {
            if (this.getCol() == knightEntities.get(i).getCol() && (this.getRow() == knightEntities.get(i).getRow())) {
                if (this.getName() != knightEntities.get(i).getName()) {
                    this.attack(knightEntities.get(i));
                }
            }
        }
    }

    public void equipItem(Item item) {
        if(item.getIsEquipped()) {
            System.out.println("Sire, the " + item.getName() + " is already carried by another knight");
        } else {
            this.item = item;
            this.hasItem = true;
            this.increaseKnightAttack(item.getAttackBoost());
            this.increaseKnightDefense(item.getDefenceBoost());
            item.setIsEquipped(true);
            System.out.println(this.getName() + " has equipped the " + this.item.getName());
        }
    }

    public void dropItem(Item item) {
        this.item = item;
        item.setIsEquipped(false);
        this.hasItem = false;
    }

    public void throwItemToBank(Item item) {
        if (item.getRow() > 7) {
            item.setRow(7);
        } else if (item.getRow() < 0) {
            item.setRow(0);
        } else if (item.getCol() > 7) {
            item.setCol(7);
        } else if (item.getCol() < 0) {
            item.setCol(0);
        }
        this.hasItem = false;
        item.setIsEquipped(false);
    }

    public void increaseKnightAttack(double attackBonus) {

        this.attackScore += attackBonus;
    }

    public void increaseKnightDefense(double defenseBonus) {

        this.defenseScore += defenseBonus;
    }

    public void attack(Knight enemyKnight) {
        if(enemyKnight.getStatus().equalsIgnoreCase("DEAD")){
            System.out.print(this.getName() + " awkwardly steps over " + enemyKnight.getName() + "'s body and utters a small prayer for his fallen brethren");
        } else {
            System.out.print(this.getName() + " knight attacked " + enemyKnight.getName() + " knight!");
            this.increaseKnightAttack(0.5);
            if (this.getAttackScore() > enemyKnight.getDefenseScore()) {
                if (!this.hasItem) {
                    this.equipItem(enemyKnight.item);
                }
                enemyKnight.die();
            } else {
                if (!enemyKnight.hasItem) {
                    enemyKnight.equipItem(this.item);
                }
                this.die();
            }
        }
    }

    public void die() {

        this.setStatus("DEAD");
        if (this.hasItem) {
            this.dropItem(item);
        }
        this.setAttackScore(0);
        this.setDefenseScore(0);
        System.out.println(" My lord, " + this.getName() + " knight hath been slain!");
    }

    public void drown() {

        if (this.hasItem) {
            this.throwItemToBank(item);
        }
        this.setAttackScore(0);
        this.setDefenseScore(0);
        this.setStatus("DROWNED");
        System.out.println(this.getName() + " knight fell into the abyss below!");
    }

    public int moveKnight(char direction) {

        if (direction == 'N') {
            if (this.hasItem) {
                this.item.moveItem('N');
            }
            return this.row -= 1;
        } else if (direction == 'S') {
            if (this.hasItem) {
                this.item.moveItem('S');
            }
            return this.row += 1;
        } else if (direction == 'E') {
            if (this.hasItem) {
                this.item.moveItem('E');
            }
            return this.col += 1;
        } else if (direction == 'W') {
            if (this.hasItem) {
                this.item.moveItem('W');
            }
            return this.col -= 1;
        }
        return 1;
    }

    public void checkKnightPosition() {

        if (this.getCol() < 0 || this.getCol() > 7 || this.getRow() < 0 || this.getRow() > 7) {
            this.drown();
        }
    }
}
