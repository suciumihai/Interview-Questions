package interview.model.version;

import interview.enums.Dificulty;
import interview.enums.Type;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class TestQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

    private Dificulty dificulty;

    private Type type;

    //sarim validare for now...
    //@ManyToOne
    //@JoinColumn(name = "category_id")
    //private Category category;

    private String content;

    @OneToMany(mappedBy = "testQuestion")
    private List<TestAnswer> testAnswers = new ArrayList<TestAnswer>();

}
