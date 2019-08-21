package interview.controller;

import interview.config.JpaConfig;
import interview.dao.TemplateRepository;
import interview.model.DTO.TemplateDto;
import interview.model.Template;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class TemplateServiceController {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private ModelMapper modelMapper;

    private TemplateDto convertToDto(Template template){
        TemplateDto templateDto = modelMapper.map(template, TemplateDto.class);
        return templateDto;
    }

    private Template convertToEntity(TemplateDto templateDto) {
        Template template = modelMapper.map(templateDto, Template.class);
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
        Template templateCreated = templateRepository.save(template);
        return convertToDto(templateCreated);
    }

    @RequestMapping(value="/templates/{id}", method = RequestMethod.PUT)
    public TemplateDto updateTemplate(@PathVariable("id") String id, @RequestBody TemplateDto templateDto){
        Template template = convertToEntity(templateDto);
        templateRepository.deleteById(Long.parseLong(id));
        template.setId(Long.parseLong(id));
        Template templateCreated = templateRepository.save(template);
        return convertToDto(templateCreated);
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
