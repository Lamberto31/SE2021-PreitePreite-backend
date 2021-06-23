package it.unisalento.mylinkedin.modelmapper;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.PostDTO;
import it.unisalento.mylinkedin.dto.StructureDTO;
import it.unisalento.mylinkedin.dto.UserDTO;
import it.unisalento.mylinkedin.entities.Post;
import it.unisalento.mylinkedin.entities.Structure;
import it.unisalento.mylinkedin.entities.User;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostModelmapperUnitTest {

    @Test
    public void whenConvertPostEntityToPostDto_thenCorrect() throws ParseException {

        Post post = new Post();
        post.setId(1);
        post.setTitle("testTitle");
        post.setPubblicationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        post.setHidden(true);
        post.setPrivate(true);
        post.setData("testData");

        Structure structure = new Structure();
        structure.setId(1);
        User user = new User();
        user.setId(1);

        post.setStructure(structure);
        post.setUser(user);

        PostDTO postDTO = new PostDTO().convertToDto(post);

        assertEquals(post.getId(), postDTO.getId());
        assertEquals(post.getTitle(), postDTO.getTitle());
        assertEquals(post.getPubblicationDate(), postDTO.getPubblicationDate(Constants.timezone));
        assertEquals(post.isHidden(), postDTO.isHidden());
        assertEquals(post.isPrivate(), postDTO.isPrivate());
        assertEquals(post.getData(), postDTO.getData());
        assertEquals(post.getStructure().getId(), postDTO.getStructure().getId());
        assertEquals(post.getUser().getId(), postDTO.getUser().getId());
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

        StructureDTO structure = new StructureDTO();
        structure.setId(1);
        UserDTO user = new UserDTO();
        user.setId(1);

        postDTO.setStructure(structure);
        postDTO.setUser(user);

        Post post = new Post().convertToEntity(postDTO);

        assertEquals(post.getId(), postDTO.getId());
        assertEquals(post.getTitle(), postDTO.getTitle());
        assertEquals(post.getPubblicationDate(), postDTO.getPubblicationDate(Constants.timezone));
        assertEquals(post.isHidden(), postDTO.isHidden());
        assertEquals(post.isPrivate(), postDTO.isPrivate());
        assertEquals(post.getData(), postDTO.getData());
        assertEquals(post.getStructure().getId(), postDTO.getStructure().getId());
        assertEquals(post.getUser().getId(), postDTO.getUser().getId());
    }
}
