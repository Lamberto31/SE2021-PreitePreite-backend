package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
    List<Applicant> findByStatus(String status);
    //TODO: updateStatusRegistrationUser e copiare in Offeror
}
