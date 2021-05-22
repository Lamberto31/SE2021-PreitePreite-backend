package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
    List<Applicant> findByStatus(String status);

    @Modifying
    @Query("update Applicant a set a.status = :status where a.id = :id")
    void updateStatusRegistration(@Param("status") String status, @Param("id") int id);
}
