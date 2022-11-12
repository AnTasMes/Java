package antas.tech.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import antas.tech.demo.model.User;
import antas.tech.demo.repo.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByName(String name) {
        return userRepository.findUserByUsername(name);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
