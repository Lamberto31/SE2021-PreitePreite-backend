package it.unisalento.mylinkedin.dto;

import it.unisalento.mylinkedin.configurations.Constants;

import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

public class CommentDTO{

    int id;
    @NotBlank
    String text;
    String pubblicationDate;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
