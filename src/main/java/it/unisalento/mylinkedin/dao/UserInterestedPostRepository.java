package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInterestedPostRepository extends JpaRepository<UserInterestedPost, Integer> {
    List<User> findUserByPost(Post post);
}
