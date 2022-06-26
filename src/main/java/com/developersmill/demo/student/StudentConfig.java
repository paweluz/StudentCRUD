package com.developersmill.demo.student;

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
            Student john = new Student(
                    "John",
                    LocalDate.of(2000, Month.JANUARY, 5),
                    "johnSmish@test.com");

            Student miriam = new Student(
                    "Miriam",
                    LocalDate.of(2002, Month.JANUARY, 5),
                    "MiriamLuis@test.com");
            repository.saveAll(List.of(john, miriam));
        };
    }
}
