package interview.controller;

import interview.dao.TestQuestionRepository;
import interview.model.TestQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class TestQuestionServiceController {

    @Autowired
    private TestQuestionRepository repo;

    @RequestMapping(value="/testQuestions")
    public List<TestQuestion> get(){
        final List<TestQuestion> all = repo.findAll();
        return all;
    }

    @RequestMapping(value="/testQuestions/{id}")
    public TestQuestion getById(@PathVariable("id") String id){ return repo.findById(Long.valueOf(id)).get(); }

    @RequestMapping(value="/testQuestions", method = RequestMethod.POST)
    public TestQuestion create(@RequestBody TestQuestion body){
        return repo.save(body);
    }

    @RequestMapping(value="/testQuestions/{id}", method = RequestMethod.PUT)
    public TestQuestion update(@PathVariable("id") String id, @RequestBody TestQuestion body){
        repo.deleteById(Long.parseLong(id));
        body.setId(Long.parseLong(id));
        repo.save(body);
        return body;
    }

    @RequestMapping(value="/testQuestions/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id){
        repo.deleteById(Long.parseLong(id));
    }
}
