package com.qa.SupplementProject.Supplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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
    } // Retrieves all supplements in the database

    public void addNewSupplement(Supplement supplement) {

        Optional<Supplement> suppNameOptional = supplementRepository
                .findByName(supplement.getName());

        Optional<Supplement> suppPCIDOptional = supplementRepository
                .findByPubChemId(supplement.getPubChemId());

        if (suppNameOptional.isPresent()) {
            throw new IllegalStateException(
                    "Supplement " + supplement.getName() + " already exists in the database");
        } else if(suppNameOptional.isPresent()){
            throw new IllegalStateException(
                    "Please provide the name of your supplement");
        } else if (suppPCIDOptional.isPresent()) {
            throw new IllegalStateException(
                    "Supplement with Pubchem ID " + supplement.getPubChemId() + " already exists in the database");
        }else if(suppPCIDOptional.isPresent()){
            throw new IllegalStateException(
                    "Please provide the PubChemID of your supplement");
        }
        supplementRepository.save(supplement);
    }

    public void deleteSuppByID(Long supplementId) {
        boolean idExists = supplementRepository.existsById(supplementId);

        if (!idExists) {
            throw new IllegalStateException(
                    "Supplement with ID: " + supplementId + " does not exist in the database");
        } else{ supplementRepository.deleteById(supplementId);
        }
    }

    public void deleteSuppByName(String supplementName) {
        boolean nameExists = supplementRepository.existsByName(supplementName);

        if (!nameExists) {
            throw new IllegalStateException("Supplement with name: " + supplementName + " does not exist in the database");
        } else {
            supplementRepository.deleteByName(supplementName);
            System.out.println("Supplement with name " + supplementName + " was successfully deleted");
        }
    }

    @Transactional
    public void updateSupplement(Long supplementId,
                                 String name) {
        Supplement supplement = supplementRepository.findById(supplementId)
                .orElseThrow(() -> new IllegalStateException(
                        "Supplement with ID " + supplementId + " does not exist in the database"));
        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(supplement.getName(), name)) {
            supplement.setName(name);
        }
    }

    public Supplement updateSupplement(Long supplementId, Supplement supplement) {
        supplement.setId(supplementId);
        return supplementRepository.save(supplement);
    }


}