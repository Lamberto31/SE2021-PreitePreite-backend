package it.unisalento.mylinkedin.dto;

import it.unisalento.mylinkedin.entities.Attribute;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;

public class AttributeDTO {

    int id;
    @NotBlank
    String title;
    @NotBlank
    String type;

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

    public AttributeDTO convertToDto(Attribute entity) {
        ModelMapper modelMapper =  new ModelMapper();
        return modelMapper.map(entity, AttributeDTO.class);
    }
}
