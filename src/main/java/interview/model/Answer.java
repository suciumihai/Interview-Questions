package interview.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

    private String text;

    private String value;
    /* true or false */

//    @ManyToOne
//    @JoinColumn(name = "question_id")
//    private Question question;
//    temporary inserting asnwer in question as a string

}
