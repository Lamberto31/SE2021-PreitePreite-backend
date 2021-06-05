package it.unisalento.mylinkedin.restcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.entities.Applicant;
import it.unisalento.mylinkedin.entities.Offeror;
import it.unisalento.mylinkedin.entities.User;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private Applicant applicant;
    private Offeror offeror;


    @BeforeEach
    void init() throws UserNotFoundException, ParseException {

        this.user = new User();
        this.user.setId(1);
        this.user.setName("testName");
        this.user.setSurname("testSurname");
        this.user.setEmail("emailtest@test.com");
        this.user.setPassword("testPassword");
        this.user.setBirthDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.user.setDescription("testDescription");

        when(userServiceMock.getById(1)).thenReturn(user);
    }

    @Test
    void deleteTest() {
        try {
            mockMvc.perform(delete(Constants.URI_ADMIN+Constants.URI_DELETE, "1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getApplicantByStatusTest() {
        try {
            mockMvc.perform(get(Constants.URI_ADMIN+Constants.URI_GETAPPLICANTBYSTATUS, Constants.REGISTRATION_PENDING)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getOfferorByStatusTest() {
        try {
            mockMvc.perform(get(Constants.URI_ADMIN+Constants.URI_GETOFFERORBYSTATUS, Constants.REGISTRATION_PENDING)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO: Test updateStatusRegistration perchè dobbiamo fare put
    //TODO: Test updateIsHidden perchè dobbiamo fare put

    @Test
    void getAllStructureTest() {
        try {
            mockMvc.perform(get(Constants.URI_ADMIN+Constants.URI_STRUCTURE+Constants.URI_GETALL)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
