package antas.tech.demo.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import antas.tech.demo.models.ErrorLog;

@Repository
public interface ErrLogRepository extends MongoRepository<ErrorLog, ObjectId> {
    @Query("{'exception':?0}")
    public Optional<ErrorLog> findLogByException(String exception);
}
