package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Applicant;
import it.unisalento.mylinkedin.entities.Offeror;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferorRepository extends JpaRepository<Offeror, Integer> {
    List<Offeror> findByStatus(String status);

    @Modifying
    @Query("update Offeror o set o.status = :status where o.id = :id")
    void updateStatusRegistration(@Param("status") String status, @Param("id") int id);
}
