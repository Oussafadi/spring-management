package com.example.springbootdepartement.Controllers;

import com.example.springbootdepartement.DTO.DepartementDTO;
import com.example.springbootdepartement.DTO.EmployeDTO;
import com.example.springbootdepartement.Models.Departement;
import com.example.springbootdepartement.Services.IDepartementService;
import com.example.springbootdepartement.Services.IEmployeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
public class DepartementController {
       private IDepartementService service ;
       private IEmployeService service_employe ;
       @Autowired
       public DepartementController(IDepartementService service , IEmployeService service_employe) {
             this.service = service;
             this.service_employe = service_employe;
         }


    @GetMapping("/")
    public String dashboard(Model model) {
           List<EmployeDTO> employees = service_employe.getAll();
           model.addAttribute("nombre_employes", employees.size());
           List<DepartementDTO> departements = service.getAll();
          Optional<DepartementDTO> grand_departement= service.lePlusGrandDepartement(departements);
          grand_departement.ifPresent(departementDTO ->
                  model.addAttribute("grand_departement",departementDTO.getNom_dept()));
          model.addAttribute("masse_salariale", service.masseSalariale(departements));
           return "dashboard";
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
              if(error != null) {
                  redirectAttributes.addFlashAttribute("message", error.getDefaultMessage());
              }
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
           List<EmployeDTO> employees_orphan = dep_to_delete.getEmployes();
            employees_orphan.stream().forEach(employe -> {
                                employe.setRef_dep(null);
                                service_employe.update(employe);
                            });
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
        if(result.hasErrors()){
            FieldError error = result.getFieldError("nom_dept");
            if(error != null) {
                redirectAttributes.addFlashAttribute("message", error.getDefaultMessage());
            }
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/EditDepartement/"+id_dept;
        }
        departement.setId_dept(id_dept);
        service.update(departement);
        redirectAttributes.addFlashAttribute("message","Le département est modifié avec succès");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/EditDepartement/"+id_dept ;
    }

    @GetMapping("/ShowEmployees/{id_dep}")
    public String showEmployees(@PathVariable("id_dep") int id_dep ,Model model) {
           DepartementDTO dep_dto = service.findDepartement(id_dep);
           List<EmployeDTO> employees = dep_dto.getEmployes();
           model.addAttribute("employees",employees);
           model.addAttribute("departement_parent",dep_dto);
            EmployeDTO employe = new EmployeDTO();
            model.addAttribute("emp_dto",employe);
        model.addAttribute("departements",service.getAll());
               return "employees";
    }



}
