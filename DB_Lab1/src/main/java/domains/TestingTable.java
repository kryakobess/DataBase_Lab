package domains;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
public class TestingTable implements Serializable {

    private static final long serialVersionUID = 899L;
    private long studentId;
    private long variantId;

    public TestingTable(long studentId, long variantId){
        this.studentId = studentId;
        this.variantId = variantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestingTable that = (TestingTable) o;
        return studentId == that.studentId && variantId == that.variantId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, variantId);
    }

    public TestingTable() {}
}
