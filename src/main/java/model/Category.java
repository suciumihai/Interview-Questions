package model;

import lombok.Getter;
import lombok.Setter;
import model.version.TestQuestion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    //imi face numele automat category)_id?!?!?!?
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Question> questions = new ArrayList<Question>();

    //sarim versionare for now
    //@OneToMany(mappedBy = "category")
    //private List<TestQuestion> testQuestions = new ArrayList<>();

    // aici tratam o FK catre el, ca parinte
    // fiind lazy jpa lista automat, dar nu e cam in tabel. il incarca din bd, doar de pe primu nivel
    // pot sa sar asta, da ca sa gasesc copii ar trebui sa fac query; asa imi da jpa ca am pus lista, cu get Parent, etc
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category parent;
    //????????????????????????????e om mapat?????????
    @OneToMany(mappedBy = "parent")
    private List<Category> children=new ArrayList<Category>();


    //chiar nu stiu daca vreau o lista de categoryTemplate in category, il las commented 4 now
    @OneToMany(mappedBy = "category")
    private List<CategoryTemplate> categoryTemplates = new ArrayList<>();

}
