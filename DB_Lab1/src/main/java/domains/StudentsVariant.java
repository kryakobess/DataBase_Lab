package domains;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public class StudentsVariant {
    @Serial
    private static final long serialVersionUID = 899L;
    private Student student;
    private Variant variant;

    public StudentsVariant(Student student, Variant variant) {
        this.student = student;
        this.variant = variant;
    }
}
