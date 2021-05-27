package it.unisalento.mylinkedin.dto;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.entities.Structure;
import it.unisalento.mylinkedin.entities.User;
import it.unisalento.mylinkedin.validators.CheckValueInListConstraint;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
public class StructureDTO {

    int id;
    @NotBlank
    String title;
    String description;
    @CheckValueInListConstraint(feasibleList = {Constants.CAN_PUBLISH_APPLICANT, Constants.CAN_PUBLISH_APPLICANT, Constants.CAN_PUBLISH_BOTH})
    String userCanPublish;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserCanPublish() {
        return userCanPublish;
    }

    public void setUserCanPublish(String userCanPublish) {
        this.userCanPublish = userCanPublish;
    }

    public StructureDTO convertToDto(Structure entity) {
        ModelMapper modelMapper =  new ModelMapper();
        return modelMapper.map(entity, StructureDTO.class);
    }
}
