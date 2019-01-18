import java.util.ArrayList;

public class Wildcard extends Jewel {

    public Wildcard(IGameGrid gameGrid, int row, int column) {
        super(gameGrid, row, column);
    }

    @Override
    public String getName() {
        return "W";
    }

    @Override
    public ArrayList<Jewel> go(int row, int column) {
        ArrayList<Jewel> result = new ArrayList<>();
        boolean finished = false;
        try {
            Jewel jewel = this.gameGrid.getItem(row - 1, column);
            Jewel jewel1 = this.gameGrid.getItem(row - 2, column);
            String staticJewelName = jewel.getName();
            if ("+".equals(staticJewelName) || "|".equals(staticJewelName)) {
                if ("/".equals(jewel1.getName()) || "\\".equals(jewel1.getName()) || "-".equals(jewel1.getName()) || "+".equals(jewel1.getName()) || "|".equals(jewel1.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row - 1, column));
                    result.add(this.gameGrid.getItem(row - 2, column));
                    finished = true;
                }
            } else if ("T".equals(staticJewelName)) {
                if (staticJewelName.equals(jewel1.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row - 1, column));
                    result.add(this.gameGrid.getItem(row - 2, column));
                    finished = true;
                }
            } else if (getName().equals(staticJewelName)) {
                result.add(this.gameGrid.getItem(row, column));
                result.add(this.gameGrid.getItem(row - 1, column));
                result.add(this.gameGrid.getItem(row - 2, column));
                finished = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        try {
            Jewel jewel = this.gameGrid.getItem(row + 1, column);
            Jewel jewel1 = this.gameGrid.getItem(row + 2, column);
            String staticJewelName = jewel.getName();
            if (("+".equals(staticJewelName) || "|".equals(staticJewelName)) && finished == false) {
                if ("/".equals(jewel1.getName()) || "\\".equals(jewel1.getName()) || "-".equals(jewel1.getName()) || "+".equals(jewel1.getName()) || "|".equals(jewel1.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row + 1, column));
                    result.add(this.gameGrid.getItem(row + 2, column));
                    finished = true;
                }
            } else if (("T".equals(staticJewelName)) && finished == false) {
                if (staticJewelName.equals(jewel1.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row + 1, column));
                    result.add(this.gameGrid.getItem(row + 2, column));
                    finished = true;
                }
            } else if ((getName().equals(staticJewelName)) && finished == false) {
                result.add(this.gameGrid.getItem(row, column));
                result.add(this.gameGrid.getItem(row + 1, column));
                result.add(this.gameGrid.getItem(row + 2, column));
                finished = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        try {
            Jewel jewel = this.gameGrid.getItem(row, column - 1);
            Jewel jewel1 = this.gameGrid.getItem(row, column - 2);
            String staticJewelName = jewel.getName();
            if (("-".equals(staticJewelName) || "+".equals(staticJewelName)) && finished == false) {
                if ("/".equals(jewel1.getName()) || "\\".equals(jewel1.getName()) || "-".equals(jewel1.getName()) || "+".equals(jewel1.getName()) || "|".equals(jewel1.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row, column - 1));
                    result.add(this.gameGrid.getItem(row, column - 2));
                    finished = true;
                }
            } else if (("S".equals(staticJewelName)) && finished == false) {
                if (staticJewelName.equals(jewel1.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row, column - 1));
                    result.add(this.gameGrid.getItem(row, column - 2));
                    finished = true;
                }
            } else if ((getName().equals(staticJewelName)) && finished == false) {
                result.add(this.gameGrid.getItem(row, column));
                result.add(this.gameGrid.getItem(row, column - 1));
                result.add(this.gameGrid.getItem(row, column - 2));
                finished = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        try {
            Jewel jewel = this.gameGrid.getItem(row, column + 1);
            Jewel jewel1 = this.gameGrid.getItem(row, column + 2);
            String staticJewelName = jewel.getName();
            if (("-".equals(staticJewelName) || "+".equals(staticJewelName)) && finished == false) {
                if ("/".equals(jewel1.getName()) || "\\".equals(jewel1.getName()) || "-".equals(jewel1.getName()) || "+".equals(jewel1.getName()) || "|".equals(jewel1.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row, column + 1));
                    result.add(this.gameGrid.getItem(row, column + 2));
                    finished = true;
                }
            } else if (("S".equals(staticJewelName)) && finished == false) {
                if (staticJewelName.equals(jewel1.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row, column + 1));
                    result.add(this.gameGrid.getItem(row, column + 2));
                    finished = true;
                }
            } else if ((getName().equals(staticJewelName)) && finished == false) {
                result.add(this.gameGrid.getItem(row, column));
                result.add(this.gameGrid.getItem(row, column + 1));
                result.add(this.gameGrid.getItem(row, column + 2));
                finished = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        try {
            Jewel jewel = this.gameGrid.getItem(row - 1, column - 1);
            Jewel jewel1 = this.gameGrid.getItem(row - 2, column - 2);
            String staticJewelName = jewel.getName();
            if (("\\".equals(staticJewelName)) && finished == false) {
                if ("/".equals(jewel1.getName()) || "\\".equals(jewel1.getName()) || "-".equals(jewel1.getName()) || "+".equals(jewel1.getName()) || "|".equals(jewel1.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row - 1, column - 1));
                    result.add(this.gameGrid.getItem(row - 2, column - 2));
                    finished = true;
                }
            } else if (("D".equals(staticJewelName)) && finished == false) {
                if (staticJewelName.equals(jewel1.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row - 1, column - 1));
                    result.add(this.gameGrid.getItem(row - 2, column - 2));
                    finished = true;
                }
            } else if ((getName().equals(staticJewelName)) && finished == false) {
                result.add(this.gameGrid.getItem(row, column));
                result.add(this.gameGrid.getItem(row - 1, column - 1));
                result.add(this.gameGrid.getItem(row - 2, column - 2));
                finished = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        try {
            Jewel jewel = this.gameGrid.getItem(row + 1, column + 1);
            Jewel jewel1 = this.gameGrid.getItem(row + 2, column + 2);
            String staticJewelName = jewel.getName();
            if (("\\".equals(staticJewelName)) && finished == false) {
                if ("/".equals(jewel1.getName()) || "\\".equals(jewel1.getName()) || "-".equals(jewel1.getName()) || "+".equals(jewel1.getName()) || "|".equals(jewel1.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row + 1, column + 1));
                    result.add(this.gameGrid.getItem(row + 2, column + 2));
                    finished = true;
                }
            } else if (("D".equals(staticJewelName)) && finished == false) {
                if (staticJewelName.equals(jewel1.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row + 1, column + 1));
                    result.add(this.gameGrid.getItem(row + 1, column + 2));
                    finished = true;
                }
            } else if ((getName().equals(staticJewelName)) && finished == false) {
                result.add(this.gameGrid.getItem(row, column));
                result.add(this.gameGrid.getItem(row + 1, column + 1));
                result.add(this.gameGrid.getItem(row + 2, column + 2));
                finished = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        try {
            Jewel jewel = this.gameGrid.getItem(row - 1, column + 1);
            Jewel jewel1 = this.gameGrid.getItem(row - 2, column + 2);
            String staticJewelName = jewel.getName();
            if (("/".equals(staticJewelName)) && finished == false) {
                if ("/".equals(jewel1.getName()) || "\\".equals(jewel1.getName()) || "-".equals(jewel1.getName()) || "+".equals(jewel1.getName()) || "|".equals(jewel1.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row - 1, column + 1));
                    result.add(this.gameGrid.getItem(row - 2, column + 2));
                    finished = true;
                }
            } else if (("D".equals(staticJewelName)) && finished == false) {
                if (staticJewelName.equals(jewel1.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row - 1, column + 1));
                    result.add(this.gameGrid.getItem(row - 2, column + 2));
                    finished = true;
                }
            } else if ((getName().equals(staticJewelName)) && finished == false) {
                result.add(this.gameGrid.getItem(row, column));
                result.add(this.gameGrid.getItem(row - 1, column + 1));
                result.add(this.gameGrid.getItem(row - 2, column + 2));
                finished = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        try {
            Jewel jewel = this.gameGrid.getItem(row + 1, column - 1);
            Jewel jewel1 = this.gameGrid.getItem(row + 2, column - 2);
            String staticJewelName = jewel.getName();
            if (("/".equals(staticJewelName)) && finished == false) {
                if ("/".equals(jewel1.getName()) || "\\".equals(jewel1.getName()) || "-".equals(jewel1.getName()) || "+".equals(jewel1.getName()) || "|".equals(jewel1.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row + 1, column - 1));
                    result.add(this.gameGrid.getItem(row + 2, column - 2));
                    finished = true;
                }
            } else if (("D".equals(staticJewelName)) && finished == false) {
                if (staticJewelName.equals(jewel1.getName())) {
                    result.add(this.gameGrid.getItem(row, column));
                    result.add(this.gameGrid.getItem(row + 1, column - 1));
                    result.add(this.gameGrid.getItem(row + 2, column - 2));
                    finished = true;
                }
            } else if ((getName().equals(staticJewelName)) && finished == false) {
                result.add(this.gameGrid.getItem(row, column));
                result.add(this.gameGrid.getItem(row + 1, column - 1));
                result.add(this.gameGrid.getItem(row + 2, column - 2));
                finished = true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return result;
    }

    @Override
    public int getPoint() {
        return 10;
    }
}
