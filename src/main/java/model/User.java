package model;

import enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

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

    private Role role;

}
