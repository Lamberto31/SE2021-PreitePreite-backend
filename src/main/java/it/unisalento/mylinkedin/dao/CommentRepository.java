package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Attribute;
import it.unisalento.mylinkedin.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
