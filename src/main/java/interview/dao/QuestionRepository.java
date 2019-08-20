package interview.dao;


import interview.model.Question;

import org.springframework.data.jpa.repository.JpaRepository;



public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionRepositoryCustom {

    // asta foloseste jap named queries, (nu native), mai exact jos avem u nmanual custom query
    //@Query("SELECT q FROM Question WHERE LOWER(q.name) = LOWER(:name) or q.name = 'ce vreau eu'")
    //Question retrieveByName(@Param("name") String name);

    // puteam ave si un auto custom query
    // Question findByName(String name);


}
