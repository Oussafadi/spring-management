package com.example.springbootdepartement.Controllers;

import com.example.springbootdepartement.DTO.DepartementDTO;
import com.example.springbootdepartement.Models.Departement;
import com.example.springbootdepartement.Services.IDepartementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Field;

@Controller
public class DepartementController {
       private IDepartementService service ;
       @Autowired
       public DepartementController(IDepartementService service) {
             this.service = service;
         }

    @GetMapping("/home")
    public String home(Model model){
        DepartementDTO dep = new DepartementDTO();
        model.addAttribute("departements", service.getAll());
        model.addAttribute("dep",dep);
        return "departements";
    }

    @PostMapping("/AddDepartement")
    public String addDepartement(@Valid @ModelAttribute DepartementDTO departement,
                                  BindingResult result ,RedirectAttributes redirectAttributes) {
          if(result.hasErrors()){
              FieldError error = result.getFieldError("nom_dept");
              redirectAttributes.addFlashAttribute("message",error.getDefaultMessage());
              redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
              return "redirect:/home";
          }
           service.add(departement);
           redirectAttributes.addFlashAttribute("message", "Vous avez ajouter un nouveau département !");
          redirectAttributes.addFlashAttribute("alertClass", "alert-success");
           return "redirect:/home";
    }

    @PostMapping("/DeleteDepartement")
    public String deleteDepartement(@RequestParam int id ,RedirectAttributes redirectAttributes ) {
           DepartementDTO dep_to_delete = service.findDepartement(id);
           service.delete(dep_to_delete);
        redirectAttributes.addFlashAttribute("message", "Vous avez supprimez le département !");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/home";
    }

    @GetMapping("/EditDepartement/{id_dept}")
    public String editDepartement(@PathVariable("id_dept") int id_dept , Model model ) {
           DepartementDTO dep_to_edit = service.findDepartement(id_dept);
           model.addAttribute("dep",dep_to_edit);
           return "edit-departement";
    }

    @PostMapping("/UpdateDepartement/{id_dept}")
    public String updateDepartement(@PathVariable("id_dept") int id_dept ,
                                    @Valid @ModelAttribute DepartementDTO departement ,
                                    BindingResult result , RedirectAttributes redirectAttributes) {
        departement.setId_dept(id_dept);
        service.update(departement);
        redirectAttributes.addFlashAttribute("message","Le département est modifié avec succès");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/EditDepartement/"+id_dept ;
    }



}
