package model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Candidate {

    //construcotr jsut to test with in mem db, and canditateHelper
    public Candidate(){
    }

    public Candidate(Long id, String name){
        super();
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String surName;

    @Email
    @Column(name="email", unique = true, nullable = false)
    private String email;

    private String phone;

}
