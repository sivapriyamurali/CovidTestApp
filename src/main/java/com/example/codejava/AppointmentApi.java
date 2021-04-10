package com.example.codejava;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("hc")
public class AppointmentApi {

    @Autowired
    private AppointmentHcRepository repository;

    @Autowired
    private TestCenterRepository centerRepository;

    @GetMapping("appointment")
    public String index(@RequestParam("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "HC_Appt";
    }

    @GetMapping("appointments/{id}")
    @ResponseBody
    public List<AppointmentHc> findAll(@PathVariable("id") Long id) {
        return centerRepository.findById(id)
                .map((c) -> repository.findByTestCenter(c.getName()))
                .orElse(new ArrayList<>());
    }


    @PostMapping("appointments")
    @ResponseBody
    public ResponseEntity<Boolean> add(@RequestBody AppointmentHc appointment) {
        appointment.buildRecord();
        centerRepository.findById(appointment.getCenterId()).ifPresent((cen) -> {
            appointment.setTestCenter(cen.getName());
            repository.save(appointment);
        });
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("appointments/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok(StringUtils.EMPTY);
    }
}
