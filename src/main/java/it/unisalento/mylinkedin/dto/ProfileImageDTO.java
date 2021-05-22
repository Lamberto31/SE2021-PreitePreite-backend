package it.unisalento.mylinkedin.dto;

import it.unisalento.mylinkedin.configurations.Constants;

import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

public class ProfileImageDTO {

    int id;
    String description;
    String pubblicationDate;
    @NotBlank
    String imagePath;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
