package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Offeror;
import it.unisalento.mylinkedin.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}