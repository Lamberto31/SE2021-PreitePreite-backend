package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.Post;
import it.unisalento.mylinkedin.entities.Structure;
import it.unisalento.mylinkedin.entities.StructureAttribute;
import it.unisalento.mylinkedin.entities.UserInterestedPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInterestedPostRepository extends JpaRepository<UserInterestedPost, Integer> {
    List<UserInterestedPost> findUserByPost(Post post);
}
