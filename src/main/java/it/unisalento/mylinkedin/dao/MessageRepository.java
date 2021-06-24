package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Message;
import it.unisalento.mylinkedin.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findBySenderAndReceiver(User sender, User receiver);
    List<Message> findBySender(User sender);
    List<Message> findByReceiver(User receiver);
    List<Message> findBySenderOrReceiverOrderByPubblicationDateDesc(User sender, User receiver);
}
