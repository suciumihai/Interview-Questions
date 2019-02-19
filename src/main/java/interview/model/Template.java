package interview.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //un fel de prim key, pentru mine
    @Column(name="name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "template")
    private List<CategoryTemplate> categoryTemplates = new ArrayList<CategoryTemplate>();

    @Column(nullable = false, name = "duration")
    private int duration = 1;
    // primitive e bine not null, cu def value

    //@OneToMany(mappedBy = "template")
    //private ArrayList<Test> tests;
    // nu e obligatorie reciprocitatea cu lista, intre el si test, chiar daca test are ceva in el ca foreign key catre template
    // in cazul meu e independent, chair in cazul asa nu vreau lista de teste

}
