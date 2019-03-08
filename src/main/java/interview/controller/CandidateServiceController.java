package interview.controller;

import interview.dao.CandidateRepository;
import interview.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//addign crossorigin requests to deploy the AngularJS front-end application separately than the REST API
//RESTful web services. It serves JSON, XML and custom response. This is the interview.controller class file that contains GET, POST, PUT methods REST Endpoint
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CandidateServiceController {

    @Autowired
    private CandidateRepository candidateRepository;

    @RequestMapping(value = "/candidates") //default e get
    public List<Candidate> getCandidates(){
//        Candidate candidate = new Candidate();
//        candidate.setName("ion");
//        candidate.setSurName("maria");
//        candidate.setEmail("ion.maria@pix.com");
//        candidate.setPhone("1234123123");
//        candidateRepository.save(candidate);
        return candidateRepository.findAll();
    }

    @RequestMapping(value = "/candidates", method = RequestMethod.POST)
    public Candidate createCandidate(@RequestBody Candidate candidate){
        return candidateRepository.save(candidate);
    }

    @RequestMapping(value = "/candidates/{id}", method = RequestMethod.PUT)
    public Candidate updateCandidate(@PathVariable("id") String id, @RequestBody Candidate candidate){
        ////teorietci aici porneste tranzactie business, caci proxyl vede ca ii va trebui o tranzactie cu baza de date
        candidateRepository.deleteById(Long.parseLong(id));
        candidate.setId(Long.parseLong(id));
        candidateRepository.save(candidate);
        return candidate;
        //// teoretci aici vede daca toate tranzactiile cu baza de date au focatu comit ok, si atunci se termina si busines sranzaction
    }

    @RequestMapping(value = "/candidates/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        candidateRepository.deleteById(Long.parseLong(id));
    }

//    aici sunt testele pentru servicuil. mapl ala putea sa fie static, dar repositoryul meu nu merge sa fie static. nu se fac injectii, auowired statice
//    private static Map<String, Candidate> candidateRepo = new HashMap<>(); //asta l-am folosit sa testez daor serviciul. mai jos este codul comentat penru el
//
//    //doar o initializare statica, sa am ce sa vad in get candidates
//    static {
//        Candidate candidate1 = new Candidate();
//        candidate1.setName("ion");
//        candidate1.setId(10L); //tre sa il setez eu, caci ttez doar repo si serviciu doecamdata, nu am dat repo.save, deci nu am persisteat nimic in db cu hibernate deocamdata
//        candidateRepo.put(candidate1.getId().toString(), candidate1);
//
//
//        Candidate candidate2 = new Candidate();
//        candidate2.setName("maria");
//        candidate2.setId(20L);
//        candidateRepo.put(candidate2.getId().toString(), candidate2);
//    }
//
//    //define the HTTP GET request method. it does not require any Request Body. You can send request parameters and path variables to define the custom or dynamic URL
//    @RequestMapping(value = "/candidates")
//    public ResponseEntity<Object> getCandidates(){
//        return new ResponseEntity<Object>(candidateRepo.values(), HttpStatus.OK);
//    }
//    //http://localhost:8080/candidates
//
//    //HTTP POST request is used to create a resource, and it containts a request body
//    @RequestMapping(value = "/candidates", method = RequestMethod.POST)
//    public ResponseEntity<Object> createCandidate(@RequestBody Candidate candidate) {
//        candidateRepo.put(candidate.getId().toString(), candidate);
//        return new ResponseEntity<Object>("candidate is created successfully", HttpStatus.CREATED);
//    }
//
//    //HTTP PUT request is used to update the existing resource. This method contains a Request Body
//    @RequestMapping(value = "/candidates/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<Object> updateCandidate(@PathVariable("id") String id, @RequestBody Candidate candidate) {
//        candidateRepo.remove(id);
//        candidate.setId(Long.parseLong(id));
//        candidateRepo.put(id, candidate);
//        return new ResponseEntity<Object>("candidate is updated successsfully", HttpStatus.OK);
//    }
//
//    //u know what this is
//    @RequestMapping(value = "/candidates/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<Object> deleteCandidate(@PathVariable("id") String id) {
//        candidateRepo.remove(id);
//        return new ResponseEntity<Object>("Candidate is deleted successsfully", HttpStatus.OK);
//    }

}
