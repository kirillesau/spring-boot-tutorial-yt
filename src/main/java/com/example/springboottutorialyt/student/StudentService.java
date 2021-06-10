package com.example.springboottutorialyt.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> getStudents() {
    return studentRepository.findAll();
  }

  public void addNewStudent(Student student) {
    final Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
    if (studentOptional.isPresent()) {
      throw new IllegalStateException("email taken");
    }
    studentRepository.save(student);
  }

  public void deleteStudent(Long id) {
    final boolean exists = studentRepository.existsById(id);
    if (!exists) {
      throw new IllegalStateException("student with id %d does not exists".formatted(id));
    }
    studentRepository.deleteById(id);
  }

  @Transactional
  public void updateStudent(Long studentId, String name, String email) {
    final Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new IllegalStateException("student with id %d does not exists".formatted(studentId)));

    if (name != null &&
        name.length() > 0 &&
        !Objects.equals(student.getName(), name)) {
      student.setName(name);
    }

    if (email != null &&
        email.length() > 0 &&
        !Objects.equals(student.getEmail(), email)) {
      final Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
      if (studentOptional.isPresent()) {
        throw new IllegalStateException("email taken");
      }
      student.setEmail(email);
    }
  }
}
