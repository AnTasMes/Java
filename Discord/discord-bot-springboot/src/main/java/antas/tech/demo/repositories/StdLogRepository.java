package antas.tech.demo.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import antas.tech.demo.models.StdLog;

@Repository
public interface StdLogRepository extends MongoRepository<StdLog, ObjectId> {

    @Query("{'type':?0}")
    public Optional<StdLog> findLogByType(String type);
}
