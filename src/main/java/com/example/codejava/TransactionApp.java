package com.example.codejava;


import javax.persistence.*;

@Entity
@Table(name = "Transaction")
public class TransactionApp
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Patient_id", nullable = false, length = 45)
    private String Patient_id;

    @Column(name = "Date", nullable = false, length = 20)
    private String Date;

    @Column(name = "Timeslot", nullable = false, length = 100)
    private String Timeslot;

    @Column(name = "Test_center", nullable = false, length = 45)
    private String Test_center;

    @Column(name = "Test_Type", nullable = false, length = 45)
    private String Test_Type;

    @Column(name = "Type", nullable = true, length = 45)
    private String Type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatient_id() {
        return Patient_id;
    }

    public void setPatient_id(String patient_id)
    {
        Patient_id = patient_id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTimeslot()
    {
        return Timeslot;
    }

    public void setTimeslot(String timeslot) {
        Timeslot = timeslot;
    }

    public String getTest_center() {
        return Test_center;
    }

    public void setTest_center(String test_center) {
        Test_center = test_center;
    }

    public String getTest_Type() {
        return Test_Type;
    }

    public void setTest_Type(String test_Type) {
        Test_Type = test_Type;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

}
