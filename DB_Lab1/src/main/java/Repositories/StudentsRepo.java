package Repositories;

import domains.Student;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Getter
@Setter
@ToString
public class StudentsRepo implements RepoInterface<Student>, Serializable {

    private static final long serialVersionUID = 899L;
    private long idSequence;
    private long elementsCount;
    private List<Student>  studentsList;

    public StudentsRepo(){
        this.studentsList = new ArrayList<>();
    }

    @Override
    public void Post(Student element) {
        boolean exists = false;
        for (Student st : this.studentsList) {
            exists = st.getName().equals(element.getName()) &&
                     st.getSurname().equals(element.getSurname()) &&
                     st.getPatronymic().equals(element.getPatronymic());
            if (exists) break;
        }
        if (!exists) {
            Student student = new Student();
            student.setId(++idSequence);
            elementsCount++;
            student.setName(element.getName());
            student.setSurname(element.getSurname());
            student.setPatronymic(element.getPatronymic());
            this.studentsList.add(student);
        }
        else System.out.println("Element with such name already exists!");
    }

    @Override
    public Student GetById(long id) {
        try {
            Student newStudent = new Student();
            Student foundStudent = this.studentsList.stream().filter(student -> student.getId() == id).findFirst().get();
            newStudent.setId(foundStudent.getId());
            newStudent.setName(foundStudent.getName());
            newStudent.setSurname(foundStudent.getSurname());
            newStudent.setPatronymic(foundStudent.getPatronymic());
            return newStudent;
        }catch (NoSuchElementException e){
            return null;
        }
    }

    @Override
    public void PatchById(long existedId, Student editedElement) {
        Student existedStudent = GetById(existedId);
        boolean exists = false;
        for (Student st : this.studentsList) {
            exists = st.getName().equals(editedElement.getName()) &&
                    st.getSurname().equals(editedElement.getSurname()) &&
                    st.getPatronymic().equals(editedElement.getPatronymic());
            if (exists) break;
        }
        if (existedStudent != null && !exists){
            int index = this.studentsList.indexOf(existedStudent);
            existedStudent.setName(editedElement.getName());
            existedStudent.setSurname(editedElement.getSurname());
            existedStudent.setPatronymic(editedElement.getPatronymic());
            this.studentsList.set(index, existedStudent);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentsRepo that = (StudentsRepo) o;
        return idSequence == that.idSequence && elementsCount == that.elementsCount && studentsList.equals(that.studentsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSequence, elementsCount, studentsList);
    }

    @Override
    public void DeleteById(long id) {
        Student student = this.GetById(id);
        if (student != null){
            elementsCount--;
            this.studentsList.remove(student);
        }
    }
}
