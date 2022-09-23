package domains;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Variant {
    private long id;
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
