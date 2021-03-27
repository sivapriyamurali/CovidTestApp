package com.example.codejava.hc.api;

import com.example.codejava.hc.domain.Patient;
import com.example.codejava.hc.repo.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("hc")
@AllArgsConstructor
public class PatientApi {

    private final PatientRepository repository;

    @GetMapping("patient")
    public String index() {
        return "hc/patient/index";
    }

    @GetMapping("patient/update")
    public String toUpdate() {
        return "hc/patient/update";
    }

    @GetMapping("patients")
    @ResponseBody
    public List<Patient> findAll() {
        return repository.findAll();
    }
}
