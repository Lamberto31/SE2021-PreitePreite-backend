package it.unisalento.mylinkedin.entities;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.MessageDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;

@Entity
public class Message {
    public Message() {}

    public Message(int id, String text, Date pubblicationDate, boolean isRead, User sender, User receiver) {
        this.id = id;
        this.text = text;
        this.pubblicationDate = pubblicationDate;
        this.sender = sender;
        this.receiver = receiver;
        this.isRead = isRead;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String text;
    String imagePath;
    Date pubblicationDate;
    boolean isRead;

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

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
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

    public Message convertToEntity(MessageDTO dto) throws ParseException {
        ModelMapper modelMapper =  new ModelMapper();
        modelMapper.addMappings(new PropertyMap<MessageDTO, Message>() {
            @Override
            protected void configure() {
                skip(destination.getPubblicationDate());
            }
        });

        Message entity = modelMapper.map(dto, Message.class);
        try {
            entity.setPubblicationDate(dto.getPubblicationDate(Constants.timezone));
        } catch (Exception e) {
            entity.setPubblicationDate(null);
        }
        return entity;
    }
}
