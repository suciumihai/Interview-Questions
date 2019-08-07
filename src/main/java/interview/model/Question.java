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

    //private Dificulty difficulty;

    //private Type type;

    @Column
    private String difficulty;
    //Easy, Medium, Hard

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    // daca am scos lista de questions din category, nu tre neaparat sa scot si cat din question

    @ManyToOne
    @JoinColumn(name = "template_id")
    private Template template;

    @Column
    private String content;

    @ElementCollection(targetClass = String.class)
    private List<String> possibleAnswers;

    @ElementCollection(targetClass = String.class)
    private List<String> correctAnswers;

    @ElementCollection(targetClass = String.class)
    private List<String> selectedAnswers;

//    @OneToMany(mappedBy = "question")
//    private List<Answer> answers = new ArrayList<Answer>();
//    temporary decided not to use answer as a string in question
}
