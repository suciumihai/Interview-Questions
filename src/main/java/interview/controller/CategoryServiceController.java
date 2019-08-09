package interview.controller;

import interview.dao.CategoryRepository;
import interview.dao.TemplateRepository;
import interview.model.Category;
import interview.model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CategoryServiceController {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value="/categories")
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    @RequestMapping(value="/categories/{id}")
    public Category getCategoryById(@PathVariable("id") String id){ return categoryRepository.findById(Long.valueOf(id)).get(); }

    @RequestMapping(value="/categories", method = RequestMethod.POST)
    public Category createCategory(@RequestBody Category category){
        return categoryRepository.save(category);
    }

    @RequestMapping(value="/categories/{id}", method = RequestMethod.PUT)
    public Category updateCategory(@PathVariable("id") String id, @RequestBody Category category){
        categoryRepository.deleteById(Long.parseLong(id));
        category.setId(Long.parseLong(id));
        categoryRepository.save(category);
        return category;
    }

    @RequestMapping(value="/categories/{id}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable("id") String id){
        categoryRepository.deleteById(Long.parseLong(id));
    }



    //not used for now
    @RequestMapping("/categoryNames")
    private List<String> getCategoryNames(){
        List<String> res = new ArrayList<>();
        for (Category c : categoryRepository.findAll()){
            res.add(c.getName());
        }
        return res;
    }

}
