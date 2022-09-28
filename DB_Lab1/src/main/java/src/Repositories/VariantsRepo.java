package src.Repositories;

import src.domains.Variant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Getter
@Setter
@ToString
public class VariantsRepo implements RepoInterface<Variant>, Serializable {
    @Serial
    private static final long serialVersionUID = 899L;
    private long idSequence;
    private long elementsCount;
    private List<Variant> variantsList;

    public VariantsRepo(){
        this.variantsList = new ArrayList<>();
    }


    @Override
    public int Post(Variant element) {
        boolean exists = false;
        for (Variant var : this.variantsList) {
            exists = var.getPathToFile().equals(element.getPathToFile());
            if (exists) break;
        }
        if (!exists) {
            Variant variant = new Variant();
            variant.setId(++idSequence);
            elementsCount++;
            variant.setPathToFile(element.getPathToFile());
            this.variantsList.add(variant);
            return 0;
        }
        else return -1;
    }

    @Override
    public Variant GetById(long id) {
        try{
            Variant variant = new Variant();
            Variant foundVariant = this.variantsList.stream().filter(variant1 -> variant1.getId() == id).findFirst().get();
            variant.setId(id);
            variant.setPathToFile(foundVariant.getPathToFile());
            return variant;
        }catch (NoSuchElementException e){
            return null;
        }
    }

    @Override
    public void PatchById(long existedId, Variant editedElement) {
        Variant existedVariant = this.GetById(existedId);
        boolean exists = false;
        for (Variant var : this.variantsList) {
            exists = var.getPathToFile().equals(editedElement.getPathToFile());
            if (exists) break;
        }
        if (existedVariant != null && !exists){
            int index = this.variantsList.indexOf(existedVariant);
            existedVariant.setPathToFile(editedElement.getPathToFile());
            this.variantsList.set(index, existedVariant);
        }
    }

    @Override
    public void DeleteById(long id) {
        Variant variant = this.GetById(id);
        if (variant != null){
            elementsCount--;
            this.variantsList.remove(variant);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariantsRepo that = (VariantsRepo) o;
        return idSequence == that.idSequence && elementsCount == that.elementsCount && variantsList.equals(that.variantsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSequence, elementsCount, variantsList);
    }

    public void GenerateVariants(int count){
        for (int i = 1; i <= count; ++i){
            this.Post(new Variant("var"+(this.idSequence+1)));
        }
    }
}
