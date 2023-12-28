package com.example.springbootdepartement.Controllers;

import com.example.springbootdepartement.DTO.DepartementDTO;
import com.example.springbootdepartement.DTO.EmployeDTO;
import com.example.springbootdepartement.Mapper.DepartementMapper;
import com.example.springbootdepartement.Mapper.EmployeMapper;
import com.example.springbootdepartement.Models.Departement;
import com.example.springbootdepartement.Services.IDepartementService;
import com.example.springbootdepartement.Services.IEmployeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;

@Controller
public class EmployeController {
    private IEmployeService service ;
    private IDepartementService service_departement;
    @Autowired
    public EmployeController(IEmployeService service,IDepartementService service_departement) {
        this.service = service;
        this.service_departement=service_departement;
    }

    @GetMapping("/employees")
    public String index(Model model) {
        EmployeDTO employe = new EmployeDTO();
        model.addAttribute("emp_dto",employe);
        model.addAttribute("employees",service.getAll());
        model.addAttribute("departements",service_departement.getAll());
        return "employees";
    }

    @PostMapping("/AddEmploye")
    public String addEmploye(@Valid @ModelAttribute EmployeDTO employe , BindingResult result,
                             RedirectAttributes redirectAttributes, @RequestParam int id_dept) {
        if(result.hasErrors()){
            FieldError error = result.getFieldError("nom");
            FieldError error2 = result.getFieldError("salaire");
            redirectAttributes.addFlashAttribute("message",error.getDefaultMessage());
            redirectAttributes.addFlashAttribute("message2",error2.getDefaultMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/employees";
        }
        employe.setRef_dep(DepartementMapper.mapToDepartement(service_departement.findDepartement(id_dept)));
        service.add(employe);
        redirectAttributes.addFlashAttribute("message", "Vous avez ajouter un nouveau employé !");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/employees";
    }

    @PostMapping("/DeleteEmploye")
    public String deleteEmploye(@RequestParam int id ,RedirectAttributes redirectAttributes ) {
        EmployeDTO emp_to_delete = service.findEmploye(id);
        if(emp_to_delete.getRef_dep()!=null) {
            Departement dep = emp_to_delete.getRef_dep();
            emp_to_delete.setRef_dep(null);
            service.update(emp_to_delete);
            service_departement.update(DepartementMapper.mapToDepartementDTO(dep));
            dep.getEmployes().remove(emp_to_delete);
        }
        service.delete(emp_to_delete);
        redirectAttributes.addFlashAttribute("message", "Vous avez supprimez l'employé !");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/employees";
    }

    @GetMapping("/EditEmploye/{id}")
    public String editEmploye(@PathVariable int id , Model model) {
        EmployeDTO emp_to_edit = service.findEmploye(id);
        model.addAttribute("employe" , emp_to_edit);
        model.addAttribute("departements",service_departement.getAll());
        return "edit-employe";
    }

    @PostMapping("/UpdateEmploye/{emp_id}")
    public String updateEmploye(@PathVariable("emp_id") int id_emp ,
                                    @Valid @ModelAttribute EmployeDTO employe ,
                                    BindingResult result , RedirectAttributes redirectAttributes ,  @RequestParam int id_dept) {
        if(result.hasErrors()){
            FieldError error = result.getFieldError("nom");
            FieldError error2 = result.getFieldError("salaire");
            redirectAttributes.addFlashAttribute("message",error.getDefaultMessage());
            redirectAttributes.addFlashAttribute("message2",error2.getDefaultMessage());
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/EditEmploye/"+id_emp;
        }
        employe.setId_emp(id_emp);
        DepartementDTO departement_dto = service_departement.findDepartement(id_dept);
        employe.setRef_dep(DepartementMapper.mapToDepartement(departement_dto));
        service.update(employe);
        redirectAttributes.addFlashAttribute("message","L'employé est modifié avec succès");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        return "redirect:/EditEmploye/"+id_emp ;
    }


}
