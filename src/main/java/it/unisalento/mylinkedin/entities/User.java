package it.unisalento.mylinkedin.entities;

import javax.persistence.*;
import java.util.Date;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class User {

    //Variabili statiche per tipo utente
    public final static String TYPE_ADMIN = "admin";
    public final static String TYPE_OFFEROR = "offeror";
    public final static String TYPE_APPLICANT = "applicant";

    //Variabili statiche per stato registrazione
    public final static String REGISTRATION_PENDING = "pending";
    public final static String REGISTRATION_ACCEPTED = "accepted";
    public final static String REGISTRATION_BLOCKED = "blocked";


    public User() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String surname;
    @Column(unique = true, nullable = false)
    String email;
    @Column(nullable = false)
    String password;
    Date birthDate;
    @Column(length = 500)
    String description;

    //TODO: relazioni, costruttore, getter e setter e specializzazioni
}
