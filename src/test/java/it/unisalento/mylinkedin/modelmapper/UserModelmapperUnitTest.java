package it.unisalento.mylinkedin.modelmapper;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.UserDTO;
import it.unisalento.mylinkedin.entities.User;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserModelmapperUnitTest {

    @Test
    public void whenConvertUserEntityToUserDto_thenCorrect() throws ParseException {

        User user = new User();
        user.setId(1);
        user.setName("testName");
        user.setSurname("testSurname");
        user.setEmail("emailtest@test.com");
        user.setPassword("testPassword");

        user.setBirthDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));

        user.setDescription("testDescription");

        UserDTO userDTO = new UserDTO().convertToDto(user);

        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getName(), userDTO.getName());
        assertEquals(user.getSurname(), userDTO.getSurname());
        assertEquals(user.getEmail(), userDTO.getEmail());
        assertEquals(user.getPassword(), userDTO.getPassword());
        assertEquals(user.getBirthDate(), userDTO.getBirthDate(Constants.timezone));
        assertEquals(user.getDescription(), userDTO.getDescription());
    }

    @Test
    public void whenConvertUserDtoToUserEntity_thenCorrect() throws ParseException {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setName("testName");
        userDTO.setSurname("testSurname");
        userDTO.setEmail("emailtest@test.com");
        userDTO.setPassword("testPassword");

        Date date = Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00");
        userDTO.setBirthDate(date, Constants.timezone);

        User user = new User().convertToEntity(userDTO);

        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getName(), userDTO.getName());
        assertEquals(user.getSurname(), userDTO.getSurname());
        assertEquals(user.getEmail(), userDTO.getEmail());
        assertEquals(user.getPassword(), userDTO.getPassword());
        assertEquals(user.getBirthDate(), userDTO.getBirthDate(Constants.timezone));
        assertEquals(user.getDescription(), userDTO.getDescription());
    }
}
