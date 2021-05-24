package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Comment;
import it.unisalento.mylinkedin.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> getByPost(Post post);
}
