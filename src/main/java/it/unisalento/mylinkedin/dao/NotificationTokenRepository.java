package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.NotificationToken;
import it.unisalento.mylinkedin.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationTokenRepository extends JpaRepository<NotificationToken, Integer> {

    List<NotificationToken> findByUser(User user);
    NotificationToken findByToken(String token);
}
