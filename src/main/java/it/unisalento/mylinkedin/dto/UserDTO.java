package it.unisalento.mylinkedin.dto;

import it.unisalento.mylinkedin.configurations.Constants;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

//TODO: Fare validator per confronto email e password
public class UserDTO {
    protected static final SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);

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
    String type;

    @NotBlank
    @Email
    String emailToVerify;
    @NotBlank
    String passwordToVerify;

    public Date getBirthDate(String timezone) throws ParseException {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return dateFormat.parse(this.birthDate);
    }

    public void setBirthDate(Date date, String timezone) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.birthDate = dateFormat.format(date);
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
}
