package interview.dao;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import interview.config.JpaConfig;
import interview.model.*;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfig.class})
@Transactional
public class InMemoryDBIntegrationTest {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CategoryTemplateRepository categoryTemplateRepository;

    @Autowired
    private TemplateRepository templateRepository;

    private static final Long ID = 1L;
    private static final String NAME = "john";

    @Test
    public void givenCandidate_whenSave_thenGetOk() {
        Candidate candidate1 = new Candidate();
        candidate1.setName(NAME);
        //candidate1.setId(ID);
        //aici cu save l-am pus pe hibernate sa persiste
        candidateRepository.save(candidate1);

        Candidate candidate2 = candidateRepository.getOne(candidate1.getId());

        assertEquals("name incorrect", NAME, candidate2.getName());
    }

    @Test
    public void getCatNameByQuestId() {
        Category java = new Category();
        java.setName("Java");
        categoryRepository.save(java);

        Question question1 = new Question();
        question1.setName("q1");
        question1.setDifficulty("Easy");
        question1.setContent("este java OOP?");
        question1.setCategory(java);
        questionRepository.save(question1);

        assertEquals("Java", questionRepository.findById(Long.valueOf(question1.getId())).get().getCategory().getName());
    }

    @Test
    public void listOfCatTemplates() {

        Category java = new Category();
        java.setName("Java");
        categoryRepository.save(java);

        Category sql = new Category();
        sql.setName("SQL");
        categoryRepository.save(sql);

        List<String> q1CorAns = new ArrayList<>();
        List<String> q1PosAns = new ArrayList<>();
        q1CorAns.add("Nu");
        q1CorAns.add("Poate");
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

        Template ionTwoEasyJavaOneMedSql = new Template();
        ionTwoEasyJavaOneMedSql.setCategoryTemplates(catTempl);
        ionTwoEasyJavaOneMedSql.setName("ionTwoEasyJavaOneMedSql");

        //in this template, we, or a service, should create a list of questions
        List<Question> templateQuestions = new ArrayList<>();
        //in functie de nr de intrebari, si categoria din template catgory, se adauga in lista intrebari. ar trebui sa dea
        templateQuestions.add(question1);
        templateQuestions.add(question2);
        templateQuestions.add(question3);

        Set<Question> tempQuest = new HashSet<>(templateQuestions);
        ionTwoEasyJavaOneMedSql.setQuestions(tempQuest);
        templateRepository.save(ionTwoEasyJavaOneMedSql);

//        assertEquals("q1", templateRepository.getOne(ionTwoEasyJavaOneMedSql.getId()).getQuestions().get(0).getName());
//        assertEquals("SQL", templateRepository.getOne(ionTwoEasyJavaOneMedSql.getId()).getCategoryTemplates().get(0).getCategory().getName());

        //e greu sa testezi asa, caci HashSet nu iti garanteaza ordinea
        assertThat(templateRepository.getOne(ionTwoEasyJavaOneMedSql.getId()).getCategoryTemplates().iterator().next().getName()).isIn("1 med sql", "2 easy java");
        assertThat(templateRepository.getOne(ionTwoEasyJavaOneMedSql.getId()).getCategoryTemplates().iterator().next().getCategory().getName()).isIn("Java", "SQL");
        assertThat(templateRepository.getOne(ionTwoEasyJavaOneMedSql.getId()).getQuestions().stream().findFirst().get().getName()).isIn("q1", "q2", "q3", "q4");

    }

    @Test
    public void givenJsonArray_whenDeserializingAsListWithTypeReferenceHelp_thenCorrect()
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper mapper = new ObjectMapper();

        Category java = new Category();
        java.setName("Java");
        categoryRepository.save(java);

        Category sql = new Category();
        sql.setName("SQL");
        categoryRepository.save(sql);

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

        List<CategoryTemplate> listOfCategoryTemplates = Lists.newArrayList(OneMedSql, TwoEasyJava);
        String jsonArray = mapper.writeValueAsString(listOfCategoryTemplates);

        List<CategoryTemplate> asList = mapper.readValue(
                jsonArray, new TypeReference<List<CategoryTemplate>>() { });
       //assertThat(asList.get(0), instanceOf(CategoryTemplate.class));
       assertEquals(asList.get(0).getName(), OneMedSql.getName());
    }
}