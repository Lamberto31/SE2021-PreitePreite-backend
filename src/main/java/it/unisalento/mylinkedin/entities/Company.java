package it.unisalento.mylinkedin.entities;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.CommentDTO;
import it.unisalento.mylinkedin.dto.CompanyDTO;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.text.ParseException;
import java.util.List;

@Entity
public class Company {

    public Company() { }

    public Company(int id, String name, String description, String partitaIva, String address, List<ProfileImage> profileImageList, List<Offeror> offerorList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.partitaIva = partitaIva;
        this.address = address;
        this.profileImageList = profileImageList;
        this.offerorList = offerorList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true, nullable = false)
    String name;
    @Column(length = 500)
    String description;
    @Column(unique = true, nullable = false ,length = 11)
    String partitaIva;
    String address;

    @OneToMany(mappedBy = "company", targetEntity = ProfileImage.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<ProfileImage> profileImageList;
    @OneToMany(mappedBy = "company", targetEntity = Offeror.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Offeror> offerorList;

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

    public void setPartitaIva(String p_iva) {
        this.partitaIva = p_iva;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ProfileImage> getProfileImageList() {
        return profileImageList;
    }

    public void setProfileImageList(List<ProfileImage> profileImageList) {
        this.profileImageList = profileImageList;
    }

    public List<Offeror> getOfferorList() {
        return offerorList;
    }

    public void setOfferorList(List<Offeror> offerorList) {
        this.offerorList = offerorList;
    }

    public Company convertToEntity(CompanyDTO dto) {
        ModelMapper modelMapper =  new ModelMapper();
        return modelMapper.map(dto, Company.class);
    }
}
