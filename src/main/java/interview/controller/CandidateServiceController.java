package interview.controller;

import interview.dao.CandidateRepository;
import interview.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CandidateServiceController {

    @Autowired
    private CandidateRepository candidateRepository;

    @RequestMapping(value = "/candidates")
    public List<Candidate> getCandidates(){
        return candidateRepository.findAll();
    }

    @RequestMapping(value="/candidates/{id}")
    public Candidate getCandidateById(@PathVariable("id") String id) {
        return candidateRepository.findById(Long.valueOf(id)).get();

    }



    @RequestMapping(value = "/candidates", method = RequestMethod.POST)
    public Candidate createCandidate(@RequestBody Candidate candidate){
        return candidateRepository.save(candidate);
    }

    @RequestMapping(value = "/candidates/{id}", method = RequestMethod.PUT)
    public Candidate updateCandidate(@PathVariable("id") String id, @RequestBody Candidate candidate){
        candidateRepository.deleteById(Long.parseLong(id));
        candidate.setId(Long.parseLong(id));
        candidateRepository.save(candidate);
        return candidate;
    }

    @RequestMapping(value = "/candidates/{id}", method = RequestMethod.DELETE)
    public void deleteCandidate(@PathVariable("id") String id) {
        candidateRepository.deleteById(Long.parseLong(id));
    }



}
