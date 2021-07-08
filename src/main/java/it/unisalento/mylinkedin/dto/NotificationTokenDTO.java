package it.unisalento.mylinkedin.dto;

import it.unisalento.mylinkedin.entities.NotificationToken;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;

public class NotificationTokenDTO {

    int id;
    @NotBlank
    String token;

    UserDTO user;

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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public NotificationTokenDTO convertToDto(NotificationToken entity) {
        ModelMapper modelMapper =  new ModelMapper();
        return modelMapper.map(entity, NotificationTokenDTO.class);
    }
}