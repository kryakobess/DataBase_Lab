import Repositories.StudentsRepo;
import Repositories.TestingTableRepo;
import Repositories.VariantsRepo;
import Services.FileService;
import domains.Student;
import domains.Variant;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Application {
    public static void main(String[] args)  {
        FileService fileService = new FileService();
        StudentsRepo studentsRepo = new StudentsRepo();
        VariantsRepo variantsRepo = new VariantsRepo();
        TestingTableRepo testingTableRepo = new TestingTableRepo();

        fileService.loadStudentsFromFile("C:\\Users\\DNS\\Desktop\\HW_Projects\\DB_Lab1\\src\\main\\resources\\names.txt", studentsRepo);
        variantsRepo.GenerateVariants((int)studentsRepo.getElementsCount());

        testingTableRepo.GenerateTestingTable(studentsRepo, variantsRepo);
        Map<Student, Variant> stuentsVariants = testingTableRepo.ParseTestingTable(studentsRepo, variantsRepo);
        
        System.out.println(stuentsVariants.toString());
    }
}
