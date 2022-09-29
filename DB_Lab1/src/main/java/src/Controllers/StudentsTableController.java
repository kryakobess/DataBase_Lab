package src.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import src.Repositories.DBRepo;
import src.Services.FileService;
import src.domains.Student;

import javax.validation.Valid;

@Controller
public class StudentsTableController {
    private final DBRepo dbRepo;
    private final FileService fileService;

    public StudentsTableController(DBRepo dbRepo, FileService fileService) {
        this.dbRepo = dbRepo;
        this.fileService = fileService;
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
}
