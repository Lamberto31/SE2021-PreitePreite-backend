package it.unisalento.mylinkedin.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@DiscriminatorValue(User.TYPE_OFFEROR)
public class Offeror extends User{

    Date RegistrationDate;
    @Column(nullable = false)
    String status;

    @ManyToOne(optional = false)
    Company company;

    //TODO: costruttore completo, getter e setter dopo aver completato User
}