import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootTutorialBasicsConfigurationApplication {

    //asta e app primar, care porneste. se foloseste de welcome resource si absic config

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication
                .run(SpringBootTutorialBasicsConfigurationApplication.class, args);

        for (String name : applicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }
}