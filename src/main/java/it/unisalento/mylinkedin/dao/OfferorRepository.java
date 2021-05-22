package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Applicant;
import it.unisalento.mylinkedin.entities.Offeror;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferorRepository extends JpaRepository<Offeror, Integer> {
    List<Offeror> findByStatus(String status);
}
