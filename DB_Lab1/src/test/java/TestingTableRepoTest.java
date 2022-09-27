import src.Repositories.StudentsRepo;
import src.Repositories.TestingTableRepo;
import src.Repositories.VariantsRepo;
import src.domains.Student;
import src.domains.StudentsVariant;
import src.domains.Variant;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class TestingTableRepoTest {
    @Test
    public void TestingTableGenerationTest(){
        TestingTableRepo testingTableRepo = new TestingTableRepo();
        StudentsRepo studentsRepo = new StudentsRepo();
        VariantsRepo variantsRepo = new VariantsRepo();
        studentsRepo.Post(new Student("Nikita", "Kuritsyn", "Alex"));
        studentsRepo.Post(new Student("Buba", "Biba", ""));
        studentsRepo.Post(new Student("Kurita", "Nikititsyn", "Alex"));
        studentsRepo.Post(new Student("hehe", "huhu", "bebe"));
        studentsRepo.DeleteById(2);
        studentsRepo.Post(new Student("Buba", "Beba", ""));

        variantsRepo.Post(new Variant("var1"));
        variantsRepo.Post(new Variant("var1"));
        variantsRepo.Post(new Variant("var2"));
        variantsRepo.Post(new Variant("var3"));
        variantsRepo.Post(new Variant("var4"));
        variantsRepo.Post(new Variant("var5"));
        variantsRepo.Post(new Variant("var6"));

        testingTableRepo.GenerateTestingTable(studentsRepo, variantsRepo);
        for (int i = 1; i <= studentsRepo.getElementsCount(); ++i){
            try {
                Assert.assertTrue(testingTableRepo.getTestingTableList().get(i-1).getStudentId() +
                    testingTableRepo.getTestingTableList().get(i-1).getVariantId() > 0);
            }catch (IndexOutOfBoundsException exception){
                Assert.assertTrue(false);
            }
        }
    }
    @Test
    public void parsingTest(){
        TestingTableRepo testingTableRepo = new TestingTableRepo();
        StudentsRepo studentsRepo = new StudentsRepo();
        VariantsRepo variantsRepo = new VariantsRepo();
        studentsRepo.Post(new Student("Nikita", "Kuritsyn", "Alex"));
        studentsRepo.Post(new Student("Buba", "Biba", ""));
        studentsRepo.Post(new Student("Kurita", "Nikititsyn", "Alex"));
        studentsRepo.Post(new Student("hehe", "huhu", "bebe"));
        studentsRepo.DeleteById(2);
        studentsRepo.Post(new Student("Buba", "Beba", ""));

        variantsRepo.Post(new Variant("var1"));
        variantsRepo.Post(new Variant("var7"));
        variantsRepo.Post(new Variant("var2"));
        variantsRepo.Post(new Variant("var3"));
        variantsRepo.Post(new Variant("var4"));
        variantsRepo.Post(new Variant("var5"));
        variantsRepo.Post(new Variant("var6"));

        testingTableRepo.GenerateTestingTable(studentsRepo, variantsRepo);
        List<StudentsVariant> studentVariant = testingTableRepo.ParseTestingTable(studentsRepo, variantsRepo);

        for (StudentsVariant sv : studentVariant){
            System.out.println(sv.getStudent() + " - " + sv.getVariant());
            Assert.assertNotNull(sv.getStudent());
            Assert.assertNotNull(sv.getVariant());
        }
    }
}
