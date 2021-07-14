package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Post;
import it.unisalento.mylinkedin.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByIsPrivateAndIsHidden(boolean isPrivate, boolean isHidden);

    @Modifying
    @Query("update Post p set p.isHidden = :isHidden where p.id = :id")
    void updateIsHidden(@Param("isHidden") boolean isHidden, @Param("id") int id);

    @Query("select p.user from Post p where p.id=:postId ")
    User findUserById(int postId);

    List<Post> findAllByOrderByPubblicationDateDesc();

    //TODO: filtraggio per skills
}
