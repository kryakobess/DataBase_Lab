package src.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import src.Repositories.DBRepo;
import src.domains.DataBase;
import src.domains.Student;

import javax.validation.Valid;

@Controller
public class DataBasesController {
    private final DBRepo dbRepo;

    public DataBasesController(DBRepo dbRepo) {
        this.dbRepo = dbRepo;
    }

    @GetMapping("/dblist")
    public String showList(Model model){
        if (dbRepo.getDataBaseList().isEmpty()) return "redirect:/createDB";

        model.addAttribute("dbList",dbRepo.getDataBaseList());
        return "DBList";
    }
    @GetMapping("/dblist/{id}")
    public String showDB(@PathVariable("id") int id,@RequestParam(required = false) boolean exists, Model model){
        DataBase db = dbRepo.GetById(id);
        if (db == null) return "redirect:/dblist";
        model.addAttribute("db", dbRepo.GetById(id));
        if (exists) model.addAttribute("ExistingWarning", "Such student already exists");
        model.addAttribute("studentsTable", db.getStudentsRepo().getStudentsList());
        model.addAttribute("variantTable", db.getVariantsRepo().getVariantsList());
        model.addAttribute("student", new Student());
        model.addAttribute("path", new String());
        return "dataBaseById";
    }
    @PostMapping("/dblist/{id}/addOne")
    public String addOneStudent(@PathVariable("id") int id, @ModelAttribute("student") @Valid Student student, Errors errors){
        if (errors.hasErrors()){
            return "redirect:/dblist/"+id;
        }
        if (dbRepo.GetById(id).getStudentsRepo().Post(student) == -1){
            return "redirect:/dblist/"+id+"?exists=true";
        }
        return "redirect:/dblist/"+id;
    }
}
