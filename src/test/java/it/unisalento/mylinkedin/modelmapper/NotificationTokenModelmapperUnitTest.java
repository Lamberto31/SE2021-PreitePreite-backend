package it.unisalento.mylinkedin.modelmapper;

import it.unisalento.mylinkedin.dto.NotificationTokenDTO;
import it.unisalento.mylinkedin.dto.UserDTO;
import it.unisalento.mylinkedin.entities.NotificationToken;
import it.unisalento.mylinkedin.entities.User;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificationTokenModelmapperUnitTest {

    @Test
    public void whenConvertNotificationTokenEntityToNotificationTokenDto_thenCorrect() {

        NotificationToken notificationToken = new NotificationToken();
        notificationToken.setId(1);
        notificationToken.setToken("testToken");

        User user = new User();
        user.setId(1);

        notificationToken.setUser(user);

        NotificationTokenDTO notificationTokenDTO = new NotificationTokenDTO().convertToDto(notificationToken);

        assertEquals(notificationToken.getId(), notificationTokenDTO.getId());
        assertEquals(notificationToken.getToken(), notificationTokenDTO.getToken());
        assertEquals(notificationToken.getUser().getId(), notificationTokenDTO.getUser().getId());
    }

    @Test
    public void whenConvertNotificationTokenDtoToNotificationTokenEntity_thenCorrect() {

        NotificationTokenDTO notificationTokenDTO = new NotificationTokenDTO();
        notificationTokenDTO.setId(1);
        notificationTokenDTO.setToken("testToken");

        UserDTO user = new UserDTO();
        user.setId(1);

        notificationTokenDTO.setUser(user);

        NotificationToken notificationToken = new NotificationToken().convertToEntity(notificationTokenDTO);

        assertEquals(notificationToken.getId(), notificationTokenDTO.getId());
        assertEquals(notificationToken.getToken(), notificationTokenDTO.getToken());
        assertEquals(notificationToken.getUser().getId(), notificationTokenDTO.getUser().getId());
    }
}
