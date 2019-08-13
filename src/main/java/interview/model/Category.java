package interview.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//aparent jackson are nevoie de no args constructor
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

//    @OneToMany(mappedBy = "category")
//    private List<Question> questions = new ArrayList<Question>();
    //hai sa nu puem lista de quesitions in category 4 now, let's move it in template

    // aici tratam o FK catre el, ca parinte
    // fiind lazy jpa lista automat, dar nu e cam in tabel. il incarca din bd, doar de pe primu nivel
    // pot sa sar asta, da ca sa gasesc copii ar trebui sa fac query; asa imi da jpa ca am pus lista, cu get Parent, etc
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children=new ArrayList<Category>();

//    imi da balarii mari daca las lista aici...
//    @OneToMany(mappedBy = "category")
//    private List<CategoryTemplate> categoryTemplates = new ArrayList<>();

    //sarim versionare for now
    //@OneToMany(mappedBy = "category")
    //private List<TestQuestion> testQuestions = new ArrayList<>();

}
