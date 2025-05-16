package code.challenge.entities;

public class TeamResult {

    public String team;

    public Integer goals;

    public TeamResult(String team, Integer goals) {
        this.team = team;
        this.goals = goals;
    }

    public String getTeam() {
        return team;
    }

    public Integer getGoals() {
        return goals;
    }

    @Override
    public String toString() {
        return "TeamResult{" +
                "team='" + team + '\'' +
                ", goals=" + goals +
                '}';
    }

}

