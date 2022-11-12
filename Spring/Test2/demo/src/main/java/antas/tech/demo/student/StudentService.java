package antas.tech.demo.student;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    // Service class is used to handle business logic
    // Mostly made of methods that perform some action on the data
    // and return a response to the controller

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository; // dependency injection
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> optStudent = studentRepository.findStudentByEmail(student.getEmail());
        if (optStudent.isPresent()) {
            throw new IllegalStateException("Email already taken"); // this will also show as a message and error 500
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("Student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " does not exist"));

        if (!name.isEmpty() && name != null && name.length() > 0 && !student.getName().equals(name)) {
            student.setName(name);
        }

        if (!email.isEmpty() && email != null && email.length() > 0 && !student.getEmail().equals(email)) {
            Optional<Student> optStudentEmail = studentRepository.findStudentByEmail(email);
            if (optStudentEmail.isPresent()) {
                throw new IllegalStateException("Email already taken");
            }
            student.setEmail(email);
        }
    }
}
