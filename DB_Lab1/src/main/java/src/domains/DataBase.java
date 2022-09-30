package src.domains;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import src.Repositories.StudentsRepo;
import src.Repositories.TestingTableRepo;
import src.Repositories.VariantsRepo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class DataBase implements Serializable{
    @Serial
    private static final long serialVersionUID = 899L;
    private int id;
    @NotBlank(message = "You have to write name!")
    @Size(max = 15, message = "Come up with shorter name(maximum 15 symbols)")
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
