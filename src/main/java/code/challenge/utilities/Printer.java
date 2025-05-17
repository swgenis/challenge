package code.challenge.utilities;

import code.challenge.dto.Ranking;

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

    /**
     * Prints a sorted list of rankings to the standard output.
     *
     * @param rankings the list of {@code Ranking} objects to be sorted and printed
     */
    public static void print(List<Ranking> rankings) {

        // Sort by points
        rankings.sort(pointsComparator());

        // Print sorted rankings by index
        IntStream.range(0, rankings.size())
            .forEach(idx -> System.out.println(output(idx + 1, rankings.get(idx))));
    }

    /**
     * Formats a ranking output string for printing.
     *
     * @param idx the index of the ranking in the sorted list
     * @param ranking the {@code Ranking} object to be formatted
     * @return a formatted string representing the ranking
     */
    public static String output(int idx, Ranking ranking) {
        String post = ranking.points() == 1 ? "pt" : "pts";
        return format("%d. %s, %d %s", idx, ranking.team(), ranking.points(), post);
    }

    /**
     * Provides a comparator for sorting rankings by points in descending order.
     * If two rankings have the same number of points, they are sorted
     * alphabetically by team name.
     *
     * @return a comparator for sorting instances of {@code Ranking} by points
     *         in descending order and, in the case of ties, by team names in ascending order
     */
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
