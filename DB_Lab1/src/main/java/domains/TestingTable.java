package domains;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestingTable {
    private long studentId;
    private long variantId;

    public TestingTable(long studentId, long variantId){
        this.studentId = studentId;
        this.variantId = variantId;
    }
}
