package it.unisalento.mylinkedin.dto;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.entities.Comment;
import it.unisalento.mylinkedin.entities.Company;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CompanyDTO {

    int id;
    @NotBlank
    String name;
    String description;
    @NotBlank
    @Size(min = 11, max = 11)
    String partitaIva;
    String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CompanyDTO convertToDto(Company entity) {
        ModelMapper modelMapper =  new ModelMapper();
        return modelMapper.map(entity, CompanyDTO.class);
    }
}
