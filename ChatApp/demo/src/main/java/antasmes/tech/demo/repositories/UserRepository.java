package antasmes.tech.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import antasmes.tech.demo.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    @Query("{'username':?0}")
    public Optional<User> findByUsername(String username);

    @Query("{'_id':?0}")
    public Optional<List<User>> findByUID(ObjectId UID); // This is a workaround for a bug in Spring Data MongoDB

    @Query("{'username':?0,'password':?1}")
    public Optional<User> auth(String username, String password);
}
