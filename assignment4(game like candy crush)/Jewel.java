import java.util.ArrayList;

public abstract class Jewel {

    protected int row;
    protected int column;
    protected IGameGrid gameGrid;

    public Jewel(IGameGrid gameGrid, int row, int column) {
        this.gameGrid = gameGrid;
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public IGameGrid getGameGrid() {
        return gameGrid;
    }

    public abstract String getName();

    public abstract ArrayList<Jewel> go(int row, int column);

    public abstract int getPoint();
}
