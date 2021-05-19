package it.unisalento.mylinkedin.dto;

import javax.validation.constraints.NotBlank;
//TODO: validator per vedere se userCanPublish è uguale ad uno dei valori ammissibili
public class StructureDTO {

    int id;
    @NotBlank
    String title;
    String description;
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
}
