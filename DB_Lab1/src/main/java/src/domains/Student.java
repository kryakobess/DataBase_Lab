package src.domains;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 899L;
    private long id;
    @NotBlank(message = "You better remember their name")
    private String name;
    @NotBlank(message = "What are their surname boiii?")
    private String surname;
    private String patronymic;

    public Student() {
        this.name = null;
        this.surname = null;
        this.patronymic = null;
    }

    public Student(String name, String surname, String patronymic) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }
    public Student(long id, String name, String surname, String patronymic) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && name.equals(student.name) && surname.equals(student.surname) && patronymic.equals(student.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, patronymic);
    }

}
