package app.core;
import app.core.interfaces.Runnable;
import app.factories.RecordFactory;
import app.io.FileIO;
import app.io.Writer;
import app.model.Record;
import app.model.Team;
import app.services.EmployeeService;
import java.util.List;
import java.util.stream.Collectors;

import static app.allConstants.ApplicationWithConstants.*;

public class Engine implements Runnable {

    private FileIO fileIO;
    private Writer writer;
    private EmployeeService emplService;

    public Engine(FileIO fileIO, Writer writer, EmployeeService emplService) {
        this.fileIO = fileIO;
        this.writer = writer;
        this.emplService = emplService;
    }
    private void printResult(List<Team> teams) {
        if (teams.size() != EMPTY_COLLECTION_SIZE) {
            teams.sort((team1, team2) ->
                    (int) (team2.getTotalDuration() - team1.getTotalDuration()));
            Team bestTeam = teams.get(ZERO);

            this.writer.write(
                    String.format(PATTERN_FOR_EMPLOYEES,
                            bestTeam.getFirstEmployeeId(),
                            bestTeam.getSecondEmployeeId(),
                            bestTeam.getTotalDuration()));
        } else {
            this.writer.write(NO_MESSAGE);
        }
    }

    @Override
    public void run() {
        List<Record> records = this.fileIO.read(FILE_PATH)
                .stream()
                .map(RecordFactory::execute)
                .collect(Collectors.toList());

        this.emplService.addRecordsOfEmployees(records);
        List<Team> teams = this.emplService.findTeamsWithOverlap();
        printResult(teams);
    }

}