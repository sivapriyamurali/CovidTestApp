package com.example.codejava;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AppointmentHcRepository extends JpaRepository<AppointmentHc, Long> {
    List<AppointmentHc> findByTestCenter(String name);

}
