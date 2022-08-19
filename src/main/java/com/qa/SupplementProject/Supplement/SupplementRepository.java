package com.qa.SupplementProject.Supplement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplementRepository
        extends JpaRepository<Supplement, Long> {
}
