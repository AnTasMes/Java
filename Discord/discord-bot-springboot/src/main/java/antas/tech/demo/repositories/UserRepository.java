package antas.tech.demo.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import antas.tech.demo.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

    /**
     * Finds a user based on provided UID
     * 
     * @param uid UID to search
     * @return Possible users
     */
    @Query("{'uid':?0}")
    public Optional<User> findUserByUID(String uid);

    @Query("{'discriminator':?0}")
    public Optional<User> findUserByDiscriminator(String discriminator);
}
