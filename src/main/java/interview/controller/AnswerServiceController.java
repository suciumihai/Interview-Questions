package interview.controller;

import interview.dao.AnswerRepository;
import interview.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@Transactional
public class AnswerServiceController {

    @Autowired
    private AnswerRepository answerRepository;

    @RequestMapping(value="/answers")
    public List<Answer> getAnswers(){
        return answerRepository.findAll();
    }

    @RequestMapping(value="/answers/{id}")
    public Answer getAnswerById(@PathVariable("id") String id){ return answerRepository.findById(Long.valueOf(id)).get(); }

    @RequestMapping(value="/answers", method = RequestMethod.POST)
    public Answer createAnswer(@RequestBody Answer answer){
        return answerRepository.save(answer);
    }

    @RequestMapping(value="/answers/{id}", method = RequestMethod.PUT)
    public Answer updateAnswer(@PathVariable("id") String id, @RequestBody Answer answer){
        answerRepository.deleteById(Long.parseLong(id));
        answer.setId(Long.parseLong(id));
        answerRepository.save(answer);
        return answer;
    }

    @RequestMapping(value="/answers/{id}", method = RequestMethod.DELETE)
    public void deleteAnswer(@PathVariable("id") String id){
        answerRepository.deleteById(Long.parseLong(id));
    }
}
