package interview.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryTemplateDto {

    private Long id;

    private String name;

    @JsonIgnore
    private TemplateDto templateDto;

    private CategoryDto categoryDto;

    private int questionNumber;

    private String difficulty;
}
