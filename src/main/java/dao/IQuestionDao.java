package dao;

import model.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IQuestionDao extends JpaRepository<Question, Long> {

    // asta foloseste jap named queries, (nu native), mai exact jos avem u nmanual custom query
    @Query("SELECT q FROM Question WHERE LOWER(q.name) = LOWER(:name) or q.name = 'ce vreau eu'")
    Question retrieveByName(@Param("name") String name);

    // puteam ave si un auto custom query
    // Question findByName(String name);


}
