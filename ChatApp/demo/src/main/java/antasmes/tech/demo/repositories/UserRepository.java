package antasmes.tech.demo.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import antasmes.tech.demo.models.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {

}
