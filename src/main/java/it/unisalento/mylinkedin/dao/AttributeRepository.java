package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, Integer> {
}
