package it.unisalento.mylinkedin.modelmapper;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.MessageDTO;
import it.unisalento.mylinkedin.entities.Message;
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

        MessageDTO messageDTO = new MessageDTO().convertToDto(message);

        assertEquals(message.getId(), messageDTO.getId());
        assertEquals(message.getText(), messageDTO.getText());
        assertEquals(message.getImagePath(), messageDTO.getImagePath());
        assertEquals(message.getPubblicationDate(), messageDTO.getPubblicationDate(Constants.timezone));
    }

    @Test
    public void whenConvertMessageDtoToMessageEntity_thenCorrect() throws ParseException {

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(1);
        messageDTO.setText("testText");
        messageDTO.setImagePath("testImagePath");
        Date date = Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00");
        messageDTO.setPubblicationDate(date, Constants.timezone);

        Message message = new Message().convertToEntity(messageDTO);

        assertEquals(message.getId(), messageDTO.getId());
        assertEquals(message.getText(), messageDTO.getText());
        assertEquals(message.getImagePath(), messageDTO.getImagePath());
        assertEquals(message.getPubblicationDate(), messageDTO.getPubblicationDate(Constants.timezone));
    }
}
