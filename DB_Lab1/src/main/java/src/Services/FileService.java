package src.Services;

import org.springframework.stereotype.Service;
import src.Repositories.StudentsRepo;
import src.Repositories.TestingTableRepo;
import src.domains.Student;
import src.domains.TestingTable;

import java.io.*;
import java.util.Scanner;
@Service
public class FileService {
    public int loadStudentsFromFile(String filePath, StudentsRepo studentsRepo){
        try {
            File file = new File(filePath);
            Scanner in = new Scanner(file);
            while (in.hasNextLine()){
                String[] fullName = in.nextLine().split(" ");
                if (fullName.length == 3) studentsRepo.Post(new Student(fullName[1], fullName[0], fullName[2]));
            }
            return 0;
        } catch (IOException e){
            return -1;
        }
    }
    public int loadTestingTableFromFile(String filePath, TestingTableRepo testingTableRepo){
        try {
            File file = new File(filePath);
            Scanner in = new Scanner(file);
            while (in.hasNextLine()){
                String[] ids = in.nextLine().split(" ");
                if (ids.length == 2) {
                    boolean exists = false;
                    int i = 0;
                    for (TestingTable testingTable: testingTableRepo.getTestingTableList()){
                        if (testingTable.getStudentId() == Integer.parseInt(ids[0])){
                            exists = true;
                            testingTableRepo.getTestingTableList().set(i, new TestingTable(Integer.parseInt(ids[0]), Integer.parseInt(ids[1])));
                        }
                        i++;
                    }
                    if (!exists) testingTableRepo.getTestingTableList().add(new TestingTable(Integer.parseInt(ids[0]), Integer.parseInt(ids[1])));
                }
            }
            return 0;
        } catch (IOException e){
            return -1;
        }
    }
    public void saveObject(Object object, String path) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        objectOutputStream.writeObject(object);

        objectOutputStream.close();
    }

    public Object loadObject(String path) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        return objectInputStream.readObject();
    }

}
