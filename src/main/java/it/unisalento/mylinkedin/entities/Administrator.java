package it.unisalento.mylinkedin.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue(User.TYPE_ADMIN)
public class Administrator extends User{

    public Administrator() {}

    public Administrator(int id, String name, String surname, String email, String password, Date birthDate, String description, List<ProfileImage> profileImage, List<Message> sentMessageList, List<Message> receivedMessageList, List<Comment> commentList, List<UserInterestedPost> userInterestedPostList, List<Post> postList) {
        super(id, name, surname, email, password, birthDate, description, profileImage, sentMessageList, receivedMessageList, commentList, userInterestedPostList, postList);
    }
}