package interview.controller;

import interview.dao.CategoryTemplateRepository;
import interview.dao.TemplateRepository;
import interview.model.Category;
import interview.model.CategoryTemplate;
import interview.model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CategoryTemplateServiceController {

    @Autowired
    private CategoryTemplateRepository categoryTemplateRepository;

    @RequestMapping(value="/categoryTemplates")
    public List<CategoryTemplate> getCategoryTemplates(){
        return categoryTemplateRepository.findAll();
    }

    @RequestMapping(value="/categoryTemplates/{id}")
    public CategoryTemplate getCategoryTemplateById(@PathVariable("id") String id){ return categoryTemplateRepository.findById(Long.valueOf(id)).get(); }

    @RequestMapping(value="/categoryTemplates", method = RequestMethod.POST)
    public CategoryTemplate createCategoryTemplate(@RequestBody CategoryTemplate categoryTemplate){
        return categoryTemplateRepository.save(categoryTemplate);
    }

    @RequestMapping(value="/categoryTemplates/{id}", method = RequestMethod.PUT)
    public CategoryTemplate updateCategoryTemplate(@PathVariable("id") String id, @RequestBody CategoryTemplate categoryTemplate){
        categoryTemplateRepository.deleteById(Long.parseLong(id));
        categoryTemplate.setId(Long.parseLong(id));
        categoryTemplateRepository.save(categoryTemplate);
        return categoryTemplate;
    }

    @RequestMapping(value="/categoryTemplates/{id}", method = RequestMethod.DELETE)
    public void deleteCategoryTemplate(@PathVariable("id") String id){
        categoryTemplateRepository.deleteById(Long.parseLong(id));
    }
}

