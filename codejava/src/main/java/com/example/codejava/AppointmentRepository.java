package com.example.codejava;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointments, Long>

{
    @Query("SELECT u FROM Appointments u WHERE u.id = ?1")
    public Appointments findById(int id);

    @Query("SELECT u FROM Appointments u WHERE u.Patient_id = ?1 and u.Date <= CURRENT_DATE ")
    public List<Appointments> findByNamePastAppointments(String Name);

    @Query("SELECT u FROM Appointments u WHERE u.Patient_id = ?1 and u.Date > CURRENT_DATE ")
    public List<Appointments> findByNameFutureAppointments(String Name);

}
