package it.unisalento.mylinkedin.dto;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.entities.Comment;
import it.unisalento.mylinkedin.entities.User;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

public class CommentDTO{

    int id;
    @NotBlank
    String text;
    String pubblicationDate;

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

    public CommentDTO convertToDto(Comment entity) {
        ModelMapper modelMapper =  new ModelMapper();
        CommentDTO dto = modelMapper.map(entity, CommentDTO.class);
        dto.setPubblicationDate(entity.getPubblicationDate(), Constants.timezone);
        return dto;
    }
}
