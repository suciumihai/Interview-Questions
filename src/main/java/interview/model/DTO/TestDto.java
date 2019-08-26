package interview.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TestDto {

    private Long id;

    private TemplateDto templateDto;

    private CandidateDto candidateDto;

    //@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private Set<TestQuestionDto> testQuestionsDto = new HashSet<>();

    private String name;

    String nota;
}
