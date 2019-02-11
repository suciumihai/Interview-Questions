package config;

import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;

//Defines that this java bean contains configuration properties. All property names will start with basic
@Component
@ConfigurationProperties("basic")
public class BasicConfiguration {
    //facem clasa asta ca sa nu ai values aiurea prin applicatie, sa fie totul centralizat

    private boolean value;
    private String message;
    private int number;

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
