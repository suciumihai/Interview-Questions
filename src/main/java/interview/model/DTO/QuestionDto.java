package interview.model.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionDto {

    private Long id;

    private String name;

    private String difficulty;

    private CategoryDto categoryDto;

    private String content;

    @ElementCollection(targetClass = String.class)
    private List<String> possibleAnswers = new ArrayList<>();

    @ElementCollection(targetClass = String.class)
    private List<String> correctAnswers = new ArrayList<>();
}
