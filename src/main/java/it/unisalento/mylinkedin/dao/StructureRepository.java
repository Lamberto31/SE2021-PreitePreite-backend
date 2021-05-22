package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Post;
import it.unisalento.mylinkedin.entities.Structure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StructureRepository extends JpaRepository<Structure, Integer> {
}
