package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByPrivate(boolean isPrivate);
    //TODO: updateHiddenPost
    //TODO: filtraggio per skills
}
