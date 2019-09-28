package interview.model;

import interview.Enums.DifficultyLevel;
import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class Question implements Comparable<Question>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Embedded
    private QuestionReusable questionReusable = new QuestionReusable();

    @ElementCollection(targetClass = String.class)
    final private List<String> possibleAnswers = new ArrayList<>();

    @ElementCollection(targetClass = String.class)
    final private List<String> correctAnswers = new ArrayList<>();

    @Override
    public int compareTo(Question question) {
        return (this.getQuestionReusable().getName().compareTo(question.getQuestionReusable().getName()));
    }

}
