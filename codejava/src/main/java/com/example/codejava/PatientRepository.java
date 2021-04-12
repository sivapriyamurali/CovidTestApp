package com.example.codejava;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;


public interface PatientRepository extends JpaRepository<Patient, Long>
{
    List<Patient> findByFullnameLikeOrEmailLike(String key1,
                                                String key2);
}
