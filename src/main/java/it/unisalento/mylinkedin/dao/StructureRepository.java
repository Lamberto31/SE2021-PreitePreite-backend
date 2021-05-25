package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StructureRepository extends JpaRepository<Structure, Integer> {
    List<Structure> getByUserCanPublish(String userCanPublish);
}
