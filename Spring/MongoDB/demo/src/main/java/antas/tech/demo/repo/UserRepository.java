package antas.tech.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import antas.tech.demo.model.User;

@Repository("user")
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{'username':?0}")
    User findUserByUsername(String username);

    @Query("{'username':?0, 'discriminator':?1}")
    User findUserByUD(String username, String discriminator);
}
