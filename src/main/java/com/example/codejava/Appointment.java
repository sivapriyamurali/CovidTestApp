package com.example.codejava;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "appointment")
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    private Long day;

    private Long invitedCount;

    private Long month;

    private Long year;

    private String time;


}
