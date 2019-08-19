package interview;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import interview.dao.*;
import interview.model.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;

import org.h2.tools.Server;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class StartUpInit {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryTemplateRepository categoryTemplateRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TemplateRepository templateRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StartUpInit selfInject;

    //PostConstruct defines a method as initialization method of a spring bean which runs after dependency injection is completed
    @PostConstruct
    public void init(){
        selfInject.setup();
    }

    //nu merge transactional cu postconstruct
    @Transactional
    public void setup() {

        Candidate ion = new Candidate();
        ion.setName("Ion Gheorghe");
        ion.setEmail("ion.gheorghe@ceva.com");
        candidateRepository.save(ion);

        Candidate candidate2 = new Candidate();
        candidate2.setName("Alex dan");
        candidate2.setEmail("alex.dan@ceva.com");
        candidateRepository.save(candidate2);

        User user1 = new User();
        user1.setEmail("hr@ceva.com");
        user1.setRole("HR");
        userRepository.save(user1);

        User user2 = new User();
        user2.setEmail("questionAdmin@ceva.com");
        user2.setRole("questionAdmin");
        userRepository.save(user2);

        Category java = new Category();
        java.setName("Java");
        categoryRepository.save(java);

        Category sql = new Category();
        sql.setName("SQL");
        categoryRepository.save(sql);

        List<String> q1CorAns = new ArrayList<>();
        List<String> q1PosAns = new ArrayList<>();

        q1CorAns.add("Da");

        q1PosAns.add("Da");
        q1PosAns.add("Nu");
        q1PosAns.add("Poate");

        Question question1 = new Question();
        question1.setName("q1");
        question1.setDifficulty("Easy");
        question1.setContent("este java OOP?");
        question1.setCorrectAnswers(q1CorAns);
        question1.setPossibleAnswers(q1PosAns);
        question1.setCategory(java);
        questionRepository.save(question1);

        Question question2 = new Question();
        question2.setName("q2");
        question2.setDifficulty("Easy");
        question2.setContent("O clasa abstracta nu se instantiaza");
        question2.setCorrectAnswers(q1CorAns);
        question2.setPossibleAnswers(q1PosAns);
        question2.setCategory(java);
        questionRepository.save(question2);

        Question question3 = new Question();
        question3.setName("q3");
        question3.setDifficulty("Medium");
        question3.setContent("select * iti da totu din tabel?");
        question3.setCorrectAnswers(q1CorAns);
        question3.setPossibleAnswers(q1PosAns);
        question3.setCategory(sql);
        questionRepository.save(question3);

        Question question4 = new Question();
        question4.setName("q4");
        question4.setDifficulty("Hard");
        question4.setContent("TO_DATE('yyyy-mm-dd', '2019-07-31') e corect");
        question4.setCorrectAnswers(q1CorAns);
        question4.setPossibleAnswers(q1PosAns);
        question4.setCategory(sql);
        questionRepository.save(question4);

        //now that we have questions, we cam make a cat template
        CategoryTemplate TwoEasyJava = new CategoryTemplate();
        TwoEasyJava.setName("2 easy java");
        TwoEasyJava.setCategory(java);
        TwoEasyJava.setQuestionNumber(2);
        TwoEasyJava.setDifficulty("Easy");
        categoryTemplateRepository.save(TwoEasyJava);

        CategoryTemplate OneMedSql = new CategoryTemplate();
        OneMedSql.setName("1 med sql");
        OneMedSql.setCategory(sql);
        OneMedSql.setQuestionNumber(1);
        OneMedSql.setDifficulty("Medium");
        categoryTemplateRepository.save(OneMedSql);

        //now we can make a template
        List<CategoryTemplate> catTemplates = new ArrayList<>();
        catTemplates.add(OneMedSql);
        catTemplates.add(TwoEasyJava);
        Set<CategoryTemplate> catTempl = new HashSet<>(catTemplates);
        //daca vreau sa le sortez imi trebuie treeSet

        Template ionTwoEasyJavaOneMedSql = new Template();
        ionTwoEasyJavaOneMedSql.setName("ionTwoEasyJavaOneMedSql");
        ionTwoEasyJavaOneMedSql.getCategoryTemplates().addAll(catTempl);
        for (CategoryTemplate categoryTemplate : catTempl) {
            categoryTemplate.setTemplate(ionTwoEasyJavaOneMedSql);
        }


        //trying with object Mapper, does it really do ANYTHING?????
        //ALSO, DO I NEED to mark services with @TRANSACTIONAL????
        //ObjectMapper mapper = new ObjectMapper();
        //String jsonArray = mapper.writeValueAsString(catTemplates);

        //List<CategoryTemplate> asList = mapper.readValue(
         //       jsonArray, new TypeReference<List<CategoryTemplate>>() { });

        //ionTwoEasyJavaOneMedSql.setCategoryTemplates(asList);

        //in this template, we, or a service, should create a list of questions
        List<Question> templateQuestions = new ArrayList<>();
        //in functie de nr de intrebari, si categoria din template catgory, se adauga in lista intrebari. ar trebui sa dea
        templateQuestions.add(question1);
        templateQuestions.add(question2);
        templateQuestions.add(question3);
        Set<Question> templQuest = new HashSet<>(templateQuestions);
        //ionTwoEasyJavaOneMedSql.getQuestions().addAll(templQuest);

        //templateRepository.flush();

        templateRepository.save(ionTwoEasyJavaOneMedSql);

        //Hibernate.initialize(ionTwoEasyJavaOneMedSql.getCategoryTemplates());
        System.out.println("idu este : " + ionTwoEasyJavaOneMedSql.getId());
        int forceLoadSizeCat = templateRepository.getOne(ionTwoEasyJavaOneMedSql.getId()).getCategoryTemplates().size();
        System.out.println("forceLoadSizeCat = " + forceLoadSizeCat);

        //int forceLoadSizeQue = templateRepository.getOne(ionTwoEasyJavaOneMedSql.getId()).getQuestions().size();
        //System.out.println("forceLoadSizeQue = " + forceLoadSizeQue);

        //INTRISTING: ca sa mearga asta, mi-a trebuit @Proxy(lazy=false) la Template, si fetchType=EAger in catTempaltes din TEmplate, ca altfe; failed to lazily initialize a collection of role.
        //Mai mult, daca bag si getchType eager la questions, iti da cannot simultaneously fetch multiple bags. asa ca schimbi List in Set... csf, nai csf
        //hibernate foloseste bags, care sunt unordered lists. si cam prefera set daca sunt mai multe, pentru unicitate..
        //chiar si asa, size imi da 0. WHY?????????????????????????????????????????????????????????????????????????????????????????????????????

//        String forceLoadCatTempl = templateRepository.getOne(ionTwoEasyJavaOneMedSql.getId()).getCategoryTemplates().get(0).getName();
//        System.out.println(forceLoadCatTempl);
//        String forceLoadQuest = templateRepository.getOne(ionTwoEasyJavaOneMedSql.getId()).getQuestions().get(0).getName();
        //astea failuiesc oricum...


        //ordinea ar fi intri, dai new test, el asocieaza ar trebui sa dea new template, si template
        Test test1 = new Test();
        test1.setName("ionTwoEasyJavaOneMedSqltest");
        test1.setCandidate(ion);
        test1.setTemplate(ionTwoEasyJavaOneMedSql);

        //se creaza o lista de intrebari de test
        List<String> testAnswers = new ArrayList<>();
        testAnswers.add("Da");

        //acum, iau si bag answers in intrebarie din template, da asta era can aveam list. la set aparent mei greu sa iei un elem????????????????
//        test1.getTemplate().getQuestions().get(0).setSelectedAnswers(testAnswers);
//        test1.getTemplate().getQuestions().get(1).setSelectedAnswers(testAnswers);
//        System.out.println(test1.getTemplate().getQuestions().size());
//        test1.getTemplate().getQuestions().get(2).setSelectedAnswers(testAnswers);

        //se cauta cate intreb din testQuestions au corretAnswers.equal(selectdAnswers), si se seteaza o nota

        test1.setNota("100");
        testRepository.save(test1);

    }
}
