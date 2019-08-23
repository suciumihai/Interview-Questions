package interview.controller;

import interview.dao.CategoryRepository;
import interview.dao.TemplateRepository;
import interview.model.Category;
import interview.model.DTO.CategoryDto;
import interview.model.Template;
import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CategoryServiceController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DozerBeanMapper modelMapper;

    private CategoryDto convertToDto(Category category){
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    private Category convertToEntity(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        return category;
    }

    @RequestMapping(value="/categories")
    public List<CategoryDto> getCategories(){

        return categoryRepository.findAll().stream().map(category -> convertToDto(category)).collect(Collectors.toList());
    }

    @RequestMapping(value="/categories/{id}")
    public CategoryDto getCategoryById(@PathVariable("id") String id){
        return convertToDto(categoryRepository.findById(Long.valueOf(id)).get());
    }

    @RequestMapping(value="/categories", method = RequestMethod.POST)
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto){
        Category category = convertToEntity(categoryDto);
        return convertToDto(categoryRepository.save(category));
    }

    @RequestMapping(value="/categories/{id}", method = RequestMethod.PUT)
    public void updateCategory(@PathVariable("id") String id, @RequestBody CategoryDto categoryDto){
        Category entity = convertToEntity(categoryDto);
        Category existing = categoryRepository.findById(Long.valueOf(id)).get();
        existing.setName(entity.getName());
        categoryRepository.save(existing);
    }

    @RequestMapping(value="/categories/{id}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable("id") String id){
        categoryRepository.deleteById(Long.parseLong(id));
    }

//    @RequestMapping(value="/categories")
//    public List<Category> getCategories(){
//        return categoryRepository.findAll();
//    }
//
//    @RequestMapping(value="/categories/{id}")
//    public Category getCategoryById(@PathVariable("id") String id){ return categoryRepository.findById(Long.valueOf(id)).get(); }
//
//    @RequestMapping(value="/categories", method = RequestMethod.POST)
//    public Category createCategory(@RequestBody Category category){
//        return categoryRepository.save(category);
//    }
//
//    @RequestMapping(value="/categories/{id}", method = RequestMethod.PUT)
//    public Category updateCategory(@PathVariable("id") String id, @RequestBody Category category){
//        categoryRepository.deleteById(Long.parseLong(id));
//        category.setId(Long.parseLong(id));
//        categoryRepository.save(category);
//        return category;
//    }

}
