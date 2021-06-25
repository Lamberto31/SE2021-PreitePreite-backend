package it.unisalento.mylinkedin.restcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.CommentDTO;
import it.unisalento.mylinkedin.dto.MessageDTO;
import it.unisalento.mylinkedin.dto.PostDTO;
import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.post.CommentSavingException;
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
    private CommentDTO commentDTO;
    private Comment comment;
    private Structure structure;

    @BeforeEach
    void init() throws ParseException, MessageSavingException, UserNotFoundException, PostNotFoundException, PostSavingException, CommentSavingException {

        this.messageDTO = new MessageDTO();
        this.messageDTO.setId(1);
        this.messageDTO.setText("testText");
        this.messageDTO.setImagePath("testImagePath");
        //Date date = Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00");
        //this.messageDTO.setPubblicationDate(date, Constants.timezone);

        this.message = new Message().convertToEntity(messageDTO);

        when(userServiceMock.saveMessage(refEq(message))).thenReturn(message);

        this.user = new User();
        this.user.setId(1);
        this.user.setName("testName");
        this.user.setSurname("testSurname");
        this.user.setEmail("emailtest@test.com");
        this.user.setPassword("testPassword");
        //this.user.setBirthDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.user.setDescription("testDescription");

        when(userServiceMock.getById(user.getId())).thenReturn(user);

        this.post = new Post();
        this.post.setId(1);
        //this.post.setPubblicationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.post.setHidden(true);
        this.post.setPrivate(true);
        this.post.setData("testData");

        when(postServiceMock.getById(post.getId())).thenReturn(post);

        this.postDTO = new PostDTO().convertToDto(post);

        when(postServiceMock.save(refEq(post))).thenReturn(post);

        this.commentDTO = new CommentDTO();
        this.commentDTO.setId(1);
        this.commentDTO.setText("testText");
        //this.commentDTO.setPubblicationDate(date, Constants.timezone);

        this.comment = new Comment().convertToEntity(this.commentDTO);

        when(postServiceMock.saveComment(refEq(comment))).thenReturn(comment);

        this.structure = new Structure();
        this.structure.setId(1);
        this.structure.setTitle("testTitle");
        this.structure.setDescription("testDescription");
        this.structure.setUserCanPublish(Constants.CAN_PUBLISH_BOTH);
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
    void getMessageBySenderTest() throws Exception {
        mockMvc.perform(get(Constants.URI_REGISTEREDUSER+Constants.URI_MESSAGE+Constants.URI_GETBYSENDER, user.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getMessageByReceiverTest() throws Exception {
        mockMvc.perform(get(Constants.URI_REGISTEREDUSER+Constants.URI_MESSAGE+Constants.URI_GETBYRECEIVER, user.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getMessageSentOrReceivedByUserTest() throws Exception {
        mockMvc.perform(get(Constants.URI_REGISTEREDUSER+Constants.URI_MESSAGE+Constants.URI_GETBYUSERSENTORRECEIVED, user.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getMessageByReceiverAndNotReadTest() throws Exception {
        mockMvc.perform(get(Constants.URI_REGISTEREDUSER+Constants.URI_MESSAGE+Constants.URI_GETBYRECEIVERANDNOTREAD, user.getId())
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
    void getStructureBothCanPublish() throws Exception {
        mockMvc.perform(get(Constants.URI_REGISTEREDUSER+Constants.URI_STRUCTURE+Constants.URI_GETCANPUBLISH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getStructureApplicantCanPublish() throws Exception {
        mockMvc.perform(get(Constants.URI_REGISTEREDUSER+Constants.URI_APPLICANT+Constants.URI_STRUCTURE+Constants.URI_GETCANPUBLISH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getStructureOfferorCanPublish() throws Exception {
        mockMvc.perform(get(Constants.URI_REGISTEREDUSER+Constants.URI_OFFEROR+Constants.URI_STRUCTURE+Constants.URI_GETCANPUBLISH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveCommentTest() throws Exception {
        mockMvc.perform(post(Constants.URI_REGISTEREDUSER + Constants.URI_COMMENT + Constants.URI_SAVE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objMapper.writeValueAsString(commentDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void getCommentByPostTest() throws Exception {
        mockMvc.perform(get(Constants.URI_REGISTEREDUSER+Constants.URI_COMMENT+Constants.URI_GETBYPOST, post.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAttributeByStructureTest() throws Exception {
        mockMvc.perform(get(Constants.URI_REGISTEREDUSER+Constants.URI_ATTRIBUTE+Constants.URI_GETBYSTRUCTURE, structure.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getUserById() throws Exception {
        mockMvc.perform(get(Constants.URI_REGISTEREDUSER+Constants.URI_GETBYID, user.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
