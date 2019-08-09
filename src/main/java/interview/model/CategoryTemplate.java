package interview.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class CategoryTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private Template template;
    //chair daca asta nu o sa il arat in html, tot tre sa existe, ca asa mearga lista din template

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    int questionNumber = 0;

}
