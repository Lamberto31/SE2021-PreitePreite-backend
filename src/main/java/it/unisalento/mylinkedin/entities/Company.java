package it.unisalento.mylinkedin.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {

    public Company() { }

    public Company(int id, String name, String description, String p_iva, String address, List<ProfileImage> profileImageList, List<Offeror> offerorList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.p_iva = p_iva;
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
    String p_iva;
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

    public String getP_iva() {
        return p_iva;
    }

    public void setP_iva(String p_iva) {
        this.p_iva = p_iva;
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
}
