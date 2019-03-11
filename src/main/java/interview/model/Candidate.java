package interview.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Candidate {

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
