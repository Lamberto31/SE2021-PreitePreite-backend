package it.unisalento.mylinkedin.entities;

import it.unisalento.mylinkedin.configurations.Constants;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue(Constants.TYPE_APPLICANT)
public class Applicant extends User{

    public Applicant() {}

    public Applicant(int id, String name, String surname, String email, String password, Date birthDate, String description, List<ProfileImage> profileImage, List<Message> sentMessageList, List<Message> receivedMessageList, List<Comment> commentList, List<UserInterestedPost> userInterestedPostList, List<Post> postList, Date registrationDate, String status, String fixedAttributes) {
        super(id, name, surname, email, password, birthDate, description, profileImage, sentMessageList, receivedMessageList, commentList, userInterestedPostList, postList);
        RegistrationDate = registrationDate;
        this.status = status;
        this.fixedAttributes = fixedAttributes;
    }

    Date RegistrationDate;
    @Column(nullable = false)
    String status;
    @Column(length = 1000) //JSON
    String fixedAttributes;

    public Date getRegistrationDate() {
        return RegistrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        RegistrationDate = registrationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFixedAttributes() {
        return fixedAttributes;
    }

    public void setFixedAttributes(String fixedAttributes) {
        this.fixedAttributes = fixedAttributes;
    }
}
