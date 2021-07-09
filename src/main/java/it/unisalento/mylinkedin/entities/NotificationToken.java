package it.unisalento.mylinkedin.entities;

import it.unisalento.mylinkedin.dto.NotificationTokenDTO;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Entity
public class NotificationToken {

    public NotificationToken() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String token;
    String awsEndpointArn;

    @ManyToOne(optional = false)
    User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAwsEndpointArn() {
        return awsEndpointArn;
    }

    public void setAwsEndpointArn(String awsEndpointARN) {
        this.awsEndpointArn = awsEndpointARN;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public NotificationToken convertToEntity(NotificationTokenDTO dto) {
        ModelMapper modelMapper =  new ModelMapper();

        return modelMapper.map(dto, NotificationToken.class);
    }
}
