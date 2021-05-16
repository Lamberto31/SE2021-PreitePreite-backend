package it.unisalento.mylinkedin.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Structure {

    //Variabili statiche per tipo utente
    public final static String CAN_PUBLISH_OFFEROR = "offeror";
    public final static String CAN_PUBLISH_APPLICANT = "applicant";
    public final static String CAN_PUBLISH_BOTH = "both";

    public Structure() {}

    public Structure(int id, String title, String description, String userCanPublish, List<Post> post, List<StructureAttribute> structureAttributeList) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userCanPublish = userCanPublish;
        this.post = post;
        StructureAttributeList = structureAttributeList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;
    String description;
    String userCanPublish;

    @OneToMany //TODO: mappedby e altri
    List<Post> post;
    @OneToMany //TODO: mappedby e altri
    List<StructureAttribute> StructureAttributeList;

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

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    public List<StructureAttribute> getStructureAttributeList() {
        return StructureAttributeList;
    }

    public void setStructureAttributeList(List<StructureAttribute> structureAttributeList) {
        StructureAttributeList = structureAttributeList;
    }
}
