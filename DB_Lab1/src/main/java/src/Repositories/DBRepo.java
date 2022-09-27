package src.Repositories;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import src.domains.DataBase;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class DBRepo implements RepoInterface<DataBase>{
    private int idSequence;
    private int elemCount;
    private List<DataBase> dataBaseList;

    public DBRepo() {
        this.dataBaseList = new ArrayList<>();
    }

    @Override
    public int Post(DataBase element) {
        boolean exists = false;
        for (DataBase db:this.dataBaseList) {
            exists = db.getName().equals(element.getName());
            if (exists) break;
        }
        if (!exists){
            DataBase dataBase = new DataBase();
            dataBase.setName(element.getName());
            dataBase.setStudentsRepo(element.getStudentsRepo());
            dataBase.setVariantsRepo(element.getVariantsRepo());
            dataBase.setTestingTableRepo(element.getTestingTableRepo());
            elemCount++;
            dataBase.setId(++idSequence);
            dataBaseList.add(dataBase);
            return 0;
        }
        else return -1;
    }

    @Override
    public DataBase GetById(long id) {
        return dataBaseList.get((int)id-1);
    }

    public DataBase GetByName(String name){
        for (DataBase db : this.dataBaseList){
            if (name.equals(db.getName())) return db;
        }
        return null;
    }
    @Override
    public void PatchById(long existedId, DataBase editedElement) {

    }

    @Override
    public void DeleteById(long id) {
        elemCount--;
        this.dataBaseList.remove((int)id);
    }
}
