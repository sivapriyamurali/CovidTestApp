package com.example.codejava;

import com.mysql.cj.jdbc.Blob;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "users")
@Getter
@Setter
@Accessors(chain = true)
public class Patient {
    @Id
    private Long id;

    private String fullname;

    private String email;

    private Long phone;

    private Long age;

    private String address;

    private String image;

}
