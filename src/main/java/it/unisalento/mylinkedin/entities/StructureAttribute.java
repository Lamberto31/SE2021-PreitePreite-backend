package it.unisalento.mylinkedin.entities;

import javax.persistence.*;

@Entity
public class StructureAttribute {

    public StructureAttribute() {}

    public StructureAttribute(int id, Structure structure, Attribute attribute) {
        this.id = id;
        this.structure = structure;
        this.attribute = attribute;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    Structure structure;
    @ManyToOne
    Attribute attribute;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
}
