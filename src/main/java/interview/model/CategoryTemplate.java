package interview.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class CategoryTemplate{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "template_id")
    @JsonIgnore
    private Template template;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    private int questionNumber = 0;

    @Column
    private String difficulty;


}
