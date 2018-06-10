import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        World worldMap = new World(8, 8);

        System.out.println("Initialising Knights...");
        Knight redKnight = new Knight("red", 0, 0, "LIVE", 1, 1, false);
        Knight blueKnight = new Knight("blue", 7, 0, "LIVE", 1, 1, false);
        Knight greenKnight = new Knight("green", 7, 7, "LIVE", 1, 1, false);
        Knight yellowKnight = new Knight("yellow", 0, 7, "LIVE", 1, 1, false);


        System.out.println("Initialising Items...");
        Item axe = new Item("axe", 2, 2, 2.0, 0.0, false);
        Item dagger = new Item("dagger", 2, 5, 1.0, 0.0, false);
        Item helmet = new Item("helmet", 5, 5, 0.0, 1.0, false);
        Item magicStaff = new Item("magic staff", 5, 2, 1.0, 1.0, false);

        System.out.println("Adding Knights to the map...");
        worldMap.addKnight(redKnight);
        worldMap.addKnight(blueKnight);
        worldMap.addKnight(yellowKnight);
        worldMap.addKnight(greenKnight);

        System.out.println("Adding Items to the map...");
        worldMap.addItem(axe);
        worldMap.addItem(dagger);
        worldMap.addItem(helmet);
        worldMap.addItem(magicStaff);

        System.out.println("Reading list of commands...");
        Move moveTheKnights = new Move(redKnight, blueKnight, yellowKnight, greenKnight, axe, dagger, helmet, magicStaff);
        moveTheKnights.readTheFile();

        List<Knight> knightListForJson = new ArrayList<>();
        knightListForJson.add(redKnight);
        knightListForJson.add(blueKnight);
        knightListForJson.add(greenKnight);
        knightListForJson.add(yellowKnight);

        List<Item> itemListForJson = new ArrayList<>();
        itemListForJson.add(magicStaff);
        itemListForJson.add(helmet);
        itemListForJson.add(dagger);
        itemListForJson.add(axe);

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("final_state.json"));
        bufferedWriter.write("{  \n");
        for (int i = 0; i <knightListForJson.size(); i++) {

            if (knightListForJson.get(i).getName().equals("red"))
                bufferedWriter.write("    red: ");
            else if (knightListForJson.get(i).getName().equals("blue"))
                bufferedWriter.write("    blue: ");
            else if (knightListForJson.get(i).getName().equals("green"))
                bufferedWriter.write("    green: ");
            else if (knightListForJson.get(i).getName().equals("yellow"))
                bufferedWriter.write("    yellow: ");

            if (knightListForJson.get(i).getStatus().equalsIgnoreCase("DEAD") ||knightListForJson.get(i).getStatus().equalsIgnoreCase("DROWNED"))
                bufferedWriter.write("[(None,");
            else
                bufferedWriter.write("[(" + knightListForJson.get(i).getRow() + "," + knightListForJson.get(i).getCol() + "),");
            if (knightListForJson.get(i).getStatus().equals("LIVE"))
                bufferedWriter.write("LIVE,");
            else if (knightListForJson.get(i).getStatus().equals("DEAD"))
                bufferedWriter.write("DEAD,");
            else if (knightListForJson.get(i).getStatus().equals("DROWNED"))
                bufferedWriter.write("DROWNED,");

            if (knightListForJson.get(i).getHasItem())
                bufferedWriter.write(knightListForJson.get(i).getItem().getName() + ",");
            else
                bufferedWriter.write("None,");

            bufferedWriter.write(Integer.toString(knightListForJson.get(i).getAttackScore()) + "," + Integer.toString(knightListForJson.get(i).getDefenseScore()) + "],\n");

        }

        for (int i = 0; i <itemListForJson.size(); i++) {

            if (itemListForJson.get(i).getName().equals("magic staff"))
                bufferedWriter.write("    magic_staff: ");
            else if (itemListForJson.get(i).getName().equals("helmet"))
                bufferedWriter.write("    helmet: ");
            else if (itemListForJson.get(i).getName().equals("dagger"))
                bufferedWriter.write("    dagger: ");
            else
                bufferedWriter.write("    axe: ");
            bufferedWriter.write("[(" + itemListForJson.get(i).getRow() + "," + itemListForJson.get(i).getCol() + "),");

            if (itemListForJson.get(i).getIsEquipped())
                bufferedWriter.write("True],\n");
            else
                bufferedWriter.write("False],\n");
        }
        bufferedWriter.write("}");
        bufferedWriter.close();
        System.out.println(" \nGame over! Please check 'final_state.json' file for the result");
    }
}
