package dao;

import model.CategoryTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryTemplateDao extends JpaRepository<CategoryTemplate, Long> {
}
