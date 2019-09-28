package interview.controller;

import interview.dao.*;
import interview.model.*;
import interview.model.DTO.TestDto;
import interview.model.DTO.TestQuestionDto;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class TestServiceController {

    @Autowired
    private TestRepository repo;
    @Autowired
    private TestQuestionRepository testQuestionRepository;

    @Autowired
    private DozerBeanMapper modelMapper;

    private TestDto convertToDto(Test entity){
        TestDto dto = modelMapper.map(entity, TestDto.class);
        return dto;
    }

    private TestQuestion convertToEntityTestQ(TestQuestionDto dto) {
        TestQuestion entity = modelMapper.map(dto, TestQuestion.class);
        return entity;
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
        repo.save(entity);

        Set<TestQuestion> testQuestions = new HashSet<>();
        List<CategoryTemplate> categoryTemplates = entity.getTemplate().getCategoryTemplates();

        for (CategoryTemplate categoryTemplate : categoryTemplates) {
            List<TestQuestion> questions = new ArrayList<>(testQuestionRepository.findQuestByCategDiffi(categoryTemplate.getCategory().getName(),categoryTemplate.getDifficulty()));
            int i = 0;
            while (i < categoryTemplate.getQuestionNumber()) {
                Random rand = new Random();
                TestQuestion q = questions.get(rand.nextInt(questions.size()));
                q.setTest(entity);
                testQuestionRepository.save(q);
                if (testQuestions.add(q))
                        i++;
            }
        }
        testQuestionRepository.flush();
        entity.getTestQuestions().addAll(testQuestions);
        Test created = repo.save(entity);
        return convertToDto(created);
    }

    @RequestMapping(value="/tests/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") String id, @RequestBody TestDto body){
        Test entity = convertToEntity(body);
        Test existing = repo.findById(Long.valueOf(id)).get();
        Set<TestQuestion> testQs = new HashSet<>();
        for (TestQuestion testQ : entity.getTestQuestions()){
            testQuestionRepository.getByName(testQ.getQuestionReusable().getName()).getSelectedAnswers().addAll(testQ.getSelectedAnswers());
        }
        repo.save(existing);

        List<String> corAns = new ArrayList<>();
        List<String> selAns = new ArrayList<>();
        List<TestQuestion> tQs = new ArrayList<>(existing.getTestQuestions());

        for (TestQuestion tQ : tQs) {
            corAns.addAll(tQ.getCorrectAnswers());
            selAns.addAll(tQ.getSelectedAnswers());
        }

        if (corAns.equals(selAns))
            existing.setNota("100");
        repo.save(existing);
        System.out.println(existing.getNota());
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
