package com.example.springbootdepartement.Controllers;

import com.example.springbootdepartement.Models.Departement;
import com.example.springbootdepartement.Services.IDepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DepartementController {
       private IDepartementService service ;
       @Autowired
       public DepartementController(IDepartementService service) {
             this.service = service;
         }

    @GetMapping("/home")
    public String home(Model model){
        Departement dep = new Departement();
        model.addAttribute("departements", service.getAll());
        model.addAttribute("dep",dep);
        return "departements";
    }

    @PostMapping("/AddDepartement")
    public String addDepartement(@ModelAttribute Departement departement, RedirectAttributes redirectAttributes) {
           service.add(departement);
           redirectAttributes.addFlashAttribute("message", "Vous avez ajouter un nouveau département !");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
           return "redirect:/home";
    }

    @PostMapping("/DeleteDepartement")
    public String deleteDepartement(@RequestParam double id ,RedirectAttributes redirectAttributes ) {
           Departement dep_to_delete = service.findDepartement(id);
           service.delete(dep_to_delete);
        redirectAttributes.addFlashAttribute("message", "Vous avez supprimez le département !");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/home";
    }

    @GetMapping("/EditDepartement/{id_dept}")
    public String editDepartement(@PathVariable("id_dept") Double id_dept , Model model ) {
           Departement dep_to_edit = service.findDepartement(id_dept);
           model.addAttribute("departement",dep_to_edit);
           return "edit_departement";
    }



}
