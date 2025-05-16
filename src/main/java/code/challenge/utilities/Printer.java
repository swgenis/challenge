package code.challenge.utilities;

import code.challenge.entities.Ranking;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.String.format;

/**
 * This class prints a sorted list of rankings.
 * Rankings are sorted based on the points in descending order. In the case of a tie on points,
 * the teams are sorted alphabetically by their names.
 */
public class Printer {

    public static void print(List<Ranking> rankings) {

        // Sort by points
        rankings.sort(pointsComparator());

        // Print sorted rankings by index
        IntStream.range(0, rankings.size())
            .forEach(idx -> System.out.println(output(idx + 1, rankings.get(idx))));
    }

    public static String output(int idx, Ranking ranking) {
        String post = ranking.points() == 1 ? "pt" : "pts";
        return format("%d. %s, %d %s", idx, ranking.team(), ranking.points(), post);
    }

    public static Comparator<Ranking> pointsComparator() {
        return (o1, o2) -> {
            if (o1.points() > o2.points()) {
                return -1;
            } else if (o1.points() < o2.points()) {
                return 1;
            } else {
                // Else sort alphabetically
                return o1.team().compareTo(o2.team());
            }
        };
    }

}
