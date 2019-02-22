package interview.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//add basic mvc config to dispaly html files

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    public MvcConfig() {
        super();
    }

    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
