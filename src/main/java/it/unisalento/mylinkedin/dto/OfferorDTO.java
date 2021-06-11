package it.unisalento.mylinkedin.dto;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.entities.Applicant;
import it.unisalento.mylinkedin.entities.Offeror;
import it.unisalento.mylinkedin.validators.CheckValueInListConstraint;
import org.modelmapper.ModelMapper;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

public class OfferorDTO extends UserDTO{

    String registrationDate;
    @CheckValueInListConstraint(feasibleList = {Constants.REGISTRATION_PENDING, Constants.REGISTRATION_ACCEPTED, Constants.REGISTRATION_BLOCKED})
    String status;

    public Date getRegistrationDate(String timezone) throws ParseException {
        Constants.SIMPLE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(timezone));
        return Constants.SIMPLE_DATE_FORMAT.parse(this.registrationDate);
    }

    public void setRegistrationDate(Date date, String timezone) {
        Constants.SIMPLE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(timezone));
        try {
            this.registrationDate = Constants.SIMPLE_DATE_FORMAT.format(date);
        } catch (Exception e) {
            this.registrationDate = null;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OfferorDTO convertToDto(Offeror entity) {
        ModelMapper modelMapper =  new ModelMapper();
        OfferorDTO dto = modelMapper.map(entity, OfferorDTO.class);
        dto.setBirthDate(entity.getBirthDate(), Constants.timezone);
        dto.setRegistrationDate(entity.getRegistrationDate(), Constants.timezone);
        return dto;
    }
}
