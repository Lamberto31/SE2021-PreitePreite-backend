package it.unisalento.mylinkedin.restcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.CompanyDTO;
import it.unisalento.mylinkedin.dto.ProfileImageDTO;
import it.unisalento.mylinkedin.dto.UserDTO;
import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.user.*;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserRestController.class)
public class UserRestControllerTest {

    @MockBean
    IUserService userServiceMock;

    @MockBean
    IPostService postServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objMapper;

    private User user;
    private UserDTO userDTO;
    private ProfileImage profileImage;
    private ProfileImageDTO profileImageDTO;
    private Company company;
    private CompanyDTO companyDTO;
    private Offeror offeror;
    private Post post;

    @BeforeEach
    void init() throws UserNotFoundException, UserSavingException, ProfileImageNotFoundException, ProfileImageSavingException, CompanyNotFoundException, CompanySavingException {

        this.user = new User();
        this.user.setId(1);
        this.user.setName("testName");
        this.user.setSurname("testSurname");
        this.user.setEmail("emailtest@test.com");
        this.user.setPassword("testPassword");
        //this.user.setBirthDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.user.setDescription("testDescription");

        when(userServiceMock.getById(user.getId())).thenReturn(user);

        this.userDTO = new UserDTO().convertToDto(user);
        this.userDTO.setType(Constants.TYPE_APPLICANT);
        this.userDTO.setPasswordToVerify(userDTO.getPassword());
        this.userDTO.setEmailToVerify(userDTO.getEmail());

        when(userServiceMock.save(refEq(user))).thenReturn(user);

        this.profileImage = new ProfileImage();
        this.profileImage.setId(1);
        this.profileImage.setDescription("testDescription");
        //this.profileImage.setPubblicationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.profileImage.setImagePath("testImagePath");

        when(userServiceMock.getProfileImageById(profileImage.getId())).thenReturn(profileImage);

        this.profileImageDTO = new ProfileImageDTO().convertToDto(profileImage);

        when(userServiceMock.saveProfileImage(refEq(profileImage))).thenReturn(profileImage);

        this.company = new Company();
        this.company.setId(1);
        this.company.setName("testName");
        this.company.setDescription("testDescription");
        this.company.setPartitaIva("testPartIva");
        this.company.setAddress("testAddress");

        when(userServiceMock.getCompanyById(company.getId())).thenReturn(company);

        this.companyDTO = new CompanyDTO().convertToDto(company);

        when(userServiceMock.saveCompany(refEq(company))).thenReturn(company);

        this.offeror = new Offeror();
        this.offeror.setId(1);
        this.offeror.setName("testName");
        this.offeror.setSurname("testSurname");
        this.offeror.setEmail("emailtest@test.com");
        this.offeror.setPassword("testPassword");
        //this.offeror.setBirthDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.offeror.setDescription("testDescription");
        //this.offeror.setRegistrationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.offeror.setStatus(Constants.REGISTRATION_PENDING);
        this.offeror.setCompany(company);

        when(userServiceMock.getOfferorById(offeror.getId())).thenReturn(offeror);

        this.post = new Post();
        this.post.setId(1);
        //this.post.setPubblicationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.post.setHidden(true);
        this.post.setPrivate(false);
        this.post.setData("testData");
    }

    @Test
    void getByIdTest() throws Exception {
        mockMvc.perform(get(Constants.URI_USER+Constants.URI_GETBYID, user.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveTest() throws Exception {
        mockMvc.perform(post(Constants.URI_USER+Constants.URI_SAVE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void getProfileImageByIdTest() throws Exception {
        mockMvc.perform(get(Constants.URI_USER+Constants.URI_PROFILEIMAGE+Constants.URI_GETBYID, profileImage.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveProfileImageTest() throws Exception {
        mockMvc.perform(post(Constants.URI_USER+Constants.URI_PROFILEIMAGE+Constants.URI_SAVE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objMapper.writeValueAsString(profileImageDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void getCompanyByIdTest() throws Exception {
        mockMvc.perform(get(Constants.URI_USER+Constants.URI_COMPANY+Constants.URI_GETBYID, company.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveCompanyTest() throws Exception {
        mockMvc.perform(post(Constants.URI_USER+Constants.URI_COMPANY+Constants.URI_SAVE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objMapper.writeValueAsString(companyDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void getCompanyByOfferorTest() throws Exception {
        mockMvc.perform(get(Constants.URI_USER+Constants.URI_COMPANY+Constants.URI_GETBYOFFERORID, offeror.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getPostPublicTest() throws Exception {
        mockMvc.perform(get(Constants.URI_USER+Constants.URI_POST+Constants.URI_GETPUBLIC)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getByInterestedPost() throws Exception {
        mockMvc.perform(get(Constants.URI_USER+Constants.URI_GETBYINTERESTED, post.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
