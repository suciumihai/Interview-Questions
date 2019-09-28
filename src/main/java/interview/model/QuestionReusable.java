package interview.model;

import interview.Enums.DifficultyLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//apparently it is not good for embeddales to contain collections, if they themselves are in a collection later
@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class QuestionReusable {

    @Column(name="name", unique = true, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficulty;

    private String content;

}
