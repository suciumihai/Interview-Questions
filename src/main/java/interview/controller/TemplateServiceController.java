package interview.controller;

import interview.config.JpaConfig;
import interview.dao.TemplateRepository;
import interview.model.Category;
import interview.model.CategoryTemplate;
import interview.model.DTO.CategoryTemplateDto;
import interview.model.DTO.TemplateDto;
import interview.model.Template;
import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class TemplateServiceController {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private DozerBeanMapper modelMapper;

    private TemplateDto convertToDto(Template template){
        TemplateDto templateDto = modelMapper.map(template, TemplateDto.class);
        return templateDto;
    }

    private CategoryTemplate convertToEntityCatTemp(CategoryTemplateDto categoryTemplateDto) {
        CategoryTemplate categoryTemplate = modelMapper.map(categoryTemplateDto, CategoryTemplate.class);
        return categoryTemplate;
    }

    private Template convertToEntity(TemplateDto templateDto) {
        Template template = modelMapper.map(templateDto, Template.class);
        List<CategoryTemplate> catTemps = new ArrayList<>();
        catTemps = templateDto.getCategoryTemplatesDto().stream().map(categoryTemplateDto -> convertToEntityCatTemp(categoryTemplateDto)).collect(Collectors.toList());
        template.getCategoryTemplates().addAll(catTemps);
        return template;
    }

    @RequestMapping(value="/templates")
    public List<TemplateDto> getTemplates(){
        return templateRepository.findAll().stream().map(template -> convertToDto(template)).collect(Collectors.toList());
    }

    @RequestMapping(value="/templates/{id}")
    public TemplateDto getTemplateById(@PathVariable("id") String id){
        return convertToDto(templateRepository.findById(Long.valueOf(id)).get()); }

    @RequestMapping(value="/templates", method = RequestMethod.POST)
    public TemplateDto createTemplate(@RequestBody TemplateDto templateDto){
        Template template = convertToEntity(templateDto);
        template.getCategoryTemplates().clear();
        List<CategoryTemplate> catTempl = templateDto.getCategoryTemplatesDto().stream().map(categoryTemplateDto -> convertToEntityCatTemp(categoryTemplateDto)).collect(Collectors.toList());
        for (CategoryTemplate categoryTemplate : catTempl) {
            categoryTemplate.setTemplate(template);
        }
        template.getCategoryTemplates().addAll(catTempl);
        Template templateCreated = templateRepository.save(template);
        return convertToDto(templateCreated);
    }

    @RequestMapping(value="/templates/{id}", method = RequestMethod.PUT)
    public void updateTemplate(@PathVariable("id") String id, @RequestBody TemplateDto templateDto){
        Template entity = convertToEntity(templateDto);
        Template existing = templateRepository.findById(Long.valueOf(id)).get();
        existing.setName(entity.getName());
        existing.getCategoryTemplates().clear();
        existing.getCategoryTemplates().addAll(entity.getCategoryTemplates());
        templateRepository.save(existing);
    }

    @RequestMapping(value="/templates/{id}", method = RequestMethod.DELETE)
    public void deleteTemplate(@PathVariable("id") String id){
        templateRepository.deleteById(Long.parseLong(id));
    }

//    @RequestMapping(value="/templates")
//    public List<Template> getTemplates(){
//        final List<Template> all = templateRepository.findAll();
//        return all;
//    }
//
//    @RequestMapping(value="/templates/{id}")
//    public Template getTemplateById(@PathVariable("id") String id){ return templateRepository.findById(Long.valueOf(id)).get(); }
//
//    @RequestMapping(value="/templates", method = RequestMethod.POST)
//    public Template createTemplate(@RequestBody Template template){
//        return templateRepository.save(template);
//    }
//
//    @RequestMapping(value="/templates/{id}", method = RequestMethod.PUT)
//    public Template updateTemplate(@PathVariable("id") String id, @RequestBody Template template){
//        templateRepository.deleteById(Long.parseLong(id));
//        template.setId(Long.parseLong(id));
//        templateRepository.save(template);
//        return template;
//    }
}
