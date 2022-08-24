package com.qa.SupplementProject.Supplement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;


//DATABASE PREPOPULATION
@Configuration
public class SupplementConfig {

    @Bean
    CommandLineRunner commandLineRunner(SupplementRepository repository) { //Bean creation, Repository injection + access
        return args -> {
            Supplement vD = new Supplement(1l,
                    LocalDate.now(),
                    "Vitamin D",// to delete with spaces, path variable will look like: Vitamin%20D
                    5280795L,
                    "Cholecalciferol is a steroid hormone",
                    0.1,
                    0.2);

            repository.saveAll(List.of(vD) // Save list to database
            );
        };
    }
}