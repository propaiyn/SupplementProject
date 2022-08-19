package com.qa.SupplementProject.Supplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SupplementService {

    private final SupplementRepository supplementRepository;

    @Autowired
    public SupplementService(SupplementRepository supplementRepository) {
        this.supplementRepository = supplementRepository;
    }
    public List<Supplement> getAllSupplements() {
        return supplementRepository.findAll();
    }

    public void addNewSupplement(Supplement supplement) {

        Optional<Supplement> suppNameOptional = supplementRepository
                .findByName(supplement.getName());
        if (suppNameOptional.isPresent()) {
            throw new IllegalStateException(
                    "Supplement " + supplement.getName() + " already exists in the database");
        }

        Optional<Supplement> suppPCIDOptional = supplementRepository
                .findByPubChemID(supplement.getPubChemID());
        if (suppPCIDOptional.isPresent()) {
            throw new IllegalStateException(
                    "Supplement with Pubchem ID " + supplement.getPubChemID() + " already exists in the database");
        }
        supplementRepository.save(supplement);
    }

    public void deleteSuppByID(Long supplementID) {
        boolean idExists = supplementRepository.existsById(supplementID);

        if(!idExists) {
            throw new IllegalStateException("Supplement with ID: " + supplementID + " does not exist in the database");
        } else if(idExists) {
            supplementRepository.deleteById(supplementID);
        }
    }

    public void deleteSuppByName(String supplementName){
        boolean nameExists = supplementRepository.existsByName(supplementName);

        if(!nameExists) {
            throw new IllegalStateException("Supplement with name: " + supplementName + " does not exist in the database");
        } else {
            supplementRepository.deleteByName(supplementName);
            System.out.println("Supplement with name " + supplementName + " was successfully deleted");}
    }
}