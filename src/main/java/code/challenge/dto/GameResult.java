package code.challenge.dto;

/**
 * Represents a game result.
 * @param homeTeamResult
 * @param awayTeamResult
 */
public record GameResult(TeamResult homeTeamResult, TeamResult awayTeamResult) {

}
