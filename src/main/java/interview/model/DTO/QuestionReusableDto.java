package interview.model.DTO;

import interview.Enums.DifficultyLevel;
import interview.model.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionReusableDto {

    private String name;

    private DifficultyLevel difficulty;

    private String content;
}
