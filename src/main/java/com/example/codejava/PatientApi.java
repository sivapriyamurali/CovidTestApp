package com.example.codejava;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("hc")
public class PatientApi {

    @Autowired
    private PatientRepository repository;

    @Autowired
    private TransactionRepository transactionRepository;

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
        return repository.findAll()
                .stream().peek(p -> transactionRepository.findByPatientId(p.getEmail())
                        .ifPresent(t -> p.setImage(t.getImage64()))).collect(Collectors.toList());

    }


    @PostMapping("patients/{id}")
    @ResponseBody
    public ResponseEntity upload(@PathVariable("id") Long id, MultipartFile file) {

        try (InputStream inputStream = file.getInputStream();
        ) {
            byte[] imgdata = inputStream.readAllBytes();
            repository.findById(id).ifPresent((p) ->
                    transactionRepository.findByPatientId(p.getEmail())
                            .map(t -> {
                                t.setImage(imgdata);
                                return transactionRepository.save(t);
                            })
                            .orElseGet(() -> {
                                Transaction transaction = new Transaction();
                                transaction.setImage(imgdata);
                                transaction.setDate(StringUtils.EMPTY);
                                transaction.setTestCenter(StringUtils.EMPTY);
                                transaction.setTestType(StringUtils.EMPTY);
                                transaction.setTimeslot(StringUtils.EMPTY);
                                transaction.setType(StringUtils.EMPTY);
                                transaction.setPatientId(p.getEmail());
                                return transactionRepository.save(transaction);
                            })
            );
        } catch (IOException e) {
            e.printStackTrace();
        }


        return ResponseEntity.ok("true");
    }
}
