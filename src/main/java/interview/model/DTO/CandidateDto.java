package interview.model.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CandidateDto {

    private Long id;

    private String name;

    private String surName;

    private String email;

    private String phone;
}
