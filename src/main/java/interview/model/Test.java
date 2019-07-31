package interview.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

@Getter
@Setter
@Entity
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "template_id")
    private Template template;
    //poti sa il sari si pe el ca foreign key, daca aplicatie nu foloseste, si implementezi in aplicatie FK, dar mai bine pastrezi

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    //best practice e ca sa ai prim key auto id generata, si o coloana unique, care sa iti fie tie identificatorul
    @Column(name="name", unique = true, nullable = false)
    private String name;

    String nota;

    //sarim versionare, for now...
    //@OneToMany(mappedBy = "test")
    //private List<TestQuestion> testQuestions=new ArrayList<TestQuestion>();

}
