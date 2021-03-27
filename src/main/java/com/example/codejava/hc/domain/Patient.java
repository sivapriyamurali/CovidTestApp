package com.example.codejava.hc.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since 23/03/2021
 */
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
