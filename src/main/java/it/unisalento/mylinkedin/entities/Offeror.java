package it.unisalento.mylinkedin.entities;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.OfferorDTO;
import it.unisalento.mylinkedin.dto.UserDTO;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue(Constants.TYPE_OFFEROR)
public class Offeror extends User{

    public Offeror() {}

    public Offeror(int id, String name, String surname, String email, String password, Date birthDate, String description, List<ProfileImage> profileImage, List<Message> sentMessageList, List<Message> receivedMessageList, List<Comment> commentList, List<UserInterestedPost> userInterestedPostList, List<Post> postList, Date registrationDate, String status, Company company) {
        super(id, name, surname, email, password, birthDate, description, profileImage, sentMessageList, receivedMessageList, commentList, userInterestedPostList, postList);
        this.registrationDate = registrationDate;
        this.status = status;
        this.company = company;
    }

    Date registrationDate;
    @Column(nullable = false)
    String status;

    @ManyToOne()
    Company company;

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
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

    public Offeror convertToEntity(OfferorDTO dto) throws ParseException {
        ModelMapper modelMapper =  new ModelMapper();
        Offeror entity = modelMapper.map(dto, Offeror.class);
        entity.setBirthDate(dto.getBirthDate(Constants.timezone));
        entity.setRegistrationDate(dto.getRegistrationDate(Constants.timezone));
        return entity;
    }
}