package app.repository;

import app.model.Record;

import java.util.List;

public interface EmployeeRepository {

    void save(Record record);

    void saveAll(List<Record> records);

    List<Record> getAllRecords();
}