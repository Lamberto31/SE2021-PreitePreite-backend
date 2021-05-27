package it.unisalento.mylinkedin.dto;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.entities.Post;
import it.unisalento.mylinkedin.entities.User;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

public class PostDTO {

    int id;
    String pubblicationDate;
    boolean ishidden;
    boolean isPrivate;
    @NotBlank
    String data; //JSON

    public Date getPubblicationDate(String timezone) throws ParseException {
        Constants.SIMPLE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(timezone));
        return Constants.SIMPLE_DATE_FORMAT.parse(this.pubblicationDate);
    }

    public void setPubblicationDate(Date date, String timezone) {
        Constants.SIMPLE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(timezone));
        this.pubblicationDate = Constants.SIMPLE_DATE_FORMAT.format(date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIshidden() {
        return ishidden;
    }

    public void setIshidden(boolean ishidden) {
        this.ishidden = ishidden;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public PostDTO convertToDto(Post entity) {
        ModelMapper modelMapper =  new ModelMapper();
        PostDTO dto = modelMapper.map(entity, PostDTO.class);
        dto.setPubblicationDate(entity.getPubblicationDate(), Constants.timezone);
        return dto;
    }
}
