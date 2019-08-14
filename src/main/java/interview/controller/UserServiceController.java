package interview.controller;

import interview.dao.UserRepository;
import interview.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class UserServiceController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @RequestMapping(value="/users/{id}")
    public User getUserById(@PathVariable("id") String id){ return userRepository.findById(Long.valueOf(id)).get(); }

    @RequestMapping(value="/users", method = RequestMethod.POST)
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @RequestMapping(value="/users/{id}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable("id") String id, @RequestBody User user){
        userRepository.deleteById(Long.parseLong(id));
        user.setId(Long.parseLong(id));
        userRepository.save(user);
        return user;
    }

    @RequestMapping(value="/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") String id){
        userRepository.deleteById(Long.parseLong(id));
    }
}
