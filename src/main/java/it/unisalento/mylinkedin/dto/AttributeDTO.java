package it.unisalento.mylinkedin.dto;

import it.unisalento.mylinkedin.entities.Attribute;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AttributeDTO {

    int id;
    @NotBlank
    String title;
    @NotBlank
    String type;
    @NotNull
    Boolean required;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public AttributeDTO convertToDto(Attribute entity) {
        ModelMapper modelMapper =  new ModelMapper();
        return modelMapper.map(entity, AttributeDTO.class);
    }
}
