package app.repository;

import app.model.Record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private List<Record> databaseEmployees;

    public EmployeeRepositoryImpl() {
        this.databaseEmployees = new ArrayList<>();
    }
    @Override
    public void saveAll(List<Record> records) {
        this.databaseEmployees.addAll(records);
    }
    @Override
    public void save(Record record) {
        this.databaseEmployees.add(record);
    }
    @Override
    public List<Record> getAllRecords() {
        return Collections.unmodifiableList(this.databaseEmployees);
    }
}