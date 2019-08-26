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
    private TestQuestionRepository testQuestionRepository;

    @Autowired
    private StartUpInit selfInject;


    @PostConstruct
    public void init(){
        selfInject.setup();
    }

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
        question1.getPossibleAnswers().addAll(q1PosAns);
        question1.getCorrectAnswers().addAll(q1CorAns);
        question1.setCategory(java);
        questionRepository.save(question1);
        testQuestionRepository.save(new TestQuestion(question1));

        Question question2 = new Question();
        question2.setName("q2");
        question2.setDifficulty("Easy");
        question2.setContent("O clasa abstracta nu se instantiaza");
        question2.getPossibleAnswers().addAll(q1PosAns);
        question2.getCorrectAnswers().addAll(q1CorAns);
        question2.setCategory(java);
        questionRepository.save(question2);
        testQuestionRepository.save(new TestQuestion(question2));

        Question question3 = new Question();
        question3.setName("q3");
        question3.setDifficulty("Medium");
        question3.setContent("select * iti da totu din tabel?");
        question3.getPossibleAnswers().addAll(q1PosAns);
        question3.getCorrectAnswers().addAll(q1CorAns);
        question3.setCategory(sql);
        questionRepository.save(question3);
        testQuestionRepository.save(new TestQuestion(question3));

        Question question4 = new Question();
        question4.setName("q4");
        question4.setDifficulty("Hard");
        question4.setContent("TO_DATE('yyyy-mm-dd', '2019-07-31') e corect");
        question4.getPossibleAnswers().addAll(q1PosAns);
        question4.getCorrectAnswers().addAll(q1CorAns);
        question4.setCategory(sql);
        questionRepository.save(question4);
        testQuestionRepository.save(new TestQuestion(question4));

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

        List<CategoryTemplate> catTemplates = new ArrayList<>();
        catTemplates.add(OneMedSql);
        catTemplates.add(TwoEasyJava);
        Set<CategoryTemplate> catTempl = new HashSet<>(catTemplates);

        Template ionTwoEasyJavaOneMedSql = new Template();
        ionTwoEasyJavaOneMedSql.setName("ionTwoEasyJavaOneMedSql");
        ionTwoEasyJavaOneMedSql.getCategoryTemplates().addAll(catTempl);
        for (CategoryTemplate categoryTemplate : catTempl) {
            categoryTemplate.setTemplate(ionTwoEasyJavaOneMedSql);
        }
        templateRepository.save(ionTwoEasyJavaOneMedSql);

        Test test1 = new Test();
        test1.setName("ionTwoEasyJavaOneMedSqltest");
        test1.setCandidate(ion);
        test1.setTemplate(ionTwoEasyJavaOneMedSql);
        testRepository.save(test1);

        Set<TestQuestion> testQuestions = new HashSet<>();
        List<CategoryTemplate> testCatTemplates = test1.getTemplate().getCategoryTemplates();

        for (CategoryTemplate testCatTemplate : testCatTemplates) {
            List<Question> questions = new ArrayList<>(questionRepository.findQuestByCategDiffi(testCatTemplate.getCategory(), testCatTemplate.getDifficulty()));
            int i = 0;
            while (i < testCatTemplate.getQuestionNumber()){
                Random rand = new Random();
                Question q = questions.get(rand.nextInt(questions.size()));
                TestQuestion tq = testQuestionRepository.getByName(q.getName());
                if (testQuestions.add(tq))
                    i++;
            }
        }

        test1.getTestQuestions().addAll(testQuestions);
        for (TestQuestion testQuestion : testQuestions) {
            testQuestion.setTest(test1);
            testQuestionRepository.save(testQuestion);
        }
        testQuestionRepository.flush();
        testRepository.save(test1);
    }
}
