import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Move {

    private Knight redKnight;
    private Knight blueKnight;
    private Knight yellowKnight;
    private Knight greenKnight;
    private Item axe;
    private Item dagger;
    private Item helmet;
    private Item magicStaff;
    private ArrayList<Knight> knightEntities = new ArrayList<>();
    private ArrayList<Item> itemEntities = new ArrayList<>();
    private List<String> commandList = new ArrayList<>();

    //add the knights into the knights array
    public Move(Knight redKnight, Knight blueKnight, Knight yellowKnight, Knight greenKnight, Item axe, Item dagger, Item helmet, Item magicStaff) {
        this.redKnight = redKnight;
        this.blueKnight = blueKnight;
        this.yellowKnight = yellowKnight;
        this.greenKnight = greenKnight;
        this.axe = axe;
        this.dagger = dagger;
        this.helmet = helmet;
        this.magicStaff = magicStaff;
    }

    public void addKnightsToList() {
        knightEntities.add(redKnight);
        knightEntities.add(blueKnight);
        knightEntities.add(greenKnight);
        knightEntities.add(yellowKnight);
    }

    public void addItemsToList() {
        itemEntities.add(axe);
        itemEntities.add(dagger);
        itemEntities.add(helmet);
        itemEntities.add(magicStaff);
    }

    public void readTheFile() throws IOException {

        addKnightsToList();
        addItemsToList();

        Stream<String> stream = Files.lines(Paths.get("moves.txt"));
        stream.forEach(x -> commandList.add(x));

        if (commandList.get(0).equalsIgnoreCase("GAME-START") && commandList.get(commandList.size() - 1).equalsIgnoreCase("GAME-END")) {
            commandList.remove(0);
            commandList.remove(commandList.size() - 1);
            System.out.println("Game has start and end has been defined!");
            System.out.println("Reading moves.txt");

            for (int i = 0; i < commandList.size(); i++) {

                String command = commandList.get(i);
                String knightIdentifier = command.substring(0, 1);
                String moveIdentifier = command.substring(2, 3);

                        for (int j = 0; j< knightEntities.size(); j++) {
                            if (knightIdentifier.equalsIgnoreCase(knightEntities.get(j).getName().substring(0, 1))) {

                            if (moveIdentifier.equals("N")) {
                                knightEntities.get(j).moveKnight('N');
                            } else if (moveIdentifier.equals("S")) {
                                knightEntities.get(j).moveKnight('S');
                            } else if (moveIdentifier.equals("E")) {
                                knightEntities.get(j).moveKnight('E');
                            } else if (moveIdentifier.equals("W")) {
                               knightEntities.get(j).moveKnight('W');
                            }
                            knightEntities.get(j).checkKnightPosition();

                            if ( knightEntities.get(j).getStatus().equalsIgnoreCase("DROWNED") || knightEntities.get(j).getStatus().equalsIgnoreCase("DEAD")) {
                                knightEntities.remove(j);
                                } else {
                                knightEntities.get(j).checkForItems(itemEntities);
                                knightEntities.get(j).checkForKnights(knightEntities);
                            }
                        }
                    }
                }
            }
        }
    }

