package it.unisalento.mylinkedin.restcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.AttributeDTO;
import it.unisalento.mylinkedin.dto.StructureDTO;
import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.post.AttributeNotFoundException;
import it.unisalento.mylinkedin.exception.post.AttributeSavingException;
import it.unisalento.mylinkedin.exception.post.StructureNotFoundException;
import it.unisalento.mylinkedin.exception.post.StructureSavingException;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AdminRestController.class)
public class AdminRestControllerTest {

    @MockBean
    IUserService userServiceMock;

    @MockBean
    IPostService postServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objMapper;


    private User user;
    private StructureDTO structureDTO;
    private Structure structure;
    private AttributeDTO attributeDTO;
    private Attribute attribute;
    private Applicant applicant;
    private Offeror offeror;
    private Post post;


    @BeforeEach
    void init() throws UserNotFoundException, ParseException, StructureSavingException, StructureNotFoundException, AttributeSavingException, AttributeNotFoundException {

        this.user = new User();
        this.user.setId(1);
        this.user.setName("testName");
        this.user.setSurname("testSurname");
        this.user.setEmail("emailtest@test.com");
        this.user.setPassword("testPassword");
        this.user.setBirthDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.user.setDescription("testDescription");

        when(userServiceMock.getById(user.getId())).thenReturn(user);

        this.structureDTO = new StructureDTO();
        this.structureDTO.setId(1);
        this.structureDTO.setTitle("testTitle");
        this.structureDTO.setDescription("testDescription");
        this.structureDTO.setUserCanPublish(Constants.CAN_PUBLISH_BOTH);

        this.structure = new Structure().convertToEntity(structureDTO);

        when(postServiceMock.saveStructure(refEq(structure))).thenReturn(structure);

        when(postServiceMock.getStructureById(structure.getId())).thenReturn(structure);

        this.attributeDTO = new AttributeDTO();
        this.attributeDTO.setId(1);
        this.attributeDTO.setTitle("testTitle");
        this.attributeDTO.setType("testType");

        this.attribute = new Attribute().convertToEntity(attributeDTO);

        when(postServiceMock.saveAttribute(refEq(attribute))).thenReturn(attribute);

        when(postServiceMock.getAttributeById(attribute.getId())).thenReturn(attribute);

        this.applicant = new Applicant();
        this.applicant.setId(1);
        this.applicant.setName("testName");
        this.applicant.setSurname("testSurname");
        this.applicant.setEmail("emailtest@test.com");
        this.applicant.setPassword("testPassword");
        this.applicant.setBirthDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.applicant.setDescription("testDescription");
        this.applicant.setRegistrationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.applicant.setStatus(Constants.REGISTRATION_PENDING);
        this.applicant.setFixedAttributes("testFixedAttributes");

        this.offeror = new Offeror();
        this.offeror.setId(1);
        this.offeror.setName("testName");
        this.offeror.setSurname("testSurname");
        this.offeror.setEmail("emailtest@test.com");
        this.offeror.setPassword("testPassword");
        this.offeror.setBirthDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.offeror.setDescription("testDescription");
        this.offeror.setRegistrationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.offeror.setStatus(Constants.REGISTRATION_PENDING);

        this.post = new Post();
        this.post.setId(1);
        this.post.setPubblicationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.post.setHidden(true);
        this.post.setPrivate(true);
        this.post.setData("testData");
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void loginTest() throws Exception {
        mockMvc.perform(get(Constants.URI_ADMIN+Constants.URI_LOGIN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteTest() throws Exception {
        mockMvc.perform(delete(Constants.URI_ADMIN+Constants.URI_DELETE, user.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getApplicantByStatusTest() throws Exception {
        mockMvc.perform(get(Constants.URI_ADMIN+Constants.URI_APPLICANT+Constants.URI_GETBYSTATUS, Constants.REGISTRATION_PENDING)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getOfferorByStatusTest() throws Exception {
        mockMvc.perform(get(Constants.URI_ADMIN+Constants.URI_OFFEROR+Constants.URI_GETBYSTATUS, Constants.REGISTRATION_PENDING)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateApplicantStatusRegistrationTest() throws Exception {
        mockMvc.perform(put(Constants.URI_ADMIN +Constants.URI_APPLICANT + Constants.URI_UPDATESTATUSREGISTRATION, applicant.getId(), applicant.getStatus())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateOfferorStatusRegistrationTest() throws Exception {
        mockMvc.perform(put(Constants.URI_ADMIN +Constants.URI_OFFEROR + Constants.URI_UPDATESTATUSREGISTRATION, offeror.getId(), offeror.getStatus())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAllPostTest() throws Exception {
        mockMvc.perform(get(Constants.URI_ADMIN+Constants.URI_POST+Constants.URI_GETALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updatePostIsHiddenTest() throws Exception {
        mockMvc.perform(put(Constants.URI_ADMIN +Constants.URI_POST + Constants.URI_UPDATEISHIDDEN, post.getId(), post.isHidden())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAllStructureTest() throws Exception {
        mockMvc.perform(get(Constants.URI_ADMIN+Constants.URI_STRUCTURE+Constants.URI_GETALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void saveStructureTest() throws Exception {
        mockMvc.perform(post(Constants.URI_ADMIN + Constants.URI_STRUCTURE + Constants.URI_SAVE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objMapper.writeValueAsString(structureDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getStructureByIdTest() throws Exception {
        mockMvc.perform(get(Constants.URI_ADMIN+Constants.URI_STRUCTURE+Constants.URI_GETBYID, structure.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteStructureTest() throws Exception {
        mockMvc.perform(delete(Constants.URI_ADMIN+Constants.URI_STRUCTURE+Constants.URI_DELETE, structure.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAllAttributeTest() throws Exception {
        mockMvc.perform(get(Constants.URI_ADMIN+Constants.URI_ATTRIBUTE+Constants.URI_GETALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void saveAttributeTest() throws Exception {
        mockMvc.perform(post(Constants.URI_ADMIN + Constants.URI_ATTRIBUTE + Constants.URI_SAVE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objMapper.writeValueAsString(attributeDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteAttributeTest() throws Exception {
        mockMvc.perform(delete(Constants.URI_ADMIN+Constants.URI_ATTRIBUTE+Constants.URI_DELETE, attribute.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAllApplicantTest() throws Exception {
        mockMvc.perform(get(Constants.URI_ADMIN+Constants.URI_APPLICANT+Constants.URI_GETALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getAllOfferorTest() throws Exception {
        mockMvc.perform(get(Constants.URI_ADMIN+Constants.URI_OFFEROR+Constants.URI_GETALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
