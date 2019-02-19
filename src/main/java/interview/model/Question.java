package interview.model;

import interview.enums.Dificulty;
import interview.enums.Type;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


// question este 1 to many pentru answers; deci tre spring boot application, jpa auditing, pentru main, si persistence pentru oneToMany
@Entity
@Setter
@Getter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

    private Dificulty dificulty;

    private Type type;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String content;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<Answer>();

}
