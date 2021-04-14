package dev.nhi;

import dev.nhi.csv.WriteCsvFileExample;
import dev.nhi.model.Todo;
import dev.nhi.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoCsvApplication implements CommandLineRunner {


    private final TodoRepository repository;


    public static void main(String[] args) {
        SpringApplication.run(DemoCsvApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        List<Todo> todoList = repository.findAll();
        List<String[]> dataLines = new ArrayList<>();
        for (Todo todo: todoList) {
            dataLines.add(todo.toArray());
        }
        File csvOutputFile = new File("nhiph.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(WriteCsvFileExample::convertToCSV)
                    .forEach(pw::println);
        }
    }
}
