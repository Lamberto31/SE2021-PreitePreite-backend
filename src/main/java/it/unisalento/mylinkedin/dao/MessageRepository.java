package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Message;
import it.unisalento.mylinkedin.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findBySenderAndReceiver(User sender, User receiver);
    List<Message> findBySender(User sender);
    List<Message> findByReceiver(User receiver);
    List<Message> findBySenderOrReceiverOrderByPubblicationDateDesc(User sender, User receiver);
    List<Message> findByReceiverAndIsRead(User receiver, boolean isRead);
    List<Message> findBySenderAndReceiverAndIsRead(User sender, User receiver, boolean isRead);

    @Modifying
    @Query("update Message m set m.isRead = :isRead where m.id = :id")
    void updateIsRead(@Param("isRead") boolean isRead, @Param("id") int id);
}
