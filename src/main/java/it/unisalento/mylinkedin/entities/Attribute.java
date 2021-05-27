package it.unisalento.mylinkedin.entities;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.AttributeDTO;
import it.unisalento.mylinkedin.dto.UserDTO;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.text.ParseException;
import java.util.List;

@Entity
public class Attribute {

    public Attribute() {}

    public Attribute(int id, String title, String type, List<StructureAttribute> structureAttributeList) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.structureAttributeList = structureAttributeList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;
    String type;

    @OneToMany(mappedBy = "attribute", targetEntity = StructureAttribute.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<StructureAttribute> structureAttributeList;

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

    public List<StructureAttribute> getStructureAttributeList() {
        return structureAttributeList;
    }

    public void setStructureAttributeList(List<StructureAttribute> structureAttributeList) {
        this.structureAttributeList = structureAttributeList;
    }

    public Attribute convertToEntity(AttributeDTO dto) {
        ModelMapper modelMapper =  new ModelMapper();
        return modelMapper.map(dto, Attribute.class);
    }
}
