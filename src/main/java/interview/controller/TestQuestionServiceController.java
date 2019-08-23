package interview.controller;

import interview.dao.TestQuestionRepository;
import interview.model.DTO.TestQuestionDto;
import interview.model.Question;
import interview.model.TestQuestion;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class TestQuestionServiceController {

    @Autowired
    private TestQuestionRepository repo;

    @Autowired
    private DozerBeanMapper modelMapper;

    private TestQuestionDto convertToDto(TestQuestion entity){
        TestQuestionDto dto = modelMapper.map(entity, TestQuestionDto.class);
        return dto;
    }

    private TestQuestion convertToEntity(TestQuestionDto dto) {
        TestQuestion entity = modelMapper.map(dto, TestQuestion.class);
        return entity;
    }

    @RequestMapping(value="/testQuestions")
    public List<TestQuestionDto> get(){
        return repo.findAll().stream().map(testQuestion -> convertToDto(testQuestion)).collect(Collectors.toList());
    }

    @RequestMapping(value="/testQuestions/{id}")
    public TestQuestionDto getById(@PathVariable("id") String id){
        return convertToDto(repo.findById(Long.valueOf(id)).get()); }

    @RequestMapping(value="/testQuestions", method = RequestMethod.POST)
    public TestQuestionDto create(@RequestBody TestQuestionDto body){
        TestQuestion entity = convertToEntity(body);
        TestQuestion created = repo.save(entity);
        return convertToDto(created);
    }

    @RequestMapping(value="/testQuestions/{id}", method = RequestMethod.PUT)
    public TestQuestionDto update(@PathVariable("id") String id, @RequestBody TestQuestionDto body){
        TestQuestion entity = convertToEntity(body);
        repo.deleteById(Long.parseLong(id));
        entity.setId(Long.parseLong(id));
        TestQuestion created = repo.save(entity);
        return convertToDto(created);
    }

    @RequestMapping(value="/testQuestions/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id){
        repo.deleteById(Long.parseLong(id));
    }

//    @RequestMapping(value="/testQuestions")
//    public List<TestQuestion> get(){
//        final List<TestQuestion> all = repo.findAll();
//        return all;
//    }
//
//    @RequestMapping(value="/testQuestions/{id}")
//    public TestQuestion getById(@PathVariable("id") String id){ return repo.findById(Long.valueOf(id)).get(); }
//
//    @RequestMapping(value="/testQuestions", method = RequestMethod.POST)
//    public TestQuestion create(@RequestBody TestQuestion body){
//        return repo.save(body);
//    }
//
//    @RequestMapping(value="/testQuestions/{id}", method = RequestMethod.PUT)
//    public TestQuestion update(@PathVariable("id") String id, @RequestBody TestQuestion body){
//        repo.deleteById(Long.parseLong(id));
//        body.setId(Long.parseLong(id));
//        repo.save(body);
//        return body;
//    }
}
