package dao;

import model.CategoryTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryTemplateRepository extends JpaRepository<CategoryTemplate, Long> {
}
