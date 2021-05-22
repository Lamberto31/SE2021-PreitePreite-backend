package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
}
