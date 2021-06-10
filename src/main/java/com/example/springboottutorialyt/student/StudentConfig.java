package com.example.springboottutorialyt.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
  @Bean
  CommandLineRunner commandLineRunner(StudentRepository repository) {
    return args -> {
      final Student mustermann = new Student(1L, "Mustermann", "max.mustermann@gmail.com", LocalDate.of(2000, Month.JANUARY, 5));
      final Student alex = new Student(2L, "Alex", "alex.mustermann@gmail.com", LocalDate.of(2004, Month.JANUARY, 5));
      repository.saveAll(List.of(mustermann, alex));
    };
  }
}
