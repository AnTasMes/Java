package antas.tech.demo.services;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import antas.tech.demo.models.User;
import antas.tech.demo.repositories.UserRepository;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findUserByUID(id);
    }

    public Optional<User> getUserByDiscriminator(String discriminator) {
        return userRepository.findUserByDiscriminator(discriminator);
    }

    @NotNull
    public List<User> insertUsers(List<User> users) {
        users.removeIf(u -> {
            Optional<User> optUser = userRepository.findUserByUID(u.getUid());
            return optUser.isPresent();
        });

        return userRepository.saveAll(users);
    }

    @Transactional
    public Boolean updateUser(String uid, User user) {
        Optional<User> otpUser = userRepository.findUserByUID(uid);
        if (otpUser.isPresent()) {
            User u = otpUser.get();
            u.setUsername(user.getUsername());
            u.setDiscriminator(user.getDiscriminator());
            u.setRoles(user.getRoles());

            return true;
        }
        return false;
    }

    public Boolean deleteUser(String uid) {
        Optional<User> optUser = userRepository.findUserByUID(uid);
        if (optUser.isPresent()) {
            userRepository.delete(optUser.get());
            return true;
        }
        return false;
    }
}
