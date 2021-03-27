package com.example.codejava.hc.api;

import com.example.codejava.hc.domain.Appointment;
import com.example.codejava.hc.repo.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("hc")
@RequiredArgsConstructor
public class AppointmentApi {

    private final AppointmentRepository repository;

    @GetMapping("appointment")
    public String index() {
        return "hc/appt/index";
    }

    @GetMapping("appointments")
    @ResponseBody
    public List<Appointment> findAll() {
        return repository.findAll();
    }


    @PostMapping("appointments")
    @ResponseBody
    public ResponseEntity<Appointment> add(@RequestBody Appointment appointment) {
        final var save = repository.save(appointment);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("appointments/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok(StringUtils.EMPTY);
    }
}
