package it.unisalento.mylinkedin.dto;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

public class ApplicantDTO extends UserDTO{

    String registrationDate;
    String status;
    String fixedAttributes; //JSON

    public Date getRegistrationDate(String timezone) throws ParseException {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return dateFormat.parse(this.registrationDate);
    }

    public void setRegistrationDate(Date date, String timezone) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.registrationDate = dateFormat.format(date);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFixedAttributes() {
        return fixedAttributes;
    }

    public void setFixedAttributes(String fixedAttributes) {
        this.fixedAttributes = fixedAttributes;
    }
}
