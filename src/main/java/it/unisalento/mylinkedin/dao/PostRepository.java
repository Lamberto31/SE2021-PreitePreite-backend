package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByIsPrivate(boolean isPrivate);

    @Modifying
    @Query("update Post p set p.isHidden = :isHidden where p.id = :id")
    void updateIsHidden(@Param("isHidden") boolean isHidden, @Param("id") int id);

    //TODO: filtraggio per skills
}
