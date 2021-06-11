package it.unisalento.mylinkedin.modelmapper;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.ApplicantDTO;
import it.unisalento.mylinkedin.entities.Applicant;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicantModelmapperUnitTest {

    @Test
    public void whenConvertApplicantEntityToApplicantDto_thenCorrect() throws ParseException {

        Applicant applicant = new Applicant();
        applicant.setId(1);
        applicant.setName("testName");
        applicant.setSurname("testSurname");
        applicant.setEmail("emailtest@test.com");
        applicant.setPassword("testPassword");
        applicant.setBirthDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        applicant.setDescription("testDescription");
        applicant.setRegistrationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        applicant.setStatus(Constants.REGISTRATION_PENDING);
        applicant.setFixedAttributes("testFixedAttributes");

        ApplicantDTO applicantDTO = new ApplicantDTO().convertToDto(applicant);

        assertEquals(applicant.getId(), applicantDTO.getId());
        assertEquals(applicant.getName(), applicantDTO.getName());
        assertEquals(applicant.getSurname(), applicantDTO.getSurname());
        assertEquals(applicant.getEmail(), applicantDTO.getEmail());
        assertEquals(applicant.getPassword(), applicantDTO.getPassword());
        assertEquals(applicant.getBirthDate(), applicantDTO.getBirthDate(Constants.timezone));
        assertEquals(applicant.getDescription(), applicantDTO.getDescription());
        assertEquals(applicant.getRegistrationDate(), applicantDTO.getRegistrationDate(Constants.timezone));
        assertEquals(applicant.getStatus(), applicantDTO.getStatus());
        assertEquals(applicant.getFixedAttributes(), applicantDTO.getFixedAttributes());
    }

    @Test
    public void whenConvertApplicantDtoToApplicantEntity_thenCorrect() throws ParseException {

        ApplicantDTO applicantDTO = new ApplicantDTO();
        applicantDTO.setId(1);
        applicantDTO.setName("testName");
        applicantDTO.setSurname("testSurname");
        applicantDTO.setEmail("emailtest@test.com");
        applicantDTO.setPassword("testPassword");
        Date date = Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00");
        applicantDTO.setBirthDate(date, Constants.timezone);
        applicantDTO.setDescription("testDescription");
        applicantDTO.setRegistrationDate(date, Constants.timezone);
        applicantDTO.setStatus(Constants.REGISTRATION_PENDING);
        applicantDTO.setFixedAttributes("testFixedAttributes");

        Applicant applicant = new Applicant().convertToEntity(applicantDTO);

        assertEquals(applicant.getId(), applicantDTO.getId());
        assertEquals(applicant.getName(), applicantDTO.getName());
        assertEquals(applicant.getSurname(), applicantDTO.getSurname());
        assertEquals(applicant.getEmail(), applicantDTO.getEmail());
        assertEquals(applicant.getPassword(), applicantDTO.getPassword());
        assertEquals(applicant.getBirthDate(), applicantDTO.getBirthDate(Constants.timezone));
        assertEquals(applicant.getDescription(), applicantDTO.getDescription());
        assertEquals(applicant.getRegistrationDate(), applicantDTO.getRegistrationDate(Constants.timezone));
        assertEquals(applicant.getStatus(), applicantDTO.getStatus());
        assertEquals(applicant.getFixedAttributes(), applicantDTO.getFixedAttributes());
    }
}
