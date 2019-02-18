package controller;

import model.Candidate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//RESTful web services. It serves JSON, XML and custom response. This is the controller class file that contains GET, POST, PUT methods REST Endpoint
@RestController
public class CandidateServiceController {

    private static Map<String, Candidate> candidateRepo = new HashMap<>();
    //doar o initializare statica, sa am ce sa vad in get candidates
    static {
        Candidate candidate1 = new Candidate();
        candidate1.setName("ion");
        candidateRepo.put(candidate1.getId().toString(), candidate1);

        Candidate candidate2 = new Candidate();
        candidate1.setName("maria");
        candidateRepo.put(candidate2.getId().toString(), candidate2);
    }

    //define the HTTP GET request method. it does not require any Request Body. You can send request parameters and path variables to define the custom or dynamic URL
    @RequestMapping(value = "/candidates")
    public ResponseEntity<Object> getCandidates(){
        return new ResponseEntity<Object>(candidateRepo.values(), HttpStatus.OK);
    }

    //HTTP POST request is used to create a resource, and it containts a request body
    @RequestMapping(value = "/candidates", method = RequestMethod.POST)
    public ResponseEntity<Object> createCandidate(@RequestBody Candidate candidate) {
        candidateRepo.put(candidate.getId().toString(), candidate);
        return new ResponseEntity<Object>("candidate is created successfully", HttpStatus.CREATED);
    }

    //HTTP PUT request is used to update the existing resource. This method contains a Request Body
    @RequestMapping(value = "/candidates/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCandidate(@PathVariable("id") String id, @RequestBody Candidate candidate) {
        candidateRepo.remove(id);
        candidate.setId(Long.parseLong(id));
        candidateRepo.put(id, candidate);
        return new ResponseEntity<Object>("candidate is updated successsfully", HttpStatus.OK);
    }

    //u know what this is
    @RequestMapping(value = "/candidates/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteCandidate(@PathVariable("id") String id) {
        candidateRepo.remove(id);
        return new ResponseEntity<Object>("Candidate is deleted successsfully", HttpStatus.OK);
    }

}
