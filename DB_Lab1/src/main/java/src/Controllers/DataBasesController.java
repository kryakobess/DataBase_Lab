package src.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import src.Repositories.DBRepo;
import src.Services.FileService;
import src.domains.DataBase;
import src.domains.Student;
import src.domains.Variant;

import javax.validation.Valid;
import java.io.File;
import java.nio.file.Paths;

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
    @PostMapping("/dblist/{id}/addStudFromFile")
    public String addStudentsFromFile(@PathVariable("id") int id, String path){
        if (path.isEmpty()) return "redirect:/dblist/"+id;
        if (fileService.loadStudentsFromFile(path, dbRepo.GetById(id).getStudentsRepo()) == -1)
            return "redirect:/dblist/"+id+"?noFile=true";
        return "redirect:/dblist/"+id;
    }
    @PostMapping("/dblist/{id}/patchStudent")
    public String patchStudent(@PathVariable("id") int id, @ModelAttribute("student") @Valid Student student, Errors errors){
        if (errors.hasErrors()){
            return "redirect:/dblist/"+id;
        }
        dbRepo.GetById(id).getStudentsRepo().PatchById(student.getId(), student);
        return "redirect:/dblist/"+id;
    }
    @PostMapping("/dblist/{id}/deleteStudent")
    public String deleteStudent(@PathVariable("id") int id, String studentID){
        if (studentID.isEmpty()) return "redirect:/dblist/"+id;
        dbRepo.GetById(id).getStudentsRepo().DeleteById(Integer.parseInt(studentID));
        return "redirect:/dblist/"+id;
    }
    @PostMapping("/dblist/{id}/addVariant")
    public String addVariant(@PathVariable("id") int id, @ModelAttribute("variant") @Valid Variant variant, Errors errors){
        if (errors.hasErrors()){
            return "redirect:/dblist/"+id;
        }
        dbRepo.GetById(id).getVariantsRepo().Post(variant);
        return "redirect:/dblist/"+id;
    }
    @PostMapping("/dblist/{id}/generateVar")
    public String generateVariants(@PathVariable("id") int id, String varCount){
        if (varCount.isEmpty()) return "redirect:/dblist/"+id;
        dbRepo.GetById(id).getVariantsRepo().GenerateVariants(Integer.parseInt(varCount));
        return "redirect:/dblist/"+id;
    }
    @PostMapping("/dblist/{id}/patchVariant")
    public String patchVariant(@PathVariable("id") int id, @ModelAttribute("variant") @Valid Variant variant, Errors errors){
        if (errors.hasErrors()){
            return "redirect:/dblist/"+id;
        }
        dbRepo.GetById(id).getVariantsRepo().PatchById(variant.getId(), variant);
        return "redirect:/dblist/"+id;
    }
    @PostMapping("/dblist/{id}/deleteVariant")
    public String deleteVariant(@PathVariable("id") int id, String variantID){
        if (variantID.isEmpty()) return "redirect:/dblist/"+id;
        dbRepo.GetById(id).getVariantsRepo().DeleteById(Integer.parseInt(variantID));
        return "redirect:/dblist/"+id;
    }
    @PostMapping("/dblist/{id}/generateTT")
    public String generateTT(@PathVariable("id") int id){
        dbRepo.GetById(id).getTestingTableRepo().GenerateTestingTable(
                dbRepo.GetById(id).getStudentsRepo(), dbRepo.GetById(id).getVariantsRepo());
        return "redirect:/dblist/"+id;
    }

    @PostMapping("/dblist/{id}/addTTFromFile")
    public String addTTFromFile(@PathVariable("id") int id, String path){
        if (path.isEmpty()) return "redirect:/dblist/"+id;
        if (fileService.loadTestingTableFromFile(path, dbRepo.GetById(id).getTestingTableRepo()) == -1)
            return "redirect:/dblist/"+id+"?noFile=true";
        return "redirect:/dblist/"+id;
    }
    @PostMapping("/dblist/{id}/showSTV")
    public String showStudentsVariants(@PathVariable("id") int id){
        dbRepo.GetById(id).setStudentsVariantList(dbRepo.GetById(id).getTestingTableRepo().ParseTestingTable(
                dbRepo.GetById(id).getStudentsRepo(), dbRepo.GetById(id).getVariantsRepo()));
        return "redirect:/dblist/"+id;
    }
}
