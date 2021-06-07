package it.unisalento.mylinkedin.restcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.MessageDTO;
import it.unisalento.mylinkedin.dto.PostDTO;
import it.unisalento.mylinkedin.entities.Attribute;
import it.unisalento.mylinkedin.entities.Message;
import it.unisalento.mylinkedin.entities.Post;
import it.unisalento.mylinkedin.entities.User;
import it.unisalento.mylinkedin.exception.post.PostNotFoundException;
import it.unisalento.mylinkedin.exception.post.PostSavingException;
import it.unisalento.mylinkedin.exception.user.MessageSavingException;
import it.unisalento.mylinkedin.exception.user.UserNotFoundException;
import it.unisalento.mylinkedin.service.iservice.IPostService;
import it.unisalento.mylinkedin.service.iservice.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.util.Date;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RegisteredUserRestController.class)
public class RegisteredUserRestControllerTest {

    @MockBean
    IUserService userServiceMock;

    @MockBean
    IPostService postServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objMapper;


    private MessageDTO messageDTO;
    private Message message;
    private User user;
    private Post post;
    private PostDTO postDTO;

    @BeforeEach
    void init() throws ParseException, MessageSavingException, UserNotFoundException, PostNotFoundException, PostSavingException {

        this.messageDTO = new MessageDTO();
        this.messageDTO.setId(1);
        this.messageDTO.setText("testText");
        this.messageDTO.setImagePath("testImagePath");
        Date date = Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00");
        this.messageDTO.setPubblicationDate(date, Constants.timezone);

        this.message = new Message().convertToEntity(messageDTO);

        when(userServiceMock.saveMessage(refEq(message))).thenReturn(message);

        this.user = new User();
        this.user.setId(1);
        this.user.setName("testName");
        this.user.setSurname("testSurname");
        this.user.setEmail("emailtest@test.com");
        this.user.setPassword("testPassword");
        this.user.setBirthDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.user.setDescription("testDescription");

        when(userServiceMock.getById(user.getId())).thenReturn(user);

        this.post = new Post();
        this.post.setId(1);
        this.post.setPubblicationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.post.setHidden(true);
        this.post.setPrivate(true);
        this.post.setData("testData");

        when(postServiceMock.getById(post.getId())).thenReturn(post);

        this.postDTO = new PostDTO().convertToDto(post);

        when(postServiceMock.save(refEq(post))).thenReturn(post);

    }

    //TODO: Risolvere problema objectmapper con data
    @Test
    void saveMessageTest() throws Exception {
        mockMvc.perform(post(Constants.URI_REGISTEREDUSER + Constants.URI_MESSAGE + Constants.URI_SAVE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objMapper.writeValueAsString(messageDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void getMessageBySenderAndReceiverTest() throws Exception {
        mockMvc.perform(get(Constants.URI_REGISTEREDUSER+Constants.URI_MESSAGE+Constants.URI_GETBYSENDERANDRECEIVER, user.getId(), user.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllPostTest() throws Exception {
        mockMvc.perform(get(Constants.URI_REGISTEREDUSER+Constants.URI_POST+Constants.URI_GETALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getPostByIdTest() throws Exception {
        mockMvc.perform(get(Constants.URI_REGISTEREDUSER+Constants.URI_POST+Constants.URI_GETBYID, post.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //TODO: Risolvere problema objectmapper con data
    @Test
    void savePostTest() throws Exception {
        mockMvc.perform(post(Constants.URI_REGISTEREDUSER + Constants.URI_POST + Constants.URI_SAVE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objMapper.writeValueAsString(postDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void getStructureCanPublishTest() throws Exception {
        mockMvc.perform(get(Constants.URI_REGISTEREDUSER+Constants.URI_STRUCTURE+Constants.URI_GETBOTHCANPUBLISH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
