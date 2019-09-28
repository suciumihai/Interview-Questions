package interview.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import interview.Enums.DifficultyLevel;
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
        this.test = null;
        this.setCategory(question.getCategory());
        this.setQuestionReusable(question.getQuestionReusable());
        this.getQuestionReusable().setName(question.getQuestionReusable().getName());
        this.getQuestionReusable().setDifficulty(question.getQuestionReusable().getDifficulty());
        this.getQuestionReusable().setContent(question.getQuestionReusable().getContent());
        this.getPossibleAnswers().addAll(question.getPossibleAnswers());
        this.getCorrectAnswers().addAll(question.getCorrectAnswers());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Embedded
    private QuestionReusable questionReusable;

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
