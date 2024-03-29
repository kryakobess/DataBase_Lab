package src.domains;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class StudentsVariant implements Serializable {
    @Serial
    private static final long serialVersionUID = 899L;
    private Student student;
    private Variant variant;

    public StudentsVariant(Student student, Variant variant) {
        this.student = student;
        this.variant = variant;
    }
}
