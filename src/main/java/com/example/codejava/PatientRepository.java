package com.example.codejava;

import com.example.codejava.Patient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<Patient, Long> {
}
