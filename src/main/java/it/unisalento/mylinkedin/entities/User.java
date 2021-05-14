package it.unisalento.mylinkedin.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class User {

    //TODO: vedere come definire admin, offeror e applicant
    //public static String ADMIN = "admin";

    public User() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    //TODO: definire annotation attributi, relazioni, getter e setter e specializzazioni
    String name;
    String surname;
    String email;
    String password;
    Date birthDate;
    String description;
    String type;
}
