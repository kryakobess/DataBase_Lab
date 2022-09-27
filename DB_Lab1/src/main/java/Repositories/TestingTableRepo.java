package Repositories;

import domains.StudentsVariant;
import domains.TestingTable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;


@Getter
@Setter
@ToString
public class TestingTableRepo implements Serializable {

    private static final long serialVersionUID = 899L;
    private List<TestingTable> testingTableList;

    public TestingTableRepo() {
        this.testingTableList = new ArrayList<>();
    }

    public void GenerateTestingTable(StudentsRepo studentsRepo, VariantsRepo variantsRepo){
        for (int i = 0; i < studentsRepo.getElementsCount(); ++i){
            TestingTable testingTable = new TestingTable(studentsRepo.getStudentsList().get(i).getId(),
                    variantsRepo.getVariantsList().get((int)(Math.random() * (variantsRepo.getElementsCount()))).getId());
            this.testingTableList.add(testingTable);
        }
    }

    public List<StudentsVariant> ParseTestingTable(StudentsRepo studentsRepo, VariantsRepo variantsRepo){
       List <StudentsVariant> joinList = new ArrayList<>();
        for (TestingTable testingTable : this.getTestingTableList()){
            if (testingTable == null) return null;
            joinList.add(new StudentsVariant(studentsRepo.GetById(testingTable.getStudentId()),
                                  variantsRepo.GetById(testingTable.getVariantId())));
        }
        return joinList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestingTableRepo that = (TestingTableRepo) o;
        return testingTableList.equals(that.testingTableList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testingTableList);
    }
}
