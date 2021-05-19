package it.unisalento.mylinkedin.dto;

import it.unisalento.mylinkedin.configurations.Constants;

import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CommentDTO{
    protected static final SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);

    int id;
    @NotBlank
    String text;
    String pubblicationDate;

    public Date getPubblicationDate(String timezone) throws ParseException {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return dateFormat.parse(this.pubblicationDate);
    }

    public void setPubblicationDate(Date date, String timezone) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.pubblicationDate = dateFormat.format(date);
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
