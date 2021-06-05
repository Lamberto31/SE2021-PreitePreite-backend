package it.unisalento.mylinkedin.modelmapper;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.ApplicantDTO;
import it.unisalento.mylinkedin.dto.OfferorDTO;
import it.unisalento.mylinkedin.entities.Applicant;
import it.unisalento.mylinkedin.entities.Offeror;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OfferorModelmapperUnitTest {

    @Test
    public void whenConvertOfferorEntityToOfferorDto_thenCorrect() throws ParseException {

        Offeror offeror = new Offeror();
        offeror.setId(1);
        offeror.setName("testName");
        offeror.setSurname("testSurname");
        offeror.setEmail("emailtest@test.com");
        offeror.setPassword("testPassword");
        offeror.setBirthDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        offeror.setDescription("testDescription");
        offeror.setRegistrationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        offeror.setStatus(Constants.REGISTRATION_PENDING);

        OfferorDTO offerorDTO = new OfferorDTO().convertToDto(offeror);

        assertEquals(offeror.getId(), offerorDTO.getId());
        assertEquals(offeror.getName(), offerorDTO.getName());
        assertEquals(offeror.getSurname(), offerorDTO.getSurname());
        assertEquals(offeror.getEmail(), offerorDTO.getEmail());
        assertEquals(offeror.getPassword(), offerorDTO.getPassword());
        assertEquals(offeror.getBirthDate(), offerorDTO.getBirthDate(Constants.timezone));
        assertEquals(offeror.getDescription(), offerorDTO.getDescription());
        assertEquals(offeror.getRegistrationDate(), offerorDTO.getRegistrationDate(Constants.timezone));
        assertEquals(offeror.getStatus(), offerorDTO.getStatus());
    }

    @Test
    public void whenConvertOfferorDtoToOfferorEntity_thenCorrect() throws ParseException {

        OfferorDTO offerorDTO = new OfferorDTO();
        offerorDTO.setId(1);
        offerorDTO.setName("testName");
        offerorDTO.setSurname("testSurname");
        offerorDTO.setEmail("emailtest@test.com");
        offerorDTO.setPassword("testPassword");
        Date date = Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00");
        offerorDTO.setBirthDate(date, Constants.timezone);
        offerorDTO.setDescription("testDescription");
        offerorDTO.setRegistrationDate(date, Constants.timezone);
        offerorDTO.setStatus(Constants.REGISTRATION_PENDING);

        Offeror offeror = new Offeror().convertToEntity(offerorDTO);

        assertEquals(offeror.getId(), offerorDTO.getId());
        assertEquals(offeror.getName(), offerorDTO.getName());
        assertEquals(offeror.getSurname(), offerorDTO.getSurname());
        assertEquals(offeror.getEmail(), offerorDTO.getEmail());
        assertEquals(offeror.getPassword(), offerorDTO.getPassword());
        assertEquals(offeror.getBirthDate(), offerorDTO.getBirthDate(Constants.timezone));
        assertEquals(offeror.getDescription(), offerorDTO.getDescription());
        assertEquals(offeror.getRegistrationDate(), offerorDTO.getRegistrationDate(Constants.timezone));
        assertEquals(offeror.getStatus(), offerorDTO.getStatus());
    }
}
