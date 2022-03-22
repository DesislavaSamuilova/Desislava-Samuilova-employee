package app.factories;

import app.model.Team;

public final class TeamFactory {

    public TeamFactory() {
    }

    public static Team execute(long firstEmployeeId, long secondEmployeeId, long overlapDurability) {
        return new Team(
                firstEmployeeId,
                secondEmployeeId,
                overlapDurability);
    }
}