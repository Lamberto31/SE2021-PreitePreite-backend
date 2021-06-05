package it.unisalento.mylinkedin.modelmapper;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.ProfileImageDTO;
import it.unisalento.mylinkedin.entities.ProfileImage;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfileImageModelmapperUnitTest {

    @Test
    public void whenConvertProfileImageEntityToProfileImageDto_thenCorrect() throws ParseException {

        ProfileImage profileImage = new ProfileImage();
        profileImage.setId(1);
        profileImage.setDescription("testDescription");
        profileImage.setPubblicationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        profileImage.setImagePath("testImagePath");

        ProfileImageDTO profileImageDTO = new ProfileImageDTO().convertToDto(profileImage);

        assertEquals(profileImage.getId(), profileImageDTO.getId());
        assertEquals(profileImage.getDescription(), profileImageDTO.getDescription());
        assertEquals(profileImage.getPubblicationDate(), profileImageDTO.getPubblicationDate(Constants.timezone));
        assertEquals(profileImage.getImagePath(), profileImageDTO.getImagePath());
    }

    @Test
    public void whenConvertProfileImageDtoToProfileImageEntity_thenCorrect() throws ParseException {

        ProfileImageDTO profileImageDTO = new ProfileImageDTO();
        profileImageDTO.setId(1);
        profileImageDTO.setDescription("testName");
        Date date = Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00");
        profileImageDTO.setPubblicationDate(date, Constants.timezone);
        profileImageDTO.setImagePath("testSurname");

        ProfileImage profileImage = new ProfileImage().convertToEntity(profileImageDTO);

        assertEquals(profileImage.getId(), profileImageDTO.getId());
        assertEquals(profileImage.getDescription(), profileImageDTO.getDescription());
        assertEquals(profileImage.getPubblicationDate(), profileImageDTO.getPubblicationDate(Constants.timezone));
        assertEquals(profileImage.getImagePath(), profileImageDTO.getImagePath());
    }
}
