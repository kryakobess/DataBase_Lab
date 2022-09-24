package Services;

import Repositories.StudentsRepo;
import Repositories.TestingTableRepo;
import Repositories.VariantsRepo;
import domains.Student;
import domains.Variant;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileService {
    public void loadStudentsFromFile(String filePath, StudentsRepo studentsRepo){
        try {
            File file = new File(filePath);
            Scanner in = new Scanner(file);
            while (in.hasNextLine()){
                String[] fullName = in.nextLine().split(" ");
                studentsRepo.Post(new Student(fullName[1], fullName[0], fullName[2]));
            }
        } catch (IOException e){
            System.out.println("File don't exist");
        }
    }

}
