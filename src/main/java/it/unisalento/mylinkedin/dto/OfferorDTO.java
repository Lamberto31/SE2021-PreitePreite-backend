package it.unisalento.mylinkedin.dto;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.entities.Offeror;
import it.unisalento.mylinkedin.validators.CheckValueInListConstraint;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.TimeZone;

public class OfferorDTO extends UserDTO{

    String registrationDate;
    @CheckValueInListConstraint(feasibleList = {Constants.REGISTRATION_PENDING, Constants.REGISTRATION_ACCEPTED, Constants.REGISTRATION_BLOCKED})
    String status;

    CompanyDTO company;

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getRegistrationDate(String timezone) {
        Constants.SIMPLE_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone(timezone));
        try {
            return Constants.SIMPLE_DATE_FORMAT.parse(this.registrationDate);
        } catch (Exception e) {
            return null;
        }
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

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    public OfferorDTO convertToDto(Offeror entity) {
        ModelMapper modelMapper =  new ModelMapper();
        OfferorDTO dto = modelMapper.map(entity, OfferorDTO.class);
        dto.setBirthDate(entity.getBirthDate(), Constants.timezone);
        dto.setRegistrationDate(entity.getRegistrationDate(), Constants.timezone);
        return dto;
    }
}
