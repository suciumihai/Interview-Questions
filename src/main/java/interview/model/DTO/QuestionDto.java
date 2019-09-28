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

    private CategoryDto categoryDto;

    private QuestionReusableDto questionReusableDto;

    final private List<String> possibleAnswers = new ArrayList<>();

    final private List<String> correctAnswers = new ArrayList<>();
}
