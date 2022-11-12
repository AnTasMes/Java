package antas.tech.demo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import antas.tech.demo.model.Record;

@Repository("log")
public interface LogRepository extends MongoRepository<Record, String> {

    @Query("{'type':?0}")
    List<Record> findLogsByType(String type);
}
