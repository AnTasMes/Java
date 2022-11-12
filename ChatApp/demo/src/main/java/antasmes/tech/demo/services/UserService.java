package antasmes.tech.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import antasmes.tech.demo.listeners.ConnectionListener;
import antasmes.tech.demo.models.User;
import antasmes.tech.demo.repositories.UserRepository;

@Service
@DependsOn({ "sshinit" })
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        System.out.println("UserService constructor");
        this.userRepository = userRepository;
    }

    public List<User> getFriends(User user) {
        // Username is unique
        Optional<User> optUser = userRepository.findByUsername(user.getUsername());
        if (optUser.isPresent()) {
            return optUser.get().getFriends();
        }
        return new ArrayList<User>();
    }

    public void testService() {
        System.out.println("User service test");
    }

    public User createUser(User user) {
        return userRepository.insert(user);
    }

    public User getUserByUsername(String username) {
        Optional<User> optUser = userRepository.findByUsername(username);
        if (optUser.isPresent()) {
            return optUser.get();
        }
        return null;
    }

    public User getUserByUID(ObjectId UID) {
        Optional<List<User>> optUser = userRepository.findByUID(UID);
        if (optUser.isPresent()) {
            List<User> users = optUser.get();
            System.out.println("Found " + users.size() + " users");
            if (users.size() > 0) {
                for (User user : users) {
                    System.out.println("User: " + user.getUsername());
                }

                return users.get(0);
            }
        }
        return null;
    }

    public void addFriend(User user, String username) {
        Optional<User> optFriend = userRepository.findByUsername(username);
        if (!optFriend.isPresent()) {
            return;
        }
        User friend = optFriend.get();
        user.getFriends().add(friend);
        userRepository.save(user);
    }

    public User authorize(String username, String password) {

        if (!ConnectionListener.isConnected) {
            System.out.println("Not connected, wait");
            return null;
        }

        Optional<User> optUser = userRepository.auth(username, password);
        if (optUser.isPresent())
            return optUser.get();
        return null;
    }

    public Boolean usernameExists(String username) {

        if (!ConnectionListener.isConnected) {
            System.out.println("Not connected, wait");

            return false;
        }

        Optional<User> optUser = userRepository.findByUsername(username);

        if (optUser.isPresent()) {
            return true;
        }

        return false;
    }
}