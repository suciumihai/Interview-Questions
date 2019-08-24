package interview.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TestQuestionDto {

    private Long id;

    private String name;

    private String difficulty;

    private CategoryDto categoryDto;

    private String content;

    //@ElementCollection(targetClass = String.class)
    private List<String> possibleAnswers = new ArrayList<>();

    private List<String> correctAnswers = new ArrayList<>();

    private List<String> selectedAnswers = new ArrayList<>();

    @JsonIgnore
    private TestDto testDto;
}
