package app.model;

public class Team {
    private long firstEmployeeId;
    private long secondEmployeeId;
    private long totalDurability;

    public Team(long firstEmployeeId, long secondEmployeeId, long totalDurability) {
        this.setFirstEmployeeId(firstEmployeeId);
        this.setSecondEmployeeId(secondEmployeeId);
        this.setTotalDuration(totalDurability);
    }

    public long getFirstEmployeeId() {
        return this.firstEmployeeId;
    }

    private void setFirstEmployeeId(long firstEmployeeId) {
        this.firstEmployeeId = firstEmployeeId;
    }

    public long getSecondEmployeeId() {
        return this.secondEmployeeId;
    }

    private void setSecondEmployeeId(long secondEmployeeId) {
        this.secondEmployeeId = secondEmployeeId;
    }

    public long getTotalDuration() {
        return this.totalDurability;
    }

    private void setTotalDuration(long totalDuration) {
        this.totalDurability = totalDuration;
    }

    public void addOverlapDuration(long overlap) {
        this.totalDurability += overlap;
    }
}
