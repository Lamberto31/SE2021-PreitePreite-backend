package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Company;
import it.unisalento.mylinkedin.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
