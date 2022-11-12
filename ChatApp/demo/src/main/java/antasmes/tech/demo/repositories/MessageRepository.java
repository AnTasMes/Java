package antasmes.tech.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import antasmes.tech.demo.models.Message;

@Repository
public interface MessageRepository extends MongoRepository<Message, ObjectId> {
    // Find all messages from a user to another user
    @Query("{'fromUserId':?0, 'toUserId':?1}")
    public Optional<List<Message>> findMessages(ObjectId fromUserID, ObjectId toUserId);
}
