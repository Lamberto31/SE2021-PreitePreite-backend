package it.unisalento.mylinkedin.dto;

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
}
