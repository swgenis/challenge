package code.challenge.utilities;

import code.challenge.dto.Ranking;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PrinterTest {
    
    @Test
    public void testPrint() {

        String onePointer = Printer.output(1, new Ranking("Lions", 1));
        assertNotNull(onePointer);
        assertEquals("1. Lions, 1 pt", onePointer);

        String twoPointer = Printer.output(2, new Ranking("Lions", 2));
        assertEquals("2. Lions, 2 pts", twoPointer);

        String zeroPointer = Printer.output(3, new Ranking("Lions", 0));
        assertEquals("3. Lions, 0 pts", zeroPointer);
    }
    @Test
    public void testSortComparator() {
        List<Ranking> rankings = Arrays.asList(
                new Ranking("FC Awesome", 1),
                new Ranking("Tarantulas", 6),
                new Ranking("Snakes", 1),
                new Ranking("Lions", 5),
                new Ranking("Grouches", 0)
        );

        rankings.sort(Printer.pointsComparator());

        assertEquals("Tarantulas", rankings.get(0).team());
        assertEquals("Lions", rankings.get(1).team());
        assertEquals("FC Awesome", rankings.get(2).team());
        assertEquals("Snakes", rankings.get(3).team());
        assertEquals("Grouches", rankings.get(4).team());
    }
    
}
