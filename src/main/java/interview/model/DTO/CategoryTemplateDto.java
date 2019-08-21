package interview.model.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryTemplateDto {

    private Long id;

    private String name;

    private TemplateDto templateDto;

    private CategoryDto categoryDto;

    private int questionNumber;

    private String difficulty;
}
