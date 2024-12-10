package com.hospitallentesboulevard.opticaadmin.web;

import com.hospitallentesboulevard.opticaadmin.business.PatientBusiness;
import com.hospitallentesboulevard.opticaadmin.model.Patient;
import com.hospitallentesboulevard.opticaadmin.payload.PatientView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.hospitallentesboulevard.opticaadmin.utils.URLConstantsControllers;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping({"", URLConstantsControllers.PATIENTS_BASE_MVC})
public class PatientController {

    @Autowired
    private PatientBusiness patientBusiness;

    @GetMapping
    public String listPatients(Model model) {
        List<PatientView> patients = patientBusiness.findAllPatientView();
        model.addAttribute("patients", patients);
        return "patients/list";
    }

    @GetMapping("/nuevo")
    public String createPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patients/create";
    }

    @PostMapping
    public String savePatient(@ModelAttribute Patient patient) {
        patient.setId(UUID.randomUUID().toString());
        patientBusiness.save(patient);
        return "redirect:/";
    }

    @GetMapping("/{id}/editar")
    public String editPatientForm(@PathVariable String id, Model model) {
        Patient patient = patientBusiness.findById(id);
        model.addAttribute("patient", patient);
        return "patients/edit";
    }

    @PostMapping("/actualizar/{id}")
    public String updatePatient(@PathVariable String id, @ModelAttribute Patient patient) {
        patientBusiness.update(id, patient);
        return "redirect:/";
    }

    @GetMapping("/{id}/eliminar")
    public String deletePatient(@PathVariable String id) {
        patientBusiness.delete(id);
        return "redirect:/";
    }

}
