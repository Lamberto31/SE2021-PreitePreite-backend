package it.unisalento.mylinkedin.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue(User.TYPE_APPLICANT)
public class Applicant extends User{

    Date RegistrationDate;
    @Column(nullable = false)
    String status;
    @Column(length = 1000)
    String fixedAttributes;

    //TODO: costruttore completo, getter e setter dopo aver completato User
}
