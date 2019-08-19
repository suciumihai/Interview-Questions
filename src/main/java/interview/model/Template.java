package interview.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Proxy;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Template implements Serializable{

    //model de jpa nu se expune ca serviciu rest
    //iti trebuie layer/framework de mapare. 1 model d edate pentgru entitati, 1 model pentru datele mele. model rest e peste model jpa
    //altfel dai de ciclicitate

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //un fel de prim key, pentru mine
    @Column(name="name", unique = true, nullable = false)
    private String name;

    //@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    //@OneToMany(targetEntity = CategoryTemplate.class, mappedBy = "template")
    @OneToMany(mappedBy = "template")
    //private List<CategoryTemplate> categoryTemplates = new ArrayList<>();
    final private Set<CategoryTemplate> categoryTemplates = new HashSet<>();
    //maparea dintre entitati e un proxy. cu seteri scri inproxyul hibernatelui. categoryTemplates e de fapt proxul hibernate
    // de aia il faci final, si iei doar getter. nu trebuie sa poti seta colectia. lombobk nu face setter la final

    //decat sa pui eager, ma ibine incarci cu mana ta. EWAGER merge greu, greu. iti ma mult pe teava, si nu e garantat

    //@Column(nullable = false, name = "duration")
    //private String duration = "one minute";
    // primitive e bine not null, cu def value

    //@OneToMany(targetEntity = Question.class, mappedBy = "template")
    //@OneToMany(mappedBy = "template")
    //private List<Question> questions = new ArrayList<>();
    //final private Set<Question> questions = new HashSet<>();
    //in loc de category, am decis sa am lista de questions in template

    //@OneToMany(mappedBy = "template")
    //private ArrayList<Test> tests;
    // nu e obligatorie reciprocitatea cu lista, intre el si test, chiar daca test are ceva in el ca foreign key catre template
    // in cazul meu e independent, chair in cazul asa nu vreau lista de teste

}
