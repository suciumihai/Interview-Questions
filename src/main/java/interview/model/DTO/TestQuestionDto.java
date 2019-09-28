package interview.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import interview.model.QuestionReusable;
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

    private CategoryDto categoryDto;

    private QuestionReusableDto questionReusableDto;

    final private List<String> possibleAnswers = new ArrayList<>();

    final private List<String> correctAnswers = new ArrayList<>();

    final private List<String> selectedAnswers = new ArrayList<>();

    @JsonIgnore
    private TestDto testDto;
}
