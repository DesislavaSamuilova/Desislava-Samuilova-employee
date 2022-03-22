package app.services;

import app.factories.TeamFactory;
import app.model.Record;
import app.model.Team;
import app.repository.EmployeeRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static app.allConstants.ApplicationWithConstants.*;

public class EmployeeServiceImpl implements EmployeeService {

    private  EmployeeRepository empRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.empRepository = employeeRepository;
    }
    @Override
    public void addRecordsOfEmployees(List<Record> records) {
        this.empRepository.saveAll(records);
    }

    @Override
    public List<Team> findTeamsWithOverlap() {
        List<Record> allRecords = this.empRepository.getAllRecords();

        List<Team> teams = new ArrayList<>();
        for (int i = ZERO; i < allRecords.size() - ONE; i++) {
            for (int j = i + ONE; j < allRecords.size(); j++) {
                Record firstEmpl = allRecords.get(i);
                Record secondEmpl = allRecords.get(j);

                if (firstEmpl.getProjectId() == secondEmpl.getProjectId()
                        && hasOverlap(firstEmpl, secondEmpl)) {
                    long overlapDays = calculateOverlap(firstEmpl, secondEmpl);

                    if (overlapDays > OVERLAP_ZERO_DAYS) {
                        updateCollection(teams, firstEmpl, secondEmpl, overlapDays);
                    }
                }
            }
        }
        return teams;
    }
    private boolean isTeamPresent(Team team, long firstEmplId, long secondEmplId) {
        return ( team.getFirstEmployeeId() == firstEmplId
                && team.getSecondEmployeeId() == secondEmplId )
                || ( team.getFirstEmployeeId() == secondEmplId
                && team.getSecondEmployeeId() == firstEmplId );
    }

    private long calculateOverlap(Record firstEmpl, Record secondEmpl) {
        LocalDate periodStartDate =
                firstEmpl.getDateFrom().isBefore(secondEmpl.getDateFrom()) ?
                        secondEmpl.getDateFrom() : firstEmpl.getDateFrom();

        LocalDate periodEndDate =
                firstEmpl.getDateTo().isBefore(secondEmpl.getDateTo()) ?
                        firstEmpl.getDateTo() : secondEmpl.getDateTo();

        return Math.abs(ChronoUnit.DAYS.between(periodStartDate, periodEndDate));
    }
    private boolean hasOverlap(Record firstEmpl, Record secondEmpl) {
        return (firstEmpl.getDateFrom().isBefore(secondEmpl.getDateTo())
                || firstEmpl.getDateFrom().isEqual(secondEmpl.getDateTo()))
                && (firstEmpl.getDateTo().isAfter(secondEmpl.getDateFrom())
                || firstEmpl.getDateTo().isEqual(secondEmpl.getDateFrom()));
    }
    private void updateCollection(List<Team> teams, Record firstEmpl, Record secondEmpl, long overlapDays) {
        AtomicBoolean isPresent = new AtomicBoolean(false);
        teams.forEach(team -> {
            if (isTeamPresent(team, firstEmpl.getEmployeeId(), secondEmpl.getEmployeeId())) {
                team.addOverlapDuration(overlapDays);
                isPresent.set(true);
            }
        });
        if (!isPresent.get()) {
            Team newTeam = TeamFactory.execute(
                    firstEmpl.getEmployeeId(),
                    secondEmpl.getEmployeeId(),
                    overlapDays);
            teams.add(newTeam);
        }
    }
}