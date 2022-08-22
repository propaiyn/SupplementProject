package com.qa.SupplementProject.Supplement;

import com.qa.SupplementProject.Exception.AlreadyExistsException;
import com.qa.SupplementProject.Exception.SupplementNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplementService {

    private final SupplementRepository supplementRepository;

    @Autowired
    public SupplementService(SupplementRepository supplementRepository) {
        this.supplementRepository = supplementRepository;
    }

    public Supplement getSupplementById(Long supplementId) {

        Optional<Supplement> optionalById = supplementRepository.findById(supplementId);

        if (!optionalById.isPresent())
            throw new SupplementNotFoundException("The supplement with id " + supplementId + " could not be found.");

        return optionalById.get();
    }

    public Supplement getSupplementByName(String name) {

        Optional<Supplement> optionalByName = supplementRepository.findByName(name);

        if (!optionalByName.isPresent())
            throw new SupplementNotFoundException("The supplement with id " + name + " could not be found.");

        return optionalByName.get();
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
            throw new AlreadyExistsException(
                    "Supplement " + supplement.getName() + " already exists in the database");
        } else if(suppNameOptional.isPresent()){
            throw new IllegalStateException(
                    "Please provide the name of your supplement");
        } else if (suppPCIDOptional.isPresent()) {
            throw new AlreadyExistsException(
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
            throw new SupplementNotFoundException(
                    "Supplement with ID: " + supplementId + " does not exist in the database");
        } else{ supplementRepository.deleteById(supplementId);
        }
    }

    public void deleteSuppByName(String supplementName) {
        boolean nameExists = supplementRepository.existsByName(supplementName);

        if (!nameExists) {
            throw new SupplementNotFoundException("Supplement with name: " + supplementName + " does not exist in the database");
        } else {
            supplementRepository.deleteByName(supplementName);
            System.out.println("Supplement with name " + supplementName + " was successfully deleted");
        }
    }

    public Supplement updateSupplement(Long supplementId, Supplement supplement) {
        Optional<Supplement> suppNameOptional = supplementRepository
                .findByName(supplement.getName());

        supplement.setId(supplementId);
        return supplementRepository.save(supplement);
    } // Shouldnt let you update supplement with a name or ID or pubchem id that is the same

}