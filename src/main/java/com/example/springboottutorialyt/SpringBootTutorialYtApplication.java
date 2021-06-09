package com.example.springboottutorialyt;

import com.example.springboottutorialyt.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
@RestController
public class SpringBootTutorialYtApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootTutorialYtApplication.class, args);
  }

  @GetMapping
  public List<Student> hello() {
    return List.of(
        new Student(1L, "Mustermann", "max.mustermann@gmail.com", LocalDate.of(2000, Month.JANUARY, 5), 21)
    );
  }
}
