package src.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import src.Repositories.DBRepo;
import src.Services.FileService;

@Controller
public class TestingTableController {
    private final DBRepo dbRepo;
    private final FileService fileService;


    public TestingTableController(DBRepo dbRepo, FileService fileService) {
        this.dbRepo = dbRepo;
        this.fileService = fileService;
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
