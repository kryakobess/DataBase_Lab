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
public class Variant implements Serializable {
    @Serial
    private static final long serialVersionUID = 899L;
    private long id;
    @NotBlank(message = "Variant should contain its name")
    private String pathToFile;

    public Variant() {
    }

    public Variant(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public Variant(long id, String pathToFile) {
        this.id = id;
        this.pathToFile = pathToFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variant variant = (Variant) o;
        return id == variant.id && pathToFile.equals(variant.pathToFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pathToFile);
    }
}
