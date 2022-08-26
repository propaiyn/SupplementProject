package com.qa.SupplementProject.Supplement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SupplementRepository
        extends CrudRepository<Supplement, Long> {
    static void save(List<Supplement> getAllSupplements) {
    }
    @Override
    List<Supplement> findAll();
    Optional<Supplement> findByName(String name);

    Optional<Supplement> findById(Long id);

    Optional<Supplement> findByPubChemId(Long pubChemId);
    
    List<Supplement> findAllByOrderByNameAsc();

    List<Supplement> findAllByOrderByIdAsc();

    Boolean existsByName(String name);

    void deleteByName(String name);
}
