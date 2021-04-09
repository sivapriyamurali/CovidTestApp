
package com.example.codejava;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AppointmentRepository extends JpaRepository<Appointment, Long>
{

    @Query("SELECT DISTINCT u.Date FROM Appointment u where u.Type = 'Test' and u.Test_Center = ?1 ")
    List<String> findDistinctDate(String testCenter);

    @Query("SELECT DISTINCT u.Time FROM Appointment u where u.Type = 'Test' and u.Test_Center = ?1 and u.Date = ?2 ")
    List<String> findDistinctTime(String testCenter, String testDate);

    @Query("SELECT DISTINCT u.Date FROM Appointment u where u.Type = 'Vaccine'")
    List<String> findDistinctvaccineDate();

    @Query("SELECT DISTINCT u.Date FROM Appointment u where u.Type = 'Vaccine' and u.Test_Center = ?1 ")
    List<String> findDistinctVacDate(String testCenter);

    @Query("SELECT DISTINCT u.Time FROM Appointment u where u.Type = 'Vaccine' and u.Test_Center = ?1 and u.Date = ?2 ")
    List<String> findDistinctvacTime(String testCenter, String testDate);

}