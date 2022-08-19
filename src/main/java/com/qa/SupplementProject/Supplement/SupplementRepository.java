package com.qa.SupplementProject.Supplement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplementRepository
        extends JpaRepository<Supplement, Long> {
    Optional<Supplement> findByName(String name);  // To find a supplement by name
    Optional<Supplement> findByPubChemID(Long pubChemID); // To find a supplement by PCID

    Boolean existsByName(String name);
    public void deleteByName(String name);
}
