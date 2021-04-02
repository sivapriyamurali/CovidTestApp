package com.example.codejava;

import lombok.AllArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Controller
@RequestMapping("hc")
@AllArgsConstructor
public class PatientApi {

    private final PatientRepository repository;

    @GetMapping("patient")
    public String index() {
        return "HC_PatientList";
    }

    @GetMapping("patient/update/{id}")
    public String updateIndex(@PathVariable("id") Long id, Model model) {
        repository.findById(id).ifPresent((p) -> model.addAttribute("patient", p));
        return "HC_UpdatePatientRecord";
    }


    @GetMapping("patients")
    @ResponseBody
    public List<Patient> findAll() {
        return repository.findAll();
    }


    @PostMapping("patients/{id}")
    @ResponseBody
    public ResponseEntity upload(@PathVariable("id") Long id, MultipartFile file) {

        try (InputStream inputStream = file.getInputStream();
        ) {
            byte[] imgdata = inputStream.readAllBytes();
            String image64 = Base64.encodeBase64String(imgdata);
            repository.findById(id).ifPresent((p) ->
                    repository.save(p.setImage(image64))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }


        return ResponseEntity.ok("true");
    }
}
