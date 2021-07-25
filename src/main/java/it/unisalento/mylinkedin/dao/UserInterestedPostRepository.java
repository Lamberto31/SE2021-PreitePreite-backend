package it.unisalento.mylinkedin.dao;

import it.unisalento.mylinkedin.entities.User;
import it.unisalento.mylinkedin.entities.UserInterestedPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInterestedPostRepository extends JpaRepository<UserInterestedPost, Integer> {
    @Query("select u.user from UserInterestedPost u where u.post.id= :postId")
    List<User> findUserByPost(int postId);

    List<UserInterestedPost> findByIsNotified(boolean isNotified);

    @Modifying
    @Query("update UserInterestedPost u set u.isNotified = :isNotified where u.id = :id")
    void updateIsNotified(@Param("isNotified") boolean isNotified, @Param("id") int id);
}
