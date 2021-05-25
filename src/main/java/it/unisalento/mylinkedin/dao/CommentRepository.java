package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Comment;
import it.unisalento.mylinkedin.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> getByPost(Post post);
}
