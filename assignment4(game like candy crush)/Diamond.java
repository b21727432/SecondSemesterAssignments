import java.util.ArrayList;

public class Diamond extends Jewel {

    public Diamond(IGameGrid gameGrid, int row, int column) {
        super(gameGrid, row, column);
    }

    @Override
    public String getName() {
        return "D";
    }

    @Override
    public ArrayList<Jewel> go(int row, int column) {
        boolean finished = false;
        ArrayList<Jewel> result = new ArrayList<>();
        try {
            Jewel jewel = this.gameGrid.getItem(row - 1, column - 1);
            if (getName().equals(jewel.getName())) {
                Jewel staticJewel = this.gameGrid.getItem(row - 2, column - 2);
                if (getName().equals(staticJewel.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row - 1, column - 1));
                    result.add(this.gameGrid.getItem(row - 2, column - 2));
                    finished = true;
                }
            }

        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            Jewel jewel1 = this.gameGrid.getItem(row + 1, column + 1);
            if (getName().equals(jewel1.getName()) && finished == false) {
                Jewel staticJewel = this.gameGrid.getItem(row + 2, column + 2);
                if (getName().equals(staticJewel.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row + 1, column + 1));
                    result.add(this.gameGrid.getItem(row + 2, column + 2));
                    finished = true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            Jewel jewel2 = this.gameGrid.getItem(row - 1, column + 1);
            if (getName().equals(jewel2.getName()) && finished == false) {
                Jewel staticJewel = this.gameGrid.getItem(row - 2, column + 2);
                if (getName().equals(staticJewel.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row - 1, column + 1));
                    result.add(this.gameGrid.getItem(row - 2, column + 2));
                    finished = true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            Jewel jewel3 = this.gameGrid.getItem(row + 1, column - 1);
            if (getName().equals(jewel3.getName()) && finished == false) {
                Jewel staticJewel = this.gameGrid.getItem(row + 2, column - 2);
                if (getName().equals(staticJewel.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row + 1, column - 1));
                    result.add(this.gameGrid.getItem(row + 2, column - 2));
                    finished = true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return result;
    }

    @Override
    public int getPoint() {
        return 30;
    }
}
