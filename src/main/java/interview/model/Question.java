package interview.model;

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

    @Override
    public int compareTo(Question question) {
        return (this.name.compareTo(question.getName()));
    }

}
