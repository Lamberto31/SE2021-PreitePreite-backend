package it.unisalento.mylinkedin.modelmapper;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.PostDTO;
import it.unisalento.mylinkedin.entities.Post;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostModelmapperUnitTest {

    @Test
    public void whenConvertPostEntityToPostDto_thenCorrect() throws ParseException {

        Post post = new Post();
        post.setId(1);
        post.setPubblicationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        post.setHidden(true);
        post.setPrivate(true);
        post.setData("testData");

        PostDTO postDTO = new PostDTO().convertToDto(post);

        assertEquals(post.getId(), postDTO.getId());
        assertEquals(post.getPubblicationDate(), postDTO.getPubblicationDate(Constants.timezone));
        assertEquals(post.isHidden(), postDTO.isHidden());
        assertEquals(post.isPrivate(), postDTO.isPrivate());
        assertEquals(post.getData(), postDTO.getData());
    }

    @Test
    public void whenConvertPostDtoToPostEntity_thenCorrect() throws ParseException {

        PostDTO postDTO = new PostDTO();
        postDTO.setId(1);
        Date date = Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00");
        postDTO.setPubblicationDate(date, Constants.timezone);
        postDTO.setHidden(true);
        postDTO.setPrivate(true);
        postDTO.setData("testPassword");

        Post post = new Post().convertToEntity(postDTO);

        assertEquals(post.getId(), postDTO.getId());
        assertEquals(post.getPubblicationDate(), postDTO.getPubblicationDate(Constants.timezone));
        assertEquals(post.isHidden(), postDTO.isHidden());
        assertEquals(post.isPrivate(), postDTO.isPrivate());
        assertEquals(post.getData(), postDTO.getData());
    }
}
