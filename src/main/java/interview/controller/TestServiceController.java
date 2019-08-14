package interview.controller;

import interview.dao.QuestionRepository;
import interview.dao.TemplateRepository;
import interview.dao.TestRepository;
import interview.model.Question;
import interview.model.Template;
import interview.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@Transactional
public class TestServiceController {

    @Autowired
    private TestRepository repo;

    @RequestMapping(value="/tests")
    public List<Test> get(){
        return repo.findAll();
    }

    @RequestMapping(value="/tests/{id}")
    public Test getById(@PathVariable("id") String id){ return repo.findById(Long.valueOf(id)).get(); }

    @RequestMapping(value="/tests", method = RequestMethod.POST)
    public Test create(@RequestBody Test body){
        return repo.save(body);
    }

    @RequestMapping(value="/tests/{id}", method = RequestMethod.PUT)
    public Test update(@PathVariable("id") String id, @RequestBody Test body){
        repo.deleteById(Long.parseLong(id));
        body.setId(Long.parseLong(id));
        repo.save(body);
        return body;
    }

    @RequestMapping(value="/tests/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id){
        repo.deleteById(Long.parseLong(id));
    }
}
