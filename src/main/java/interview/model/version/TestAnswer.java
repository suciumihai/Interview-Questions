package interview.model.version;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TestAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

    private  String text;

    private boolean value;

    @ManyToOne
    @JoinColumn(name = "testQuestion_id")
    private TestQuestion testQuestion;
}