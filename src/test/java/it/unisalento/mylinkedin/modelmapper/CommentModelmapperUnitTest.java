package it.unisalento.mylinkedin.modelmapper;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.CommentDTO;
import it.unisalento.mylinkedin.entities.Comment;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentModelmapperUnitTest {

    @Test
    public void whenConvertCommentEntityToCommentDto_thenCorrect() throws ParseException {

        Comment comment = new Comment();
        comment.setId(1);
        comment.setText("testText");
        comment.setPubblicationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));

        CommentDTO commentDTO = new CommentDTO().convertToDto(comment);

        assertEquals(comment.getId(), commentDTO.getId());
        assertEquals(comment.getText(), commentDTO.getText());
        assertEquals(comment.getPubblicationDate(), commentDTO.getPubblicationDate(Constants.timezone));
    }

    @Test
    public void whenConvertCommentDtoToCommentEntity_thenCorrect() throws ParseException {

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(1);
        commentDTO.setText("testText");
        Date date = Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00");
        commentDTO.setPubblicationDate(date, Constants.timezone);

        Comment comment = new Comment().convertToEntity(commentDTO);

        assertEquals(comment.getId(), commentDTO.getId());
        assertEquals(comment.getText(), commentDTO.getText());
        assertEquals(comment.getPubblicationDate(), commentDTO.getPubblicationDate(Constants.timezone));
    }
}
