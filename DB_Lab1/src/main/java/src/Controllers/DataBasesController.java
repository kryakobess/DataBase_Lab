package src.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import src.Repositories.DBRepo;
import src.Services.FileService;
import src.domains.DataBase;
import src.domains.Student;
import src.domains.Variant;

import java.io.IOException;

@Controller
public class DataBasesController {
    private final DBRepo dbRepo;
    private final FileService fileService;

    public DataBasesController(DBRepo dbRepo, FileService fileService) {
        this.dbRepo = dbRepo;
        this.fileService = fileService;
    }

    @GetMapping("/dblist")
    public String showList(Model model){
        if (dbRepo.getDataBaseList().isEmpty()) return "redirect:/createDB";

        model.addAttribute("dbList",dbRepo.getDataBaseList());
        return "DBList";
    }
    @GetMapping("/dblist/{id}")
    public String showDB(@PathVariable("id") int id,@RequestParam(required = false) boolean exists,
                         @RequestParam(required = false) boolean noFile, Model model){
        DataBase db = dbRepo.GetById(id);
        if (db == null) return "redirect:/dblist";
        model.addAttribute("db", dbRepo.GetById(id));
        if (exists) model.addAttribute("ExistingWarning", "Such student already exists");
        if (noFile) model.addAttribute("NoFileWarning", "Can't find such file");
        model.addAttribute("studentsTable", db.getStudentsRepo().getStudentsList());
        model.addAttribute("variantTable", db.getVariantsRepo().getVariantsList());
        model.addAttribute("student", new Student());
        model.addAttribute("variant", new Variant());
        return "dataBaseById";
    }
    @PostMapping("/dblist/{id}/saveDB")
    public String saveDB(@PathVariable("id") int id, String path) throws IOException {
        if (path.isEmpty()) return "redirect:/dblist/"+id;
        fileService.saveObject(dbRepo.GetById(id), path);
        return "redirect:/dblist/"+id;
    }
    @PostMapping("/dblist/{id}/loadDB")
    public String loadDB(@PathVariable("id") int id, String path) throws IOException, ClassNotFoundException {
        if (path.isEmpty()) return "redirect:/dblist/"+id;
        dbRepo.PatchById(id,(DataBase) fileService.loadObject(path));
        return "redirect:/dblist/"+id;
    }
}
