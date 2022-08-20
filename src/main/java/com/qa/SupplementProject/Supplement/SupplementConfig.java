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
            Supplement vD = new Supplement(
                    "Vitamin D",// to delete with spaces, path variable will look like: Vitamin%20D
                   "Cholecalciferol is a steroid hormone",
                    5280795L,
                    0.1,
                    0.2);

            Supplement vC = new Supplement(
                    "Vitamin C",
                    "Ascorbic Acid is a natural water-soluble vitamin (Vitamin C)."
                    ,54670067L,
                    40.0,
                    60.0);

            Supplement vE = new Supplement(
                    "Vitamin E",// to delete with spaces, path variable will look like: Vitamin%20D
                    "Alpha-Tocopherol is the orally bioavailable alpha form" +
                    "of the naturally-occurring fat-soluble vitamin E," +
                    " with potent antioxidant and cytoprotective activities.",
                    14985L,
                    3.0,
                    4.0);

            Supplement iron= new Supplement(
                    "Iron",
                    "Iron is an essential heavy metal that is included in many over-the-counter" +
                    "multivitamin and mineral supplements and is used therapeutically in higher doses to treat" +
                    " or prevent iron deficiency anemia.",
                    23925L,
                    8.0,
                    15.0);

            Supplement vK = new Supplement(
                    "Vitamin K",
                    "A lipid cofactor that is required for normal blood clotting.",
                    5280483L,
                    0.040,
                    0.1);

            Supplement calcium = new Supplement(
                    "Calcium",
                    "Calcium plays a vital role in the anatomy, physiology and" +
                    " biochemistry of organisms and of the cell, particularly in signal transduction pathways.",
                    5460341L,
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