package com.qa.SupplementProject.SupplementDTO;

import com.qa.SupplementProject.Supplement.Supplement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v6/dto/supplement")
public class SupplementControllerDTO {

    private SupplementServiceDTO service;

    public SupplementControllerDTO(SupplementServiceDTO service) {
        super();
        this.service = service;
    }
    @GetMapping("/getall")
    public List<Supplement> findByName(String name) {
        return this.service.getAllSupplements();
    }
    @GetMapping(value = "getById/{supplementId}")
    public Supplement getSupplementById(@PathVariable("supplementId") Long supplementId) {
        return this.service.getSupplementById(supplementId);
    }
    @GetMapping(value = "getByName/{name}")
    public Supplement getSupplementByName(@PathVariable("name") String name) {
        return this.service.getSupplementByName(name);
    }

    @PostMapping("/addNew")
    public void addNewSupplement(@RequestBody Supplement supplement){
        this.service.addNewSupplement(supplement);
    }

    @Transactional
    @DeleteMapping(path = "/delByID/{supplementID}") // e.g. not null optional ?... enforce
    public void deleteSuppByID(@PathVariable("supplementID") Long supplementID) {
        this.service.deleteSuppByID(supplementID);
    }

    @Transactional
    @DeleteMapping(path = "/delByName/{supplementName}")
    public void deleteSuppByName(@PathVariable("supplementName") String supplementName) {
        this.service.deleteSuppByName(supplementName);
    }

    @PutMapping(path = "{supplementId}")
    public Supplement fullUpdateSupplement(
            @PathVariable("supplementId") Long supplementId,
            Long id,
            @RequestBody Supplement supplement) {
        return this.service.updateSupplement(supplementId, supplement);
    }
}
