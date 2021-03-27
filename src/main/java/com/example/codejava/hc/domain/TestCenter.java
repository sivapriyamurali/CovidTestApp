package com.example.codejava.hc.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_center")
@Getter
@Setter
public class TestCenter {

    @Id
    private long centerId;

    private String name;

    private String address;

    private String zipCode;
}
