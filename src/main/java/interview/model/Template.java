package interview.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Template implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //un fel de prim key, pentru mine
    @Column(name="name", unique = true, nullable = false)
    private String name;

    //@JsonFormat(with = JsonFormat.Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @OneToMany(targetEntity = CategoryTemplate.class, mappedBy = "template", fetch = FetchType.EAGER)
    private List<CategoryTemplate> categoryTemplates = new ArrayList<>();

    //@Column(nullable = false, name = "duration")
    //private String duration = "one minute";
    // primitive e bine not null, cu def value

    @OneToMany(targetEntity = Question.class, mappedBy = "template")
    private List<Question> questions = new ArrayList<>();
    //in loc de category, am decis sa am lista de questions in template

    //@OneToMany(mappedBy = "template")
    //private ArrayList<Test> tests;
    // nu e obligatorie reciprocitatea cu lista, intre el si test, chiar daca test are ceva in el ca foreign key catre template
    // in cazul meu e independent, chair in cazul asa nu vreau lista de teste

}
