package com.hospitallentesboulevard.opticaadmin.web;

import com.hospitallentesboulevard.opticaadmin.business.PatientBusiness;
import com.hospitallentesboulevard.opticaadmin.business.PrescriptionBusiness;
import com.hospitallentesboulevard.opticaadmin.model.Patient;
import com.hospitallentesboulevard.opticaadmin.model.Prescription;
import com.hospitallentesboulevard.opticaadmin.utils.URLConstantsControllers;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Controller
@RequestMapping(URLConstantsControllers.PRESCRIPTIONS_BASE_MVC)
public class PrescriptionController {

    @Autowired
    private PrescriptionBusiness prescriptionBusiness;

    @Autowired
    private PatientBusiness patientBusiness;

    private static final String URL_PATIENT = "/paciente";

    @GetMapping(URL_PATIENT + "/{id}")
    public String listPrescriptions(@PathVariable("id") String patientId, Model model) {
        List<Prescription> prescriptions = prescriptionBusiness.findAll(patientId);
        model.addAttribute("prescriptions", prescriptions);
        Patient patient = patientBusiness.findById(patientId);
        model.addAttribute("patient", patient);
        return "prescriptions/list";
    }

    @GetMapping( "/nueva" + URL_PATIENT + "/{patient_id}")
    public String createPrescriptionForm(@PathVariable("patient_id") String patientId, Model model) {
        Prescription prescription = new Prescription();
        Patient patient  = patientBusiness.findById(patientId);
        prescription.setPatient(patient);
        model.addAttribute("prescription", prescription);
        return "prescriptions/create";
    }

    @PostMapping
    public String savePrescription(@ModelAttribute Prescription prescription) {
        prescriptionBusiness.save(prescription);
        return "redirect:/graduaciones" + URL_PATIENT + "/" + prescription.getPatient().getId();
    }

    @GetMapping("/{id}/editar")
    public String editPrescriptionForm(@PathVariable String id, Model model) {
        Prescription prescription = prescriptionBusiness.findById(id);
        model.addAttribute("prescription", prescription);
        return "prescriptions/edit";
    }

    @PostMapping("/actualizar/{id}")
    public String updatePrescription(@PathVariable String id, @ModelAttribute Prescription prescription) {
        prescriptionBusiness.update(id, prescription);
        return "redirect:/graduaciones" + URL_PATIENT + "/" + prescription.getPatient().getId();
    }

    @GetMapping("/{id}/eliminar")
    public String deletePrescription(@PathVariable String id) {
        String patientId = prescriptionBusiness.findById(id).getPatient().getId();
        prescriptionBusiness.delete(id);
        return "redirect:/graduaciones" + URL_PATIENT + "/" + patientId;
    }

}
