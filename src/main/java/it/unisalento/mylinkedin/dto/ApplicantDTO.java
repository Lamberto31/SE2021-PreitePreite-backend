package it.unisalento.mylinkedin.dto;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.entities.Applicant;
import it.unisalento.mylinkedin.entities.User;
import it.unisalento.mylinkedin.validators.CheckValueInListConstraint;
import org.modelmapper.ModelMapper;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

public class ApplicantDTO extends UserDTO{

    String registrationDate;
    @CheckValueInListConstraint(feasibleList = {Constants.REGISTRATION_PENDING, Constants.REGISTRATION_ACCEPTED, Constants.REGISTRATION_BLOCKED})
    String status;
    String fixedAttributes; //JSON

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

    public String getFixedAttributes() {
        return fixedAttributes;
    }

    public void setFixedAttributes(String fixedAttributes) {
        this.fixedAttributes = fixedAttributes;
    }

    public ApplicantDTO convertToDto(Applicant entity) {
        ModelMapper modelMapper =  new ModelMapper();
        ApplicantDTO dto = modelMapper.map(entity, ApplicantDTO.class);
        dto.setBirthDate(entity.getBirthDate(), Constants.timezone);
        dto.setRegistrationDate(entity.getRegistrationDate(), Constants.timezone);
        return dto;
    }
}
