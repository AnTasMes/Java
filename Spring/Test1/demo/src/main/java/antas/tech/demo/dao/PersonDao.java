package antas.tech.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import antas.tech.demo.model.Person;

public interface PersonDao {
    int insertPerson(UUID id, Person person);

    List<Person> getPeople();

    int deletePersonById(UUID id);

    int updatePersonById(UUID id, Person person);

    Optional<Person> selectPersonById(UUID id);

    default int insertPerson(Person person) {
        UUID ID = UUID.randomUUID();
        return insertPerson(ID, person);
    }
}
