package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository)
    {
        return args -> {
            Student prabhjit = new Student( "Prabhjit", 
                            LocalDate.of(2012, 12, 12), "abc@gmail.com" );
            Student brar_navi = new Student( "Robert",
                    LocalDate.of(2012, 12, 12), "abxz@gmail.com" );

            repository.saveAll( List.of(prabhjit, brar_navi));
        };
    }
}
