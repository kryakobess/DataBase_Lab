import Repositories.StudentsRepo;
import Repositories.TestingTableRepo;
import Repositories.VariantsRepo;
import domains.Student;
import domains.Variant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        StudentsRepo studentsRepo = new StudentsRepo();
        VariantsRepo variantsRepo = new VariantsRepo();
        TestingTableRepo testingTableRepo = new TestingTableRepo();

        Path file = Paths.get("./names.txt");
        try {
            InputStream in = Files.newInputStream(file, );
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            int count = 0;
            while ((line = reader.readLine()) != null){
                count++;
                String[] FIO = line.split(" ");
                studentsRepo.Post(new Student(FIO[1], FIO[0], FIO[2]));
                variantsRepo.Post(new Variant("var"+count));
            }
            testingTableRepo.GenerateTestingTable(studentsRepo, variantsRepo);
            Map<Student, Variant> teachersTable = testingTableRepo.ParseTestingTable(studentsRepo, variantsRepo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
