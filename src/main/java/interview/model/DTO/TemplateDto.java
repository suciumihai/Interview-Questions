package interview.model.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TemplateDto {

    private Long id;

    private String name;

    private List<CategoryTemplateDto> categoryTemplatesDto = new ArrayList<>();

}
