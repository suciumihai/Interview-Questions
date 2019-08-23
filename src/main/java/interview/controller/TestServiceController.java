package interview.controller;

import interview.dao.QuestionRepository;
import interview.dao.TemplateRepository;
import interview.dao.TestRepository;
import interview.model.DTO.TestDto;
import interview.model.Question;
import interview.model.Template;
import interview.model.Test;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class TestServiceController {

    @Autowired
    private TestRepository repo;

    @Autowired
    private DozerBeanMapper modelMapper;

    private TestDto convertToDto(Test entity){
        TestDto dto = modelMapper.map(entity, TestDto.class);
        return dto;
    }

    private Test convertToEntity(TestDto dto) {
        Test entity = modelMapper.map(dto, Test.class);
        return entity;
    }

    @RequestMapping(value="/tests")
    public List<TestDto> get(){
        return repo.findAll().stream().map(test -> convertToDto(test)).collect(Collectors.toList());
    }

    @RequestMapping(value="/tests/{id}")
    public TestDto getById(@PathVariable("id") String id){
        return convertToDto(repo.findById(Long.valueOf(id)).get()); }

    @RequestMapping(value="/tests", method = RequestMethod.POST)
    public TestDto create(@RequestBody TestDto body){
        Test entity = convertToEntity(body);
        Test created = repo.save(entity);
        return convertToDto(created);
    }

    @RequestMapping(value="/tests/{id}", method = RequestMethod.PUT)
    public TestDto update(@PathVariable("id") String id, @RequestBody TestDto body){
        Test entity = convertToEntity(body);
        repo.deleteById(Long.parseLong(id));
        entity.setId(Long.parseLong(id));
        Test created = repo.save(entity);
        return convertToDto(created);
    }

    @RequestMapping(value="/tests/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id){
        repo.deleteById(Long.parseLong(id));
    }

//    @RequestMapping(value="/tests")
//    public List<Test> get(){
//        final List<Test> all = repo.findAll();
//        return all;
//    }
//
//    @RequestMapping(value="/tests/{id}")
//    public Test getById(@PathVariable("id") String id){ return repo.findById(Long.valueOf(id)).get(); }
//
//    @RequestMapping(value="/tests", method = RequestMethod.POST)
//    public Test create(@RequestBody Test body){
//        return repo.save(body);
//    }
//
//    @RequestMapping(value="/tests/{id}", method = RequestMethod.PUT)
//    public Test update(@PathVariable("id") String id, @RequestBody Test body){
//        repo.deleteById(Long.parseLong(id));
//        body.setId(Long.parseLong(id));
//        repo.save(body);
//        return body;
//    }
}
