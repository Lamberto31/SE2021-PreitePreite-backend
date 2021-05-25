package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Structure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StructureRepository extends JpaRepository<Structure, Integer> {
    List<Structure> findByUserCanPublish(String userCanPublish);
}
