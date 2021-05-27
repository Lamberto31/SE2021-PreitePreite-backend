package it.unisalento.mylinkedin.dto;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.entities.User;
import it.unisalento.mylinkedin.validators.CheckValueInListConstraint;
import it.unisalento.mylinkedin.validators.MatchTwoFieldsConstraint;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

@MatchTwoFieldsConstraint(field = "email", fieldMatch = "emailToVerify")
@MatchTwoFieldsConstraint(field = "password", fieldMatch = "passwordToVerify")
public class UserDTO {

    int id;
    @NotBlank
    String name;
    @NotBlank
    String surname;
    @NotBlank
    @Email
    String email;
    @NotBlank
    String password;
    @NotBlank
    String birthDate;
    String description;
    @NotBlank
    @CheckValueInListConstraint(feasibleList = {Constants.TYPE_ADMIN, Constants.TYPE_APPLICANT, Constants.TYPE_OFFEROR})
    String type;

    @NotBlank
    @Email
    String emailToVerify;
    @NotBlank
    String passwordToVerify;

    public Date getBirthDate(String timezone) throws ParseException {
        Constants.SIMPLE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(timezone));
        return Constants.SIMPLE_DATE_FORMAT.parse(this.birthDate);
    }

    public void setBirthDate(Date date, String timezone) {
        Constants.SIMPLE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(timezone));
        this.birthDate = Constants.SIMPLE_DATE_FORMAT.format(date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmailToVerify() {
        return emailToVerify;
    }

    public void setEmailToVerify(String emailToVerify) {
        this.emailToVerify = emailToVerify;
    }

    public String getPasswordToVerify() {
        return passwordToVerify;
    }

    public void setPasswordToVerify(String passwordToVerify) {
        this.passwordToVerify = passwordToVerify;
    }

    public UserDTO convertToDto(User user) {
        ModelMapper modelMapper =  new ModelMapper();
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setBirthDate(user.getBirthDate(), Constants.timezone);
        return userDTO;
    }
}
