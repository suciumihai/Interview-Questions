package interview.controller;

import interview.dao.UserRepository;
import interview.model.DTO.UserDto;
import interview.model.User;
import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class UserServiceController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DozerBeanMapper modelMapper;

    private UserDto convertToDto(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    private User convertToEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

    @RequestMapping(value="/users")
    public List<UserDto> getUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> convertToDto(user)).collect(Collectors.toList());
    }

    @RequestMapping(value="/users", method = RequestMethod.POST)
    public UserDto createUser(@RequestBody UserDto userDto){
        User user = convertToEntity(userDto);
        User userCreated = userRepository.save(user);
        return convertToDto(userCreated);
    }

    @RequestMapping(value="/users/{id}")
    public UserDto getUserById(@PathVariable("id") String id){
        return convertToDto(userRepository.findById(Long.valueOf(id)).get());
    }

    @RequestMapping(value="/users/{id}", method = RequestMethod.PUT)
    public void updateUser(@PathVariable("id") String id, @RequestBody UserDto userDto){
        User entity = convertToEntity(userDto);
        User existing = userRepository.findById(Long.valueOf(id)).get();
        existing.setEmail(entity.getEmail());
        existing.setRole(entity.getRole());
        existing.setPassword(entity.getPassword());
        userRepository.save(existing);
    }

    @RequestMapping(value="/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") String id){
        userRepository.deleteById(Long.parseLong(id));
    }

//    @RequestMapping(value="/users")
//    public List<User> getUsers(){
//        return userRepository.findAll();
//    }
//
//    @RequestMapping(value="/users/{id}")
//    public User getUserById(@PathVariable("id") String id){ return userRepository.findById(Long.valueOf(id)).get(); }
//
//    @RequestMapping(value="/users", method = RequestMethod.POST)
//    public User createUser(@RequestBody User user){
//        return userRepository.save(user);
//    }
//
//    @RequestMapping(value="/users/{id}", method = RequestMethod.PUT)
//    public User updateUser(@PathVariable("id") String id, @RequestBody User user){
//        userRepository.deleteById(Long.parseLong(id));
//        user.setId(Long.parseLong(id));
//        userRepository.save(user);
//        return user;
//    }

}
