package it.unisalento.mylinkedin.modelmapper;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.MessageDTO;
import it.unisalento.mylinkedin.dto.UserDTO;
import it.unisalento.mylinkedin.entities.Message;
import it.unisalento.mylinkedin.entities.User;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageModelmapperUnitTest {

    @Test
    public void whenConvertMessageEntityToMessageDto_thenCorrect() throws ParseException {

        Message message = new Message();
        message.setId(1);
        message.setText("testName");
        message.setImagePath("testImagePath");
        message.setPubblicationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));

        User sender = new User();
        sender.setId(1);
        User receiver = new User();
        receiver.setId(2);

        message.setSender(sender);
        message.setReceiver(receiver);

        MessageDTO messageDTO = new MessageDTO().convertToDto(message);

        assertEquals(message.getId(), messageDTO.getId());
        assertEquals(message.getText(), messageDTO.getText());
        assertEquals(message.getImagePath(), messageDTO.getImagePath());
        assertEquals(message.getPubblicationDate(), messageDTO.getPubblicationDate(Constants.timezone));
        assertEquals(message.getSender().getId(), messageDTO.getSender().getId());
        assertEquals(message.getReceiver().getId(), messageDTO.getReceiver().getId());
    }

    @Test
    public void whenConvertMessageDtoToMessageEntity_thenCorrect() throws ParseException {

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(1);
        messageDTO.setText("testText");
        messageDTO.setImagePath("testImagePath");
        Date date = Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00");
        messageDTO.setPubblicationDate(date, Constants.timezone);

        UserDTO sender = new UserDTO();
        sender.setId(1);
        UserDTO receiver = new UserDTO();
        receiver.setId(2);

        messageDTO.setSender(sender);
        messageDTO.setReceiver(receiver);

        Message message = new Message().convertToEntity(messageDTO);

        assertEquals(message.getId(), messageDTO.getId());
        assertEquals(message.getText(), messageDTO.getText());
        assertEquals(message.getImagePath(), messageDTO.getImagePath());
        assertEquals(message.getPubblicationDate(), messageDTO.getPubblicationDate(Constants.timezone));
        assertEquals(message.getSender().getId(), messageDTO.getSender().getId());
        assertEquals(message.getReceiver().getId(), messageDTO.getReceiver().getId());
    }
}
