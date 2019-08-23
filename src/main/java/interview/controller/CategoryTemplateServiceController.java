package interview.controller;

import interview.config.JpaConfig;
import interview.dao.CategoryTemplateRepository;
import interview.dao.TemplateRepository;
import interview.model.Category;
import interview.model.CategoryTemplate;
import interview.model.DTO.CategoryTemplateDto;
import interview.model.Template;
import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CategoryTemplateServiceController {

    @Autowired
    private CategoryTemplateRepository categoryTemplateRepository;

    @Autowired
    private DozerBeanMapper modelMapper;

    private CategoryTemplateDto convertToDto(CategoryTemplate categoryTemplate){
        CategoryTemplateDto categoryTemplateDto = modelMapper.map(categoryTemplate, CategoryTemplateDto.class);
        return categoryTemplateDto;
    }

    private CategoryTemplate convertToEntity(CategoryTemplateDto categoryTemplateDto) {
        CategoryTemplate categoryTemplate = modelMapper.map(categoryTemplateDto, CategoryTemplate.class);
        return categoryTemplate;
    }

    @RequestMapping(value="/categoryTemplates")
    public List<CategoryTemplateDto> getCategoryTemplates(){
        return categoryTemplateRepository.findAll().stream().map(categoryTemplate -> convertToDto(categoryTemplate)).collect(Collectors.toList());
    }

    @RequestMapping(value="/categoryTemplates/{id}")
    public CategoryTemplateDto getCategoryTemplateById(@PathVariable("id") String id){
        return convertToDto(categoryTemplateRepository.findById(Long.valueOf(id)).get()); }

    @RequestMapping(value="/categoryTemplates", method = RequestMethod.POST)
    public CategoryTemplateDto createCategoryTemplate(@RequestBody CategoryTemplateDto categoryTemplateDto){
        CategoryTemplate categoryTemplate = convertToEntity(categoryTemplateDto);
        CategoryTemplate categoryTemplateCreated = categoryTemplateRepository.save(categoryTemplate);
        return convertToDto(categoryTemplateCreated);
    }

    @RequestMapping(value="/categoryTemplates/{id}", method = RequestMethod.PUT)
    public CategoryTemplateDto updateCategoryTemplate(@PathVariable("id") String id, @RequestBody CategoryTemplateDto categoryTemplateDto){
        CategoryTemplate categoryTemplate = convertToEntity(categoryTemplateDto);
        categoryTemplateRepository.deleteById(Long.parseLong(id));
        categoryTemplate.setId(Long.parseLong(id));
        CategoryTemplate categoryTemplateCreated = categoryTemplateRepository.save(categoryTemplate);
        return convertToDto(categoryTemplateCreated);
    }

    @RequestMapping(value="/categoryTemplates/{id}", method = RequestMethod.DELETE)
    public void deleteCategoryTemplate(@PathVariable("id") String id){
        categoryTemplateRepository.deleteById(Long.parseLong(id));
    }

//    @RequestMapping(value="/categoryTemplates")
//    public List<CategoryTemplate> getCategoryTemplates(){
//        return categoryTemplateRepository.findAll();
//    }
//
//    @RequestMapping(value="/categoryTemplates/{id}")
//    public CategoryTemplate getCategoryTemplateById(@PathVariable("id") String id){ return categoryTemplateRepository.findById(Long.valueOf(id)).get(); }
//
//    @RequestMapping(value="/categoryTemplates", method = RequestMethod.POST)
//    public CategoryTemplate createCategoryTemplate(@RequestBody CategoryTemplate categoryTemplate){
//        return categoryTemplateRepository.save(categoryTemplate);
//    }
//
//    @RequestMapping(value="/categoryTemplates/{id}", method = RequestMethod.PUT)
//    public CategoryTemplate updateCategoryTemplate(@PathVariable("id") String id, @RequestBody CategoryTemplate categoryTemplate){
//        categoryTemplateRepository.deleteById(Long.parseLong(id));
//        categoryTemplate.setId(Long.parseLong(id));
//        categoryTemplateRepository.save(categoryTemplate);
//        return categoryTemplate;
//    }
}

