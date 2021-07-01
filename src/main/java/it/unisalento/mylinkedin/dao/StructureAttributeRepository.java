package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Attribute;
import it.unisalento.mylinkedin.entities.StructureAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StructureAttributeRepository extends JpaRepository<StructureAttribute, Integer> {
    @Query("select s.attribute from StructureAttribute s where s.structure.id= :structureId")
    List<Attribute> findAttributeByStructure(int structureId);
}
