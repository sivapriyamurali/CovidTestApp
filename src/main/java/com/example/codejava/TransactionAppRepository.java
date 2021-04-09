package com.example.codejava;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionAppRepository extends JpaRepository<TransactionApp, Long>

{
    @Query("SELECT u FROM TransactionApp u WHERE u.id = ?1")
    public TransactionApp findById(int id);

    @Query("SELECT u FROM TransactionApp u WHERE u.Patient_id = ?1 and u.Date <= CURRENT_DATE ")
    public List<TransactionApp> findByNamePastAppointments(String Name);

    @Query("SELECT u FROM TransactionApp u WHERE u.Patient_id = ?1 and u.Date > CURRENT_DATE ")
    public List<TransactionApp> findByNameFutureAppointments(String Name);

    @Query("SELECT u FROM TransactionApp u WHERE u.id = ?1 ")
    public TransactionApp findBytransactionId(long id);


}
