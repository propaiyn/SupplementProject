package com.qa.SupplementProject.Supplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v3/supplement")
public class SupplementController {

    private final SupplementService supplementService;

    @Autowired
    public SupplementController(SupplementService supplementService) {
        this.supplementService = supplementService;
    }

    @GetMapping
    public List<Supplement> getAllSupplements() {
        return supplementService.getAllSupplements();
    }

    @PostMapping
    public void addNewSupplement(@RequestBody Supplement supplement) {
        supplementService.addNewSupplement(supplement);
    }
}
