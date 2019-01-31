package model.version;

import enums.Dificulty;
import enums.Type;
import lombok.Getter;
import lombok.Setter;
import model.Category;

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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String content;

    @OneToMany(mappedBy = "testQuestion")
    private List<TestAnswer> testAnswers = new ArrayList<TestAnswer>();

}
