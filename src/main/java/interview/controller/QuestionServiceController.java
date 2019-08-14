package interview.controller;

import interview.dao.QuestionRepository;
import interview.dao.TemplateRepository;
import interview.model.Question;
import interview.model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@Transactional
public class QuestionServiceController {

    @Autowired
    private QuestionRepository repo;

    @RequestMapping(value="/questions")
    public List<Question> get(){
        return repo.findAll();
    }

    @RequestMapping(value="/questions/{id}")
    public Question getById(@PathVariable("id") String id){ return repo.findById(Long.valueOf(id)).get(); }

    @RequestMapping(value="/questions", method = RequestMethod.POST)
    public Question create(@RequestBody Question body){
        return repo.save(body);
    }

    @RequestMapping(value="/questions/{id}", method = RequestMethod.PUT)
    public Question update(@PathVariable("id") String id, @RequestBody Question body){
        repo.deleteById(Long.parseLong(id));
        body.setId(Long.parseLong(id));
        repo.save(body);
        return body;
    }

    @RequestMapping(value="/questions/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id){
        repo.deleteById(Long.parseLong(id));
    }


    //not necessary for now
    @RequestMapping(value="/questCatName/{id}")
    public String getQuestCatNameById(@PathVariable("id") String id) {
        return repo.findById(Long.valueOf(id)).get().getCategory().getName();
    }
}

