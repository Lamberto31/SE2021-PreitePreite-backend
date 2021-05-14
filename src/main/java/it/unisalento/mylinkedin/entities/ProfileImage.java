package it.unisalento.mylinkedin.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ProfileImage {

    public ProfileImage() {}

    public ProfileImage(int id, String description, Date pubblicationDate, User user, Company company) {
        this.id = id;
        this.description = description;
        this.pubblicationDate = pubblicationDate;
        this.user = user;
        this.company = company;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 500)
    String description;
    Date pubblicationDate;
    //TODO: image path o blob?
    @ManyToOne()
    User user;
    @ManyToOne()
    Company company;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPubblicationDate() {
        return pubblicationDate;
    }

    public void setPubblicationDate(Date pubblicationDate) {
        this.pubblicationDate = pubblicationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
