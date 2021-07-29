package it.unisalento.mylinkedin.modelmapper;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.CommentDTO;
import it.unisalento.mylinkedin.dto.PostDTO;
import it.unisalento.mylinkedin.dto.UserDTO;
import it.unisalento.mylinkedin.entities.Comment;
import it.unisalento.mylinkedin.entities.Post;
import it.unisalento.mylinkedin.entities.User;
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

        Comment answeredComment = new Comment();
        answeredComment.setId(2);
        Post post = new Post();
        post.setId(1);
        User user = new User();
        user.setId(1);

        comment.setAnsweredComment(answeredComment);
        comment.setPost(post);
        comment.setUser(user);

        CommentDTO commentDTO = new CommentDTO().convertToDto(comment);

        assertEquals(comment.getId(), commentDTO.getId());
        assertEquals(comment.getText(), commentDTO.getText());
        assertEquals(comment.getPubblicationDate(), commentDTO.getPubblicationDate(Constants.timezone));
        assertEquals(comment.getAnsweredComment().getId(), commentDTO.getAnsweredComment().getId());
        assertEquals(comment.getPost().getId(), commentDTO.getPost().getId());
        assertEquals(comment.getUser().getId(), commentDTO.getUser().getId());
    }

    @Test
    public void whenConvertCommentDtoToCommentEntity_thenCorrect() throws ParseException {

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(1);
        commentDTO.setText("testText");
        Date date = Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00");
        commentDTO.setPubblicationDate(date, Constants.timezone);

        CommentDTO answeredComment = new CommentDTO();
        answeredComment.setId(2);
        PostDTO post = new PostDTO();
        post.setId(1);
        UserDTO user = new UserDTO();
        user.setId(1);

        commentDTO.setAnsweredComment(answeredComment);
        commentDTO.setPost(post);
        commentDTO.setUser(user);

        Comment comment = new Comment().convertToEntity(commentDTO);

        assertEquals(comment.getId(), commentDTO.getId());
        assertEquals(comment.getText(), commentDTO.getText());
        assertEquals(comment.getPubblicationDate(), commentDTO.getPubblicationDate(Constants.timezone));
        assertEquals(comment.getAnsweredComment().getId(), commentDTO.getAnsweredComment().getId());
        assertEquals(comment.getPost().getId(), commentDTO.getPost().getId());
        assertEquals(comment.getUser().getId(), commentDTO.getUser().getId());
    }
}
