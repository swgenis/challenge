package code.challenge.entities;

/**
 * Represents a game result.
 * @param homeTeamResult
 * @param awayTeamResult
 */
public record GameResult(TeamResult homeTeamResult, TeamResult awayTeamResult) {

}
