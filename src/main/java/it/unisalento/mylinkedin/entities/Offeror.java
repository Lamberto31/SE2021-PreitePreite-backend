package it.unisalento.mylinkedin.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue(User.TYPE_OFFEROR)
public class Offeror extends User{

    public Offeror() {}

    public Offeror(int id, String name, String surname, String email, String password, Date birthDate, String description, List<ProfileImage> profileImage, List<Message> sentMessageList, List<Message> receivedMessageList, List<Comment> commentList, List<UserInterestedPost> userInterestedPostList, List<Post> postList, Date registrationDate, String status, Company company) {
        super(id, name, surname, email, password, birthDate, description, profileImage, sentMessageList, receivedMessageList, commentList, userInterestedPostList, postList);
        RegistrationDate = registrationDate;
        this.status = status;
        this.company = company;
    }

    Date RegistrationDate;
    @Column(nullable = false)
    String status;

    @ManyToOne(optional = false)
    Company company;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}