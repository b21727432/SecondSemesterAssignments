import java.util.ArrayList;

public class Triangle extends Jewel {

    public Triangle(IGameGrid gameGrid, int row, int column)
    {
        super(gameGrid, row, column);
    }

    @Override
    public String getName() {
        return "T";
    }

    @Override
    public ArrayList<Jewel> go(int row, int column) {
        boolean finished = false;
        ArrayList<Jewel> result = new ArrayList<>();
        try{
            Jewel jewel = this.gameGrid.getItem(row-1,column);
            if (getName().equals(jewel.getName())){
                Jewel jewel1 = this.gameGrid.getItem(row-2,column);
                if(getName().equals(jewel1.getName())){
                    result.add(this.gameGrid.getItem(row,column));
                    result.add(this.gameGrid.getItem(row-1,column));
                    result.add(this.gameGrid.getItem(row-2,column));
                    finished = true;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e){

        }
        try{
            Jewel jewel = this.gameGrid.getItem(row+1,column);
            if(getName().equals(jewel.getName()) && finished == false){
                Jewel jewel1 = this.gameGrid.getItem(row+2,column);
                if (getName().equals(jewel1.getName())){
                    result.add(this.gameGrid.getItem(row,column));
                    result.add(this.gameGrid.getItem(row+1,column));
                    result.add(this.gameGrid.getItem(row+2,column));
                    finished = true;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e){

        }
        return result;
    }

    @Override
    public int getPoint() {
        return 15;
    }
}



