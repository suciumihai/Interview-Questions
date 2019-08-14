package interview.controller;

import interview.dao.TemplateRepository;
import interview.model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@Transactional
public class TemplateServiceController {

    @Autowired
    private TemplateRepository templateRepository;

    @RequestMapping(value="/templates")
    public List<Template> getTemplates(){
        return templateRepository.findAll();
    }

    //hai sa vedem diferenta intre response entiti si list
    @RequestMapping(value = "/getTemplateRespEnitty")
    public ResponseEntity<List<Template>> get() {
        return new ResponseEntity<List<Template>>(templateRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/templates/{id}")
    public Template getTemplateById(@PathVariable("id") String id){ return templateRepository.findById(Long.valueOf(id)).get(); }

    @RequestMapping(value="/templates", method = RequestMethod.POST)
    public Template createTemplate(@RequestBody Template template){
        return templateRepository.save(template);
    }

    @RequestMapping(value="/templates/{id}", method = RequestMethod.PUT)
    public Template updateTemplate(@PathVariable("id") String id, @RequestBody Template template){
        templateRepository.deleteById(Long.parseLong(id));
        template.setId(Long.parseLong(id));
        templateRepository.save(template);
        return template;
    }

    @RequestMapping(value="/templates/{id}", method = RequestMethod.DELETE)
    public void deleteTemplate(@PathVariable("id") String id){
        templateRepository.deleteById(Long.parseLong(id));
    }
}
