import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    private static ArrayList<LeaderboardItem> leaderboard;

    public static void main(String[] args) throws IOException {

        // Let's play game
        String gameGridFile = "gameGrid.txt";
        IGameGrid gameGrid = initGameGrid(gameGridFile);
        System.out.println("Game grid:\n\n" + gameGrid.print());

        //Load players list
        leaderboard = loadPoints();

        InputStreamReader isReader = new InputStreamReader(System.in);
        BufferedReader bufReader = new BufferedReader(isReader);
        int totalPoint = 0;
        while (true) {
            System.out.print("Select coordinate or enter E to end the game: ");
            String inputStr;
            if ((inputStr = bufReader.readLine()) != null) {
                int userSelectionRow = 0;
                int userSelectionColumn = 0;
                if ("E".equalsIgnoreCase(inputStr)) {
                    System.out.println("\nTotal score: " + totalPoint + " points\n");
                    System.out.print("Enter name: ");
                    inputStr = bufReader.readLine();
                    addPoint(inputStr, totalPoint);
                    printRank(inputStr);
                    System.out.println("\nGood bye!");
                    break;
                } else {
                    String[] pairs = inputStr.split(" ");
                    userSelectionRow = Integer.parseInt(pairs[0]);
                    userSelectionColumn = Integer.parseInt(pairs[1]);
                }

                int point = gameGrid.play(userSelectionRow, userSelectionColumn);

                System.out.println("\n" + gameGrid.print());

                if (point < 0) {
                    System.out.println("Please enter right coordinates!");
                } else if (point == 0) {
                    System.out.println("Does not have any matches.");
                } else {
                    totalPoint += point;
                    System.out.println("Score: " + point + " points!");
                }
            } else {
                System.out.println("Command is empty. Please try again.");
            }
        }
    }


    private static void addPoint(String name, int point) {
        boolean isFound = false;
        for (int i = 0; i < leaderboard.size(); i++) {
            LeaderboardItem item = leaderboard.get(i);
            if (name.equalsIgnoreCase(item.getName())) {
                isFound = true;
                if (point > item.getPoint()) {
                    item.setPoint(point);
                }
            }
        }
        if (!isFound) {
            LeaderboardItem item = new LeaderboardItem();
            item.setName(name);
            item.setPoint(point);
            leaderboard.add(item);
        }

        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter out = null;
        try {
            fw = new FileWriter("leaderboard.txt", false);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            for (int i = 0; i < leaderboard.size(); i++) {
                LeaderboardItem item = leaderboard.get(i);
                out.println(item.getName() + " " + item.getPoint());
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<LeaderboardItem> loadPoints() {
        ArrayList<LeaderboardItem> result = new ArrayList<>();
        try {
            BufferedReader filePathReader = new BufferedReader(new FileReader("leaderboard.txt"));
            String line;
            while ((line = filePathReader.readLine()) != null) {
                String[] pairs = line.split(" ");
                LeaderboardItem item = new LeaderboardItem();
                item.setName(pairs[0]);
                item.setPoint(Integer.parseInt(pairs[1]));
                result.add(item);
            }
        } catch (Exception e) {
        }
        return result;
    }

    private static void printRank(String name) {
        // Sort leader bord
        leaderboard.sort(new LeaderboardItemRanking());

        // Find name by binary search
        ArrayList<LeaderboardItem> searchListOrderedByName = new ArrayList<>(leaderboard);
        Collections.sort(searchListOrderedByName);
        LeaderboardItem searchItem = new LeaderboardItem();
        searchItem.setName(name);
        int idx = Collections.binarySearch(searchListOrderedByName, searchItem);
        if (idx > -1) {
            LeaderboardItem targetItem = searchListOrderedByName.get(idx);
            int rank = leaderboard.indexOf(targetItem);
            LeaderboardItem previousUser = null;
            if (rank > 0) {
                previousUser = leaderboard.get(rank - 1);
            }
            LeaderboardItem nextUser = null;
            if (rank < leaderboard.size() - 1) {
                nextUser = leaderboard.get(rank + 1);
            }
            System.out.print("Your rank is " + Integer.toString(rank + 1) + "/" + Integer.toString(searchListOrderedByName.size()));
            if (nextUser != null || previousUser != null) {
                System.out.print(", your score ");
            }
            if (nextUser != null) {
                System.out.print("is " + Integer.toString(targetItem.getPoint() - nextUser.getPoint()) + " points higher than " + nextUser.getName());
            }
            if (nextUser != null && previousUser != null) {
                System.out.print(" and ");
            }
            if (previousUser != null) {
                System.out.print("is " + Integer.toString(previousUser.getPoint() - targetItem.getPoint()) + " points lower than " + previousUser.getName());
            }
            System.out.println(" ");
        } else {
            System.out.println("Leaderboard does not contain [" + name + "].");
        }
    }

    private static IGameGrid initGameGrid(String filePath) throws IOException {

        // Init game
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int rows = 0;
        String[] line = reader.readLine().split(" ");
        int columns = line.length;
        reader = new BufferedReader(new FileReader(filePath));
        while (reader.readLine() != null) {
            rows++;
        }
        IGameGrid gameGrid = new GameGrid(rows, columns);
        gameGrid.init(filePath);
        return gameGrid;
    }
}

