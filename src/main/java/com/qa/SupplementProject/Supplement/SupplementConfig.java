package com.qa.SupplementProject.Supplement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

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

            Supplement vC = new Supplement(2l,
                    LocalDate.now(),
                    "Vitamin C",
                    54670067L,
                    "Ascorbic Acid is a natural water-soluble vitamin (Vitamin C).",
                    40.0,
                    60.0);

            Supplement vE = new Supplement(3l,
                    LocalDate.now(),
                    "Vitamin E",// to delete with spaces, path variable will look like: Vitamin%20D
                    14985L,
                    "CAlpha-Tocopherol is the orally bioavailable alpha form" +
                            "of the naturally-occurring fat-soluble vitamin E," +
                            " with potent antioxidant and cytoprotective activities.",
                    3.0,
                    4.0);

            Supplement iron= new Supplement(4l,
                    LocalDate.now(),
                    "Iron",
                    23925L,
                    "Iron is an essential heavy metal that is included in many over-the-counter" +
                            "multivitamin and mineral supplements and is used therapeutically in higher doses to treat" +
                            " or prevent iron deficiency anemia.",
                    8.0,
                    15.0);

            Supplement vK = new Supplement(5l,
                    LocalDate.now(),
                    "Vitamin K",
                    5280483L,
                    "A lipid cofactor that is required for normal blood clotting.",
                    0.040,
                    0.1);

            Supplement calcium = new Supplement(1l,
                    LocalDate.now(),
                    "Calcium",
                    5460341L,
                    "Calcium plays a vital role in the anatomy, physiology and" +
                            " biochemistry of organisms and of the cell, particularly in signal transduction pathways.",
                    700.0,
                    1000.0);


            repository.saveAll(List.of(vD,
                    vE,
                    vC,
                    vK,
                    calcium,
                    iron) // Save list to database
            );
        };
    }
}