package com.qa.SupplementProject.Supplement;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v5.1/supplement")


public class SupplementController {

    private final SupplementService supplementService;

    @Autowired
    public SupplementController(SupplementService supplementService) {
        this.supplementService = supplementService;
    }

    @GetMapping("/getall")
    public List<Supplement> getAllSupplements() {
        return supplementService.getAllSupplements();
    }

    @PostMapping("/addnew")
    public void addNewSupplement(@RequestBody Supplement supplement) {
        supplementService.addNewSupplement(supplement);
    }

    @Transactional
    @DeleteMapping(path = "/delByID/{supplementID}") // e.g. not null optional ?... enforce
    public void deleteSuppByID(@PathVariable("supplementID") Long supplementID) {
        supplementService.deleteSuppByID(supplementID);
    }

    @Transactional
    @DeleteMapping(path = "/delByName/{supplementName}")
    public void deleteSuppByName(@PathVariable("supplementName") String supplementName) {
        supplementService.deleteSuppByName(supplementName);
    }

    @PutMapping(path = "{supplementId}")
    public Supplement fullUpdateSupplement(
            @PathVariable("supplementId") Long supplementId,
            Long id,
            @RequestBody Supplement supplement) {
        return supplementService.updateSupplement(supplementId, supplement);
    }
}
