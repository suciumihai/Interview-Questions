package interview.model;

import lombok.*;

import javax.validation.constraints.Email;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email
    @Column(name="email", unique = true, nullable = false)
    private String email;

    private String password;

    private String role;
}
