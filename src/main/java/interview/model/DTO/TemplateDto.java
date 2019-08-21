package interview.model.DTO;

import java.util.ArrayList;
import java.util.List;

public class TemplateDto {

    private Long id;

    private String name;

    final private List<CategoryTemplateDto> categoryTemplates = new ArrayList<>();

}
