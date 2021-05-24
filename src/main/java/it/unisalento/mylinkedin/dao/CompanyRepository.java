package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
