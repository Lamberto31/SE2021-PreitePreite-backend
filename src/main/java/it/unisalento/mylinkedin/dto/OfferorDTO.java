package it.unisalento.mylinkedin.dto;

import it.unisalento.mylinkedin.configurations.Constants;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

public class OfferorDTO extends UserDTO{

    String registrationDate;
    String status;

    public Date getRegistrationDate(String timezone) throws ParseException {
        Constants.SIMPLE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(timezone));
        return Constants.SIMPLE_DATE_FORMAT.parse(this.registrationDate);
    }

    public void setRegistrationDate(Date date, String timezone) {
        Constants.SIMPLE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(timezone));
        this.registrationDate = Constants.SIMPLE_DATE_FORMAT.format(date);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
