package app.services;

import app.model.Record;
import app.model.Team;

import java.util.List;

public interface EmployeeService {

    void addRecordsOfEmployees(List<Record> records);

    List<Team> findTeamsWithOverlap();
}