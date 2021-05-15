package it.unisalento.mylinkedin.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(User.TYPE_ADMIN)
public class Administrator extends User{
    //TODO: getter e setter dopo aver completato User
}