import src.Repositories.StudentsRepo;
import src.Repositories.TestingTableRepo;
import src.Repositories.VariantsRepo;
import src.Services.FileService;
import src.domains.Student;
import src.domains.Variant;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class FileServiceTest {
    @Test
    public void saveNLoadTest() throws IOException, ClassNotFoundException {
        FileService fileService = new FileService();
        TestingTableRepo testingTableRepo = new TestingTableRepo();
        StudentsRepo studentsRepo = new StudentsRepo();
        VariantsRepo variantsRepo = new VariantsRepo();

        studentsRepo.Post(new Student("Nikita", "Kuritsyn", "Alex"));
        studentsRepo.Post(new Student("Buba", "Biba", ""));
        studentsRepo.Post(new Student("Kurita", "Nikititsyn", "Alex"));
        studentsRepo.Post(new Student("hehe", "huhu", "bebe"));
        studentsRepo.Post(new Student("Buba", "Beba", ""));

        variantsRepo.Post(new Variant("var1"));
        variantsRepo.Post(new Variant("var7"));
        variantsRepo.Post(new Variant("var2"));
        variantsRepo.Post(new Variant("var3"));
        variantsRepo.Post(new Variant("var4"));
        variantsRepo.Post(new Variant("var5"));
        variantsRepo.Post(new Variant("var6"));

        testingTableRepo.GenerateTestingTable(studentsRepo, variantsRepo);

        String path = "C:\\Users\\DNS\\Desktop\\HW_Projects\\DB_Lab1\\src\\saves\\tests\\";

        fileService.saveObject(testingTableRepo, path + "ttTest_Save.sv");
        TestingTableRepo loadedTTRepo = (TestingTableRepo) fileService.loadObject(path + "ttTest_Save.sv");
        Assert.assertEquals(testingTableRepo, loadedTTRepo);

        fileService.saveObject(studentsRepo, path+"stdRepoTest_Save.sv");
        StudentsRepo loadedSTRepo = (StudentsRepo) fileService.loadObject(path+"stdRepoTest_Save.sv");
        Assert.assertEquals(loadedSTRepo, studentsRepo);

        fileService.saveObject(variantsRepo, path + "varRepoTest_Save.sv");
        VariantsRepo loadedVRRepo = (VariantsRepo) fileService.loadObject(path + "varRepoTest_Save.sv");
        Assert.assertEquals(loadedVRRepo, variantsRepo);
    }
}
