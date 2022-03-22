package app;

import app.core.Engine;
import app.io.ConsoleOutputWriter;
import app.io.FileIO;
import app.io.FileIOImpl;
import app.io.Writer;
import app.repository.EmployeeRepository;
import app.repository.EmployeeRepositoryImpl;
import app.services.EmployeeService;
import app.services.EmployeeServiceImpl;

public class LongestTeam {
    public static void main(String[] args) {

        FileIO fileIO = new FileIOImpl();
        Writer writer = new ConsoleOutputWriter();
        EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
        EmployeeService emplService = new EmployeeServiceImpl(employeeRepository);

        Engine engine = new Engine(fileIO, writer, emplService);
        engine.run();
    }
}
