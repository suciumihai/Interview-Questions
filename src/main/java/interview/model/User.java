package interview.model;

import interview.enums.Role;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;

import javax.persistence.*;

//mi-a trebuit hibernate entitymanager in maven, pentru entity
@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email
    @Column(name="email", unique = true, nullable = false)
    private String email;

    private String password;

    //@Enumerated(EnumType.STRING)
    //private Role role;
    //prea compli sa fac enums in angular, raman la string :((

    private String role;
}
