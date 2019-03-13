package interview.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Candidate {

    //aici nu mai am nevoide de jsonGInore, caci nu mai folosesc in controller GetOne, ci FindBYID. getOne dadea o referinta, deci de fapt un proxy cu java asist, si ala era lazy

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//daca comentez asta, las sa vina id manual. altfel, indiferent ce bag e, el decide la final
    private Long id;

    private String name;

    private String surName;

    @Email
    @Column(name="email", unique = true, nullable = false)
    private String email;

    private String phone;

}
