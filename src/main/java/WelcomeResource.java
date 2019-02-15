import config.BasicConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.HashMap;
import java.util.Map;

//daca rest controller vine din spring.web si gata, de ce mai am in pom <groupId>org.springframework.boot</groupId> si spring-boot-starter-web?!?!?!!???!!??!?!?!?!?

@RestController
public class WelcomeResource {
//asta e pasul simplu, acum trebuie configurat basic configuration

    //adica ia value din app config - application.porperties
    @Value("${welcome.message}")
    private String welcomeMessage;

    //expune un serviciu cu config
    @GetMapping("/welcome")
    public String retrieveWelcomeMessage(){
        // complex method
        return welcomeMessage;
    }
    //cand lansezi app, ttp://localhost:8080/welcome, tre sa vezi welcome message din app file

    @Autowired
    private BasicConfiguration configuration;
    //Its very easy to use BasicConfiguration. Autowire it in when ever you need the value for a property from it.

    @RequestMapping("/dynamic-configuration") //public Map
    public Map<String, Object> dynamicConfiguration() {
        // Not the best practice to use a map to store differnt types!
        Map<String, Object> map = new HashMap<>();
        map.put("message", configuration.getMessage());
        map.put("number", configuration.getNumber());
        map.put("key", configuration.isValue());
        return map;
    }
    //Define a simple service to expose the configured values. - dynamicConfiguration(). configured values le iei din app.properties
    //when you browse to http://localhost:8080/dynamic-configuration, you see: {"number":100,"message":"Dynamic Message","key":true}
}
