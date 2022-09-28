package src.domains;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import src.Repositories.StudentsRepo;
import src.Repositories.TestingTableRepo;
import src.Repositories.VariantsRepo;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class DataBase {
    private int id;
    @NotBlank(message = "You have to write name!")
    private String name;
    private StudentsRepo studentsRepo;
    private VariantsRepo variantsRepo;
    private TestingTableRepo testingTableRepo;

    private List<StudentsVariant> studentsVariantList;

    public DataBase(){
        this.name = null;
        this.studentsRepo = new StudentsRepo();
        this.variantsRepo = new VariantsRepo();
        this.testingTableRepo = new TestingTableRepo();
        this.studentsVariantList = new ArrayList<>();
    }
}
