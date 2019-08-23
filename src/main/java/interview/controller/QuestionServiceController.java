package interview.controller;

import interview.dao.QuestionRepository;
import interview.dao.TemplateRepository;
import interview.model.DTO.QuestionDto;
import interview.model.Question;
import interview.model.Template;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class QuestionServiceController {

    @Autowired
    private QuestionRepository repo;

    @Autowired
    private DozerBeanMapper modelMapper;

    private QuestionDto convertToDto(Question entity){
        QuestionDto dto = modelMapper.map(entity, QuestionDto.class);
        return dto;
    }

    private Question convertToEntity(QuestionDto dto) {
        Question entity = modelMapper.map(dto, Question.class);
        return entity;
    }

    @RequestMapping(value="/questions")
    public List<QuestionDto> get(){
        return repo.findAll().stream().map(question -> convertToDto(question)).collect(Collectors.toList());
    }

    @RequestMapping(value="/questions/{id}")
    public QuestionDto getById(@PathVariable("id") String id){
        return convertToDto(repo.findById(Long.valueOf(id)).get()); }

    @RequestMapping(value="/questions", method = RequestMethod.POST)
    public QuestionDto create(@RequestBody QuestionDto body){
        Question entity = convertToEntity(body);
        Question created = repo.save(entity);
        return convertToDto(created);
    }

    @RequestMapping(value="/questions/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") String id, @RequestBody QuestionDto body){
        Question entity = convertToEntity(body);
        Question existing = repo.findById(Long.valueOf(id)).get();
        existing.setCategory(entity.getCategory());
        existing.setContent(entity.getContent());
        existing.setDifficulty(entity.getDifficulty());
        existing.setName(entity.getName());
        existing.getPossibleAnswers().clear();
        existing.getPossibleAnswers().addAll(entity.getPossibleAnswers());
        existing.getCorrectAnswers().clear();
        existing.getCorrectAnswers().addAll(entity.getCorrectAnswers());
        repo.save(existing);
    }

    @RequestMapping(value="/questions/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id){
        repo.deleteById(Long.parseLong(id));
    }

//    @RequestMapping(value="/questions")
//    public List<Question> get(){
//        return repo.findAll();
//    }
//
//    @RequestMapping(value="/questions/{id}")
//    public Question getById(@PathVariable("id") String id){ return repo.findById(Long.valueOf(id)).get(); }
//
//    @RequestMapping(value="/questions", method = RequestMethod.POST)
//    public Question create(@RequestBody Question body){
//        return repo.save(body);
//    }
//
//    @RequestMapping(value="/questions/{id}", method = RequestMethod.PUT)
//    public Question update(@PathVariable("id") String id, @RequestBody Question body){
//        repo.deleteById(Long.parseLong(id));
//        body.setId(Long.parseLong(id));
//        repo.save(body);
//        return body;
//    }

}

