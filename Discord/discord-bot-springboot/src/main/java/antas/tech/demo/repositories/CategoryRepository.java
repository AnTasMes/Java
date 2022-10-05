package antas.tech.demo.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import antas.tech.demo.models.ServerCategory;

@Repository
public interface CategoryRepository extends MongoRepository<ServerCategory, ObjectId> {

    @Query("{'uid':?0}")
    public Optional<ServerCategory> findCategoryByUID(String uid);

    @Query("{'ownerRole._id':?0}")
    public Optional<ServerCategory> findCategoryByRoleId(String role_id);
}
