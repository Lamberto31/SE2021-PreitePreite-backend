package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Structure;
import it.unisalento.mylinkedin.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}