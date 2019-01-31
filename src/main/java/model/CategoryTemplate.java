package model;

import enums.Dificulty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class CategoryTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

    @OneToMany
    @JoinColumn(name = "template_id")
    private Template template;

    @OneToMany
    @JoinColumn(name = "category_id")
    private Category category;

}
