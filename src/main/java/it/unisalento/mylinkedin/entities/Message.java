package it.unisalento.mylinkedin.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {
    public Message() {}

    public Message(int id, String text, Date pubblicationDate, User sender, User receiver) {
        this.id = id;
        this.text = text;
        this.pubblicationDate = pubblicationDate;
        this.sender = sender;
        this.receiver = receiver;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String text;
    @Column(unique = true, nullable = false)
    String imagePath;
    Date pubblicationDate;

    @ManyToOne(optional = false)
    User sender;
    @ManyToOne(optional = false)
    User receiver;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getPubblicationDate() {
        return pubblicationDate;
    }

    public void setPubblicationDate(Date pubblicationDate) {
        this.pubblicationDate = pubblicationDate;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
