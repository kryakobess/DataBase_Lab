import Repositories.RepoInterface;
import Repositories.StudentsRepo;
import domains.Student;
import org.junit.Assert;
import org.junit.Test;


public class StudentRepoTest {

    @Test
    public void postStudentTest(){
        StudentsRepo studentsRepo = new StudentsRepo();
        Student student = new Student(123, "N", "K", "A");
        studentsRepo.Post(student);
        student.setId(1);
        Assert.assertEquals(student, studentsRepo.getStudentsList().get(0));
        student.setName("M");
        Assert.assertNotEquals(student, studentsRepo.getStudentsList().get(0));
        student.setName("N");
        studentsRepo.Post(student);
        Assert.assertNull(studentsRepo.GetById(2));
    }

    @Test
    public void getStudentByIdTest(){
        StudentsRepo studentsRepo = new StudentsRepo();
        studentsRepo.Post(new Student(123, "Nikita", "Kuritsyn", "Alex"));
        Student expectedStudent = new Student(1,"Nikita", "Kuritsyn", "Alex");
        Student actualStudent = studentsRepo.GetById(1);
        Assert.assertEquals(expectedStudent, actualStudent);
        actualStudent = studentsRepo.GetById(123);
        Assert.assertNull(actualStudent);
    }

    @Test
    public void patchStudentByIdTest(){
        StudentsRepo studentsRepo = new StudentsRepo();
        studentsRepo.Post(new Student(123, "Nikita", "Kuritsyn", "Alex"));
        Student expectedStudent = new Student(1234,"Kurita", "Nikititsyn", "Alex");
        studentsRepo.PatchById(1, expectedStudent);
        Student actualStudent = studentsRepo.GetById(1);
        Assert.assertNotEquals(expectedStudent, actualStudent);
        expectedStudent.setId(1);
        Assert.assertEquals(expectedStudent, actualStudent);
    }

    @Test
    public void patchToTheExistedElTest(){
        StudentsRepo studentsRepo = new StudentsRepo();
        studentsRepo.Post(new Student(123, "Nikita", "Kuritsyn", "Alex"));
        studentsRepo.Post(new Student(123, "Kurita", "Nikititsyn", "Alex"));
        Student expectedStudent = new Student(123, "Kurita", "Nikititsyn", "Alex");
        studentsRepo.PatchById(1, expectedStudent);

        Assert.assertNotEquals(studentsRepo.GetById(1), expectedStudent);
    }

    @Test
    public void deleteStudentByIdTest(){
        StudentsRepo studentsRepo = new StudentsRepo();
        Student student = new Student(123,"Nikita", "Kuritsyn", "Al");
        studentsRepo.Post(student);
        student = studentsRepo.GetById(1);
        Assert.assertNotNull(student);

        studentsRepo.DeleteById(1);
        student = studentsRepo.GetById(1);
        Assert.assertNull(student);

        studentsRepo.Post(new Student(123,"Nikita", "Kuritsyn", "Al"));
        Assert.assertNotNull(studentsRepo.GetById(2));
    }
}
