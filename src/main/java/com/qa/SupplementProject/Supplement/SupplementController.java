package com.qa.SupplementProject.Supplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v4/supplement")
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

    @Transactional
    @DeleteMapping(path = "/delByID/{supplementID}") // e.g. not null optional ?... enforce
    public void deleteSuppByID(
            @PathVariable("supplementID") Long supplementID)
    {
        supplementService.deleteSuppByID(supplementID);
    }

    @Transactional
    @DeleteMapping(path = "/delByName/{supplementName}")
    public void deleteSuppByName(
            @PathVariable("supplementName") String supplementName)
    {
        supplementService.deleteSuppByName(supplementName);
    }
}
