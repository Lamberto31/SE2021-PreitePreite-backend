package it.unisalento.mylinkedin.entities;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.MessageDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import javax.persistence.*;
import java.text.ParseException;

@Entity
public class NotificationToken {

    public NotificationToken() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String token;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public NotificationToken convertToEntity(NotificationTokenDTO dto) {
        ModelMapper modelMapper =  new ModelMapper();

        NotificationToken entity = modelMapper.map(dto, NotificationToken.class);
        return entity;
    }
}
