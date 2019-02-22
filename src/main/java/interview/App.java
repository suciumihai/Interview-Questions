package interview;

import interview.config.JpaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.*;

@SpringBootApplication
public class App {
//app e thread de start. el porneste springboot. el face start server (cu 1 pool threads), apoi deploy app.
    //cand vii cu 1 thread cu http request la server. el ia alt server pentru get. el ar cere o metoda din contianerul, care e aplicatia de ployata.
    //tranz business incepe cand can threadul cu get vrea sa execute o metoda din bean.
    //in cazul asta e candidateServCtroller. container face proxy la serviceController, si pe ala il da threadului cu get.
    //prin tranzactia de business, mmetoda m' din proxy poate s afaca metoda m din bean.
    //si aia va duce la o metoda din dao, care de fact tot u nproxy este , impreuna cu tranzactia de business.
    //ejb porneste mereu tranzactii de business, e super eager. spring mai asteapa, vede daca oai o tranzactie cu baza de date, si atunci poate face una de business, nu sti exact
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
