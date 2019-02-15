package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// this is just to do a couple of quikc tests with candidate, not actually in  use. it's embedable, not an entity per se, and has no repo in itself
@Embeddable
@Getter
@Setter
public class CandidateHelperClass {

    public CandidateHelperClass(String name){
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private String name;
}
