package interview.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Test implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "template_id")
    private Template template;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @OneToMany(targetEntity=TestQuestion.class, mappedBy = "test", cascade={CascadeType.MERGE},orphanRemoval=true)
    final private Set<TestQuestion> testQuestions = new HashSet<>();

    @Column(name="name", unique = true, nullable = false)
    private String name;

    String nota;

}
