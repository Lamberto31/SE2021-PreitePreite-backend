package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Attribute;
import it.unisalento.mylinkedin.entities.Structure;
import it.unisalento.mylinkedin.entities.StructureAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StructureAttributeRepository extends JpaRepository<StructureAttribute, Integer> {
    List<Attribute> findAttributeByStructure(Structure structure);
}
