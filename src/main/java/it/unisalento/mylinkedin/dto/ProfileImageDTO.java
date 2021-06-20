package it.unisalento.mylinkedin.dto;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.entities.ProfileImage;
import it.unisalento.mylinkedin.entities.User;
import org.modelmapper.ModelMapper;

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

    public ProfileImageDTO convertToDto(ProfileImage entity) {
        ModelMapper modelMapper =  new ModelMapper();
        ProfileImageDTO dto = modelMapper.map(entity, ProfileImageDTO.class);
        dto.setPubblicationDate(entity.getPubblicationDate(), Constants.timezone);
        return dto;
    }
}
