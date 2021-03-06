package interview.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class TestQuestion implements Serializable {

    public TestQuestion(Question question){
        this.name = question.getName();
        this.difficulty = question.getDifficulty();
        this.category = question.getCategory();
        this.content = question.getContent();
        this.possibleAnswers.addAll(question.getPossibleAnswers());
        this.correctAnswers.addAll(question.getCorrectAnswers());
        this.test = null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

    @Column
    private String difficulty;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    private String content;

    @ElementCollection(targetClass = String.class)
    final private List<String> possibleAnswers = new ArrayList<>();

    @ElementCollection(targetClass = String.class)
    final private List<String> correctAnswers = new ArrayList<>();

    @ElementCollection(targetClass = String.class)
    final private List<String> selectedAnswers = new ArrayList<>();

    @ManyToOne(targetEntity=Test.class)
    @JoinColumn(name = "test_id")
    @JsonIgnore
    private Test test;

}
