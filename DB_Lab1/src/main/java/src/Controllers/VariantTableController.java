package src.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import src.Repositories.DBRepo;
import src.Services.FileService;
import src.domains.Variant;

import javax.validation.Valid;

@Controller
public class VariantTableController {
    private final DBRepo dbRepo;
    private final FileService fileService;


    public VariantTableController(DBRepo dbRepo, FileService fileService) {
        this.dbRepo = dbRepo;
        this.fileService = fileService;
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
}
