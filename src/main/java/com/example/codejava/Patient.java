package com.example.codejava;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "users")
@Getter
@Setter
public class Patient {
    @Id
    private Long id;

    private String fullname;

    private String email;

    private Long phone;

    private Long age;

    private String address;

}
