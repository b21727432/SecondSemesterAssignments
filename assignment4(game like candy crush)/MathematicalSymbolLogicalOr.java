import java.util.ArrayList;

public class MathematicalSymbolLogicalOr extends Jewel {

    public MathematicalSymbolLogicalOr(IGameGrid gameGrid, int row, int column)
    {
        super(gameGrid, row, column);
    }

    @Override
    public String getName() {
        return "|";
    }

    @Override
    public ArrayList<Jewel> go(int row, int column) {
        ArrayList<Jewel> result = new ArrayList<>();
        boolean finished = false;
        try {
            Jewel jewel = this.gameGrid.getItem(row - 1, column);
            if ("+".equals(jewel.getName()) || getName().equals(jewel.getName())) {
                Jewel jewel1 = this.gameGrid.getItem(row - 2, column);
                if ("/".equals(jewel1.getName()) || "-".equals(jewel1.getName()) || "+".equals(jewel1.getName()) || getName().equals(jewel1.getName()) || "\\".equals(jewel1.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row - 1, column));
                    result.add(this.gameGrid.getItem(row - 2, column));
                    finished = true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        try {
            Jewel jewel = this.gameGrid.getItem(row + 1, column);
            if (("+".equals(jewel.getName()) || getName().equals(jewel.getName())) && finished == false) {
                Jewel jewel1 = this.gameGrid.getItem(row + 2, column);
                if ("/".equals(jewel1.getName()) || "-".equals(jewel1.getName()) || "+".equals(jewel1.getName()) || getName().equals(jewel1.getName()) || "\\".equals(jewel1.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row + 1, column));
                    result.add(this.gameGrid.getItem(row + 2, column));
                    finished = true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return result;
    }

    @Override
    public int getPoint() {
        return 20;
    }
}
