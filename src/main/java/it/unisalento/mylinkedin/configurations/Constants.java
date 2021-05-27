package it.unisalento.mylinkedin.configurations;

import java.text.SimpleDateFormat;

public interface Constants {

    //Variabili statiche per tipo utente
    String TYPE_ADMIN = "admin";
    String TYPE_OFFEROR = "offeror";
    String TYPE_APPLICANT = "applicant";

    //Variabili statiche per stato registrazione
    String REGISTRATION_PENDING = "pending";
    String REGISTRATION_ACCEPTED = "accepted";
    String REGISTRATION_BLOCKED = "blocked";

    //Variabili statiche per tipo utente
    String CAN_PUBLISH_OFFEROR = "offeror";
    String CAN_PUBLISH_APPLICANT = "applicant";
    String CAN_PUBLISH_BOTH = "both";

    //Formato data
    String DATE_FORMAT = "dd/MM/yyyy HH:mm";
    SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT);
    String timezone = "Europe/Rome";
}
