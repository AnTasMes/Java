package antas.tech.demo.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Repository class is used to handle data access
    // Mostly made of methods that perform some action on the data
    // and return a response to the service
    // This is an interface that extends JpaRepository
    // JpaRepository is a generic interface that takes two parameters
    // The first parameter is the entity class
    // The second parameter is the type of the primary key of the entity class

    // Many methods are already implemented in the JpaRepository interface

    // @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
