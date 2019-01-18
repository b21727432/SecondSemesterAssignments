public class LeaderboardItem implements Comparable<LeaderboardItem> {
    private String name;
    private int point;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public int compareTo(LeaderboardItem o) {
        if (this.name.compareTo(o.getName()) > 0)
            return 1;
        if (this.name.compareTo(o.getName()) < 0)
            return -1;
        return 0;
    }
}
