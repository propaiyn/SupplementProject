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
                .findBypubChemID(supplement.getPubChemID());
        if (suppPCIDOptional.isPresent()) {
            throw new IllegalStateException(
                    "Supplement with Pubchem ID " + supplement.getPubChemID() + " already exists in the database");
        }
    }
}