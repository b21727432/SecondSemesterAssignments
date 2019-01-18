import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameGrid implements IGameGrid {

    private int row;
    private int column;
    private Jewel[][] grid;

    public GameGrid(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < this.row; row++) {
            for (int col = 0; col < this.column; col++) {
                Jewel jewel = this.grid[row][col];
                if (jewel == null) {
                    sb.append(" ");
                } else {
                    sb.append(jewel.getName());
                }
                sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public void init(String filePath) throws IOException {
        this.grid = new Jewel[row][column];
        BufferedReader filePathReader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = filePathReader.readLine()) != null) {
            for (int i = 0; i < this.row; i++) {
                String[] jewels = line.split(" ");
                for (int j = 0; j < this.column; j++) {
                    if ("D".equals(jewels[j]))
                        this.grid[i][j] = new Diamond(this, i, j);
                    else if ("T".equals(jewels[j]))
                        this.grid[i][j] = new Triangle(this, i, j);
                    else if ("S".equals(jewels[j]))
                        this.grid[i][j] = new Square(this, i, j);
                    else if ("W".equals(jewels[j]))
                        this.grid[i][j] = new Wildcard(this, i, j);
                    else if ("\\".equals(jewels[j]))
                        this.grid[i][j] = new MathematicalSymbolBackSlash(this, i, j);
                    else if ("/".equals(jewels[j]))
                        this.grid[i][j] = new MathematicalSymbolSlash(this, i, j);
                    else if ("+".equals(jewels[j]))
                        this.grid[i][j] = new MathematicalSymbolPlus(this, i, j);
                    else if ("-".equals(jewels[j]))
                        this.grid[i][j] = new MathematicalSymbolMinus(this, i, j);
                    else if ("|".equals(jewels[j]))
                        this.grid[i][j] = new MathematicalSymbolLogicalOr(this, i, j);
                }
                line = filePathReader.readLine();
            }
        }

    }

    public int play(int row, int column) {
        if (row >= this.row || column >= this.column) {
            return -1;
        }

        Jewel jewel = getItem(row, column);
        if (jewel == null)
            return 0;

        ArrayList<Jewel> result = jewel.go(row, column);
        if (result == null || result.size() == 0)
            return 0;

        int point = 0;
        for (int i = 0; i < result.size(); i++) {
            Jewel selectedJewel = result.get(i);

            this.grid[selectedJewel.getRow()][selectedJewel.getColumn()] = null;
            point += selectedJewel.getPoint();
        }

        rearrangeGrid();

        return point;
    }

    public Jewel getItem(int row, int column) {
        return this.grid[row][column];
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }


    private void rearrangeGrid() {
        for (int i = row - 1; i >= 0; i--) {
            for (int j = column - 1; j >= 0; j--) {
                if (i > 0 && this.grid[i][j] == null) {
                    Jewel staticJewel = this.grid[i - 1][j];
                    this.grid[i][j] = staticJewel;
                    this.grid[i - 1][j] = null;
                    if (i + 1 <= row - 1 && this.grid[i + 1][j] == null) {
                        Jewel staticJewel2 = this.grid[i][j];
                        this.grid[i + 1][j] = staticJewel2;
                        this.grid[i][j] = null;
                        if (i + 2 <= row - 1 && this.grid[i + 2][j] == null) {
                            Jewel staticJewel3 = this.grid[i + 1][j];
                            this.grid[i + 2][j] = staticJewel3;
                            this.grid[i + 1][j] = null;
                        }
                    }
                }
            }
        }
    }
}
