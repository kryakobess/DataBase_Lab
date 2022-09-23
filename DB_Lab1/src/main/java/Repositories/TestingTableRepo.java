package Repositories;

import domains.Student;
import domains.TestingTable;
import domains.Variant;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class TestingTableRepo {
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

    public Map<Student, Variant> ParseTestingTable(StudentsRepo studentsRepo, VariantsRepo variantsRepo){
        Map<Student, Variant> studentVariantMap = new HashMap<>();
        for (TestingTable testingTable : this.getTestingTableList()){
            if (testingTable == null) return null;
            studentVariantMap.put(studentsRepo.GetById(testingTable.getStudentId()),
                                  variantsRepo.GetById(testingTable.getVariantId()));
        }
        return studentVariantMap;
    }
}
