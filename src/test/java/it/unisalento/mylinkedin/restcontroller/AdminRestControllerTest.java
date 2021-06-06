package it.unisalento.mylinkedin.restcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.AttributeDTO;
import it.unisalento.mylinkedin.dto.StructureDTO;
import it.unisalento.mylinkedin.entities.Attribute;
import it.unisalento.mylinkedin.entities.Structure;
import it.unisalento.mylinkedin.entities.User;
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

    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(delete(Constants.URI_ADMIN+Constants.URI_DELETE, user.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getApplicantByStatusTest() throws Exception {
        mockMvc.perform(get(Constants.URI_ADMIN+Constants.URI_GETAPPLICANTBYSTATUS, Constants.REGISTRATION_PENDING)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getOfferorByStatusTest() throws Exception {
        mockMvc.perform(get(Constants.URI_ADMIN+Constants.URI_GETOFFERORBYSTATUS, Constants.REGISTRATION_PENDING)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //TODO: Test updateStatusRegistration perchè dobbiamo fare put
    //TODO: Test updateIsHidden perchè dobbiamo fare put

    @Test
    void getAllStructureTest() throws Exception {
        mockMvc.perform(get(Constants.URI_ADMIN+Constants.URI_STRUCTURE+Constants.URI_GETALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveStructureTest() throws Exception {
        mockMvc.perform(post(Constants.URI_ADMIN + Constants.URI_STRUCTURE + Constants.URI_SAVE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objMapper.writeValueAsString(structureDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void getStructureByIdTest() throws Exception {
        mockMvc.perform(get(Constants.URI_ADMIN+Constants.URI_STRUCTURE+Constants.URI_GETBYID, structure.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteStructureTest() throws Exception {
        mockMvc.perform(delete(Constants.URI_ADMIN+Constants.URI_STRUCTURE+Constants.URI_DELETE, structure.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllAttributeTest() throws Exception {
        mockMvc.perform(get(Constants.URI_ADMIN+Constants.URI_ATTRIBUTE+Constants.URI_GETALL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveAttributeTest() throws Exception {
        mockMvc.perform(post(Constants.URI_ADMIN + Constants.URI_ATTRIBUTE + Constants.URI_SAVE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objMapper.writeValueAsString(attributeDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAttributeTest() throws Exception {
        mockMvc.perform(delete(Constants.URI_ADMIN+Constants.URI_ATTRIBUTE+Constants.URI_DELETE, attribute.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
