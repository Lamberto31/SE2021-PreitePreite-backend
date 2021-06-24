package it.unisalento.mylinkedin.dto;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.entities.Message;
import it.unisalento.mylinkedin.validators.AtLeastOneNotNullConstraint;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.TimeZone;

@AtLeastOneNotNullConstraint(fields = {"text", "imagePath"})
public class MessageDTO {

    int id;
    String text;
    String imagePath;
    String pubblicationDate;
    boolean isRead;

    UserDTO sender;
    UserDTO receiver;

    public String getPubblicationDate() {
        return pubblicationDate;
    }

    public void setPubblicationDate(String pubblicationDate) {
        this.pubblicationDate = pubblicationDate;
    }

    public Date getPubblicationDate(String timezone) {
        Constants.SIMPLE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(timezone));
        try {
            return Constants.SIMPLE_DATE_FORMAT.parse(this.pubblicationDate);
        } catch (Exception e) {
            return null;
        }
    }

    public void setPubblicationDate(Date date, String timezone) {
        Constants.SIMPLE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(timezone));
        try {
            this.pubblicationDate = Constants.SIMPLE_DATE_FORMAT.format(date);
        } catch (Exception e) {
            this.pubblicationDate = null;
        }
    }

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

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public UserDTO getSender() {
        return sender;
    }

    public void setSender(UserDTO sender) {
        this.sender = sender;
    }

    public UserDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(UserDTO receiver) {
        this.receiver = receiver;
    }

    public MessageDTO convertToDto(Message entity) {
        ModelMapper modelMapper =  new ModelMapper();
        MessageDTO dto = modelMapper.map(entity, MessageDTO.class);
        dto.setPubblicationDate(entity.getPubblicationDate(), Constants.timezone);
        return dto;
    }
}
