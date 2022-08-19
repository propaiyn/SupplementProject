package com.qa.SupplementProject.Supplement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/supplement")
public class SupplementController {
    @GetMapping
    public List<Supplement> getAllSupplements() {
        return List.of(
                new Supplement( 1l,
                        LocalDate.now(),
                        "Vitamin D",       // to delete with spaces, path variable will look like: Vitamin%20D
                        5280795L,
                        "Cholecalciferol is a steroid hormone",
                        0.1,
                        0.2
                )
        );
    }
}
