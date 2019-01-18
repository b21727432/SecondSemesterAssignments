import java.io.IOException;

public interface IGameGrid {

    String print();

    void init(String filePath) throws IOException;

    Jewel getItem(int row, int column);

    int getRow();

    int getColumn();

    int play(int row, int column);
}
