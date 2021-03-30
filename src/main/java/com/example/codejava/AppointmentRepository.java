package com.example.codejava;

import com.example.codejava.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
