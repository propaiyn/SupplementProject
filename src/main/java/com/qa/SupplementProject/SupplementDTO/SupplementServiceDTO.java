package com.qa.SupplementProject.SupplementDTO;

import com.qa.SupplementProject.Exception.*;
import com.qa.SupplementProject.Supplement.Supplement;
import com.qa.SupplementProject.Supplement.SupplementRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SupplementServiceDTO {

    @Autowired
    private SupplementRepository supplementRepository;
    private ModelMapper mapper;

    private Supplement mapToDTO(Supplement supplement) {
        Supplement dto = new Supplement();
        dto.setName(supplement.getName());
        dto.setPubChemId(supplement.getPubChemId());
        dto.setLowerBoundaryDoseMG(supplement.getLowerBoundaryDoseMG());
        dto.setUpperBoundaryDoseMG(supplement.getUpperBoundaryDoseMG());
        return dto;
    }

    // Methods to retrieve all supplements from DB (+ Ability to get & order by ID/Name)
    public List<Supplement> getAllSupplements() {
        return supplementRepository.findAll();
    }

    public List<Supplement> getAllSupplementsSortedById() {
        return supplementRepository.findAllByOrderByIdAsc();
    }

    public List<Supplement> getAllSupplementsSortedByName() {
        return supplementRepository.findAllByOrderByNameAsc();
    }

    // Following methods utilise optionals for exceptions
    public Supplement getSupplementById(Long supplementId) {

        Optional<Supplement> optionalById = supplementRepository.findById(supplementId);

        if (!optionalById.isPresent())
            throw new IDNotFoundException("The supplement with id " + supplementId + " could not be found.");

        return optionalById.get();
    }

    public Supplement getSupplementByName(String name) {

        Optional<Supplement> optionalByName = supplementRepository.findByName(name);

        if (!optionalByName.isPresent())
            throw new NameNotFoundException("The supplement with name " + name + " could not be found.");

        return optionalByName.get();
    }

    public Supplement addNewSupplement(Supplement supplement) {

        Optional<Supplement> suppNameOptional = supplementRepository
                .findByName(supplement.getName());

        Optional<Supplement> suppPCIDOptional = supplementRepository
                .findByPubChemId(supplement.getPubChemId());

        if (suppNameOptional.isPresent()) {
            throw new NameExistsException(
                    "Supplement " + supplement.getName() + " already exists in the database");
        } else if(supplement.getName() == null) {
            throw new NullNameException("Please provide the name of your supplement");
        } else if (suppPCIDOptional.isPresent()) {
            throw new PubChemIdExistsException(
                    "Supplement with PubChem ID " + supplement.getPubChemId() + " already exists in the database");
        }else if(supplement.getPubChemId() == null){
            throw new NullPCIDException(
                    "Please provide the PubChemID of your supplement");
        }
        Supplement saved = this.supplementRepository.save(supplement);
        return this.mapToDTO(saved);
    }

    public void deleteSuppByID(Long supplementId) {
        Boolean idExists = supplementRepository.existsById(supplementId);

        if (!idExists) {
            throw new IDNotFoundException(
                    "Supplement with ID: " + supplementId + " does not exist in the database");
        } else {
            supplementRepository.deleteById(supplementId);
            System.out.println("Supplement with ID: " + supplementId + " was successfully deleted");
        }
    }

    public void deleteSuppByName(String supplementName) {
        Boolean nameExists = supplementRepository.existsByName(supplementName);

        if (!nameExists) {
            throw new NameNotFoundException("Supplement with name: " + supplementName + " does not exist in the database");
        } else {
            supplementRepository.deleteByName(supplementName);
            System.out.println("Supplement with name: " + supplementName + " was successfully deleted");
        }
    }

    public Supplement updateSupplement(Long supplementId, Supplement supplement) {
        Optional<Supplement> suppNameOptional = supplementRepository
                .findByName(supplement.getName());
        Optional<Supplement> suppPCIDOptional = supplementRepository
                .findByPubChemId(supplement.getPubChemId());

        if (suppNameOptional.isPresent()) {
            throw new NameExistsException(
                    "Supplement " + supplement.getName() + " already exists in the database");
        } else if(supplement.getName() == null) {
            throw new NullNameException("Please provide the name of your supplement");
        } else if (suppPCIDOptional.isPresent()) {
            throw new PubChemIdExistsException(
                    "Supplement with PubChem ID " + supplement.getPubChemId() + " already exists in the database");
        }else if(supplement.getPubChemId() == null){
            throw new NullPCIDException(
                    "Please provide the PubChemID of your supplement");
        }

        supplement.setId(supplementId);
        return supplementRepository.save(supplement);
    }
}