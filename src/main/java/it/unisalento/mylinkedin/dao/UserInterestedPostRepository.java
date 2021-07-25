package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.User;
import it.unisalento.mylinkedin.entities.UserInterestedPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInterestedPostRepository extends JpaRepository<UserInterestedPost, Integer> {
    @Query("select u.user from UserInterestedPost u where u.post.id= :postId")
    List<User> findUserByPost(int postId);

    List<UserInterestedPost> findByIsAlreadyNotified(boolean alreadyNotified);
}
