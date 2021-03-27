package com.example.codejava.hc.repo;

import com.example.codejava.hc.domain.Appointment;
import com.example.codejava.hc.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author LiuJie
 * @since 26/03/2021
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
