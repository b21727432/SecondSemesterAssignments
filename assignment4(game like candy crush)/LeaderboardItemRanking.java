import java.util.Comparator;

public class LeaderboardItemRanking implements Comparator<LeaderboardItem> {
    @Override
    public int compare(LeaderboardItem o1, LeaderboardItem o2) {
        // Compare by point
        if (o1.getPoint() > o2.getPoint())
            return -1;
        if (o1.getPoint() < o2.getPoint())
            return 1;
        return 0;
    }
}
