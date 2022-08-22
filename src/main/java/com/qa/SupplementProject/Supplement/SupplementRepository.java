package com.qa.SupplementProject.Supplement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplementRepository
        extends CrudRepository<Supplement, Long> {
    @Override
    List<Supplement> findAll();
    Optional<Supplement> findByName(String name);  // To find a supplement by name
    Optional<Supplement> findByPubChemId(Long pubChemId); // To find a supplement by PCID
    Boolean existsByName(String name);
    void deleteByName(String name);
}
