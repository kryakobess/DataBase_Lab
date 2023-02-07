package src.Repositories;

import src.domains.Student;
import src.domains.StudentsVariant;
import src.domains.TestingTable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import src.domains.Variant;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;


@Getter
@Setter
@ToString
public class TestingTableRepo implements Serializable {
    @Serial
    private static final long serialVersionUID = 899L;
    private List<TestingTable> testingTableList;

    public TestingTableRepo() {
        this.testingTableList = new ArrayList<>();
    }

    public void GenerateTestingTable(StudentsRepo studentsRepo, VariantsRepo variantsRepo){
        if (studentsRepo.getStudentsList().isEmpty() || variantsRepo.getVariantsList().isEmpty())
            return;
        for (int i = 0; i < studentsRepo.getElementsCount(); ++i){
            TestingTable testingTable = new TestingTable(studentsRepo.getStudentsList().get(i).getId(),
                    variantsRepo.getVariantsList().get((int)(Math.random() * (variantsRepo.getElementsCount()))).getId());
            if (testingTableList.size() > i){
                this.testingTableList.set(i,testingTable);
            }
            else this.testingTableList.add(testingTable);
        }
    }

    public List<StudentsVariant> ParseTestingTable(StudentsRepo studentsRepo, VariantsRepo variantsRepo){
       List <StudentsVariant> joinList = new ArrayList<>();
        for (TestingTable testingTable : this.getTestingTableList()){
            if (testingTable == null) return null;
            Student foundStudent = studentsRepo.GetById(testingTable.getStudentId());
            Variant foundVariant = variantsRepo.GetById(testingTable.getVariantId());
            if (foundStudent != null && foundVariant != null){
                boolean exists = false;
                for (int i = 0; i < joinList.size(); ++i){
                    if (foundStudent.getName().equals(joinList.get(i).getStudent().getName()) &&
                            foundStudent.getSurname().equals(joinList.get(i).getStudent().getSurname()) &&
                            foundStudent.getPatronymic().equals(joinList.get(i).getStudent().getPatronymic())){
                        exists = true;
                        joinList.set(i, new StudentsVariant(foundStudent, foundVariant));
                    }
                }
                if (!exists) joinList.add(new StudentsVariant(foundStudent, foundVariant));
            }
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
