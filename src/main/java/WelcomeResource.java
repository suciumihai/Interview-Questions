import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//daca rest controller vine din spring.web si gata, de ce mai am in pom <groupId>org.springframework.boot</groupId> si spring-boot-starter-web?!?!?!!???!!??!?!?!?!?

@RestController
public class WelcomeResource {

    //adica ia value din app config
    @Value("${welcome.message}")
    private String welcomeMessage;

    //expune un serviciu cu config
    @GetMapping("/welcome")
    public String retrieveWelcomeMessage(){
        // complex method
        return welcomeMessage;
    }
    //cand lansezi app, ttp://localhost:8080/welcome, tre sa vezi welcome message din app file
}
