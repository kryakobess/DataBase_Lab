package src.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import src.Repositories.DBRepo;
import src.domains.DataBase;

import javax.validation.Valid;

@Controller
public class MainMenuController {
    final private DBRepo dbRepo;

    public MainMenuController(DBRepo dbRepo) {
        this.dbRepo = dbRepo;
    }

    @GetMapping("/")
    public String getMainPage(){
        return "mainMenu";
    }
    @GetMapping("/createDB")
    public String getCreation(@RequestParam(required = false)boolean exists, Model model){
        model.addAttribute("newDB", new DataBase());
        if (exists) model.addAttribute("ExistsWarning", "DB with such name already exists");
        return "createDB";
    }
    @PostMapping("/createDB")
    public String createDB(@ModelAttribute("newDB") @Valid DataBase db, Errors errors){
        if (errors.hasErrors()){
            return "/createDB";
        }
        if (dbRepo.Post(db) == -1){
            return "redirect:/createDB?exists=true";
        }
        return "redirect:/dblist";
    }
}
