package it.unisalento.mylinkedin.dto;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.entities.Post;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.TimeZone;

public class PostDTO {

    int id;
    String title;
    String pubblicationDate;
    boolean isHidden;
    boolean isPrivate;
    @NotBlank
    String data; //JSON

    StructureDTO structure;
    UserDTO user;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        this.isHidden = hidden;
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

    public StructureDTO getStructure() {
        return structure;
    }

    public void setStructure(StructureDTO structure) {
        this.structure = structure;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public PostDTO convertToDto(Post entity) {
        ModelMapper modelMapper =  new ModelMapper();
        PostDTO dto = modelMapper.map(entity, PostDTO.class);
        dto.setPubblicationDate(entity.getPubblicationDate(), Constants.timezone);
        return dto;
    }
}
