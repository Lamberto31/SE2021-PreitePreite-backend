package it.unisalento.mylinkedin.dto;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.entities.Message;
import it.unisalento.mylinkedin.entities.User;
import it.unisalento.mylinkedin.validators.AtLeastOneNotNullConstraint;
import org.modelmapper.ModelMapper;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

@AtLeastOneNotNullConstraint(fields = {"text", "imagePath"})
public class MessageDTO {

    int id;
    String text;
    String imagePath;
    String pubblicationDate;

    public Date getPubblicationDate(String timezone) throws ParseException {
        Constants.SIMPLE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(timezone));
        return Constants.SIMPLE_DATE_FORMAT.parse(this.pubblicationDate);
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

    public MessageDTO convertToDto(Message entity) {
        ModelMapper modelMapper =  new ModelMapper();
        MessageDTO dto = modelMapper.map(entity, MessageDTO.class);
        dto.setPubblicationDate(entity.getPubblicationDate(), Constants.timezone);
        return dto;
    }
}
