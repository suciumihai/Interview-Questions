package interview.controller;

import interview.dao.CandidateRepository;
import interview.model.Candidate;
import interview.model.DTO.CandidateDto;
import interview.model.DTO.UserDto;
import interview.model.User;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class CandidateServiceController {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private DozerBeanMapper modelMapper;

    private CandidateDto convertToDto(Candidate entity){
        CandidateDto dto = modelMapper.map(entity, CandidateDto.class);
        return dto;
    }

    private Candidate convertToEntity(CandidateDto dto) {
        Candidate entity = modelMapper.map(dto, Candidate.class);
        return entity;
    }

    @RequestMapping(value = "/candidates")
    public List<CandidateDto> getCandidates(){
        return candidateRepository.findAll().stream().map(candidate -> convertToDto(candidate)).collect(Collectors.toList());
    }

    @RequestMapping(value="/candidates/{id}")
    public CandidateDto getCandidateById(@PathVariable("id") String id) {
        return convertToDto(candidateRepository.findById(Long.valueOf(id)).get());
    }

    @RequestMapping(value = "/candidates", method = RequestMethod.POST)
    public CandidateDto createCandidate(@RequestBody CandidateDto body){
        Candidate entity = convertToEntity(body);
        Candidate created = candidateRepository.save(entity);
        return convertToDto(created);
    }

    @RequestMapping(value = "/candidates/{id}", method = RequestMethod.PUT)
    public CandidateDto updateCandidate(@PathVariable("id") String id, @RequestBody CandidateDto body){
        Candidate entity = convertToEntity(body);
        candidateRepository.deleteById(Long.parseLong(id));
        entity.setId(Long.parseLong(id));
        Candidate created = candidateRepository.save(entity);
        return convertToDto(created);
    }

    @RequestMapping(value = "/candidates/{id}", method = RequestMethod.DELETE)
    public void deleteCandidate(@PathVariable("id") String id) {
        candidateRepository.deleteById(Long.parseLong(id));
    }

//    @RequestMapping(value = "/candidates")
//    public List<Candidate> getCandidates(){
//        return candidateRepository.findAll();
//    }
//
//    @RequestMapping(value="/candidates/{id}")
//    public Candidate getCandidateById(@PathVariable("id") String id) {
//        return candidateRepository.findById(Long.valueOf(id)).get();
//    }
//
//    @RequestMapping(value = "/candidates", method = RequestMethod.POST)
//    public Candidate createCandidate(@RequestBody Candidate candidate){
//        return candidateRepository.save(candidate);
//    }
//
//    @RequestMapping(value = "/candidates/{id}", method = RequestMethod.PUT)
//    public Candidate updateCandidate(@PathVariable("id") String id, @RequestBody Candidate candidate){
//        candidateRepository.deleteById(Long.parseLong(id));
//        candidate.setId(Long.parseLong(id));
//        candidateRepository.save(candidate);
//        return candidate;
//    }

}
