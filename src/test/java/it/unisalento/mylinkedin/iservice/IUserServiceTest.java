package it.unisalento.mylinkedin.iservice;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dao.*;
import it.unisalento.mylinkedin.entities.Applicant;
import it.unisalento.mylinkedin.entities.User;
import it.unisalento.mylinkedin.exception.user.UserNotFoundException;
import it.unisalento.mylinkedin.exception.user.UserSavingException;
import it.unisalento.mylinkedin.service.iservice.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IUserServiceTest {

    @Autowired
    private IUserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AdministratorRepository administratorRepository;

    @MockBean
    private ApplicantRepository applicantRepository;

    @MockBean
    private OfferorRepository offerorRepository;

    @MockBean
    private ProfileImageRepository profileImageRepository;

    @MockBean
    private MessageRepository messageRepository;

    @MockBean
    private CompanyRepository companyRepository;

    private User user;
    private User wrongUser;
    private int userId;
    private Applicant applicant;
    private List<Applicant> applicantList;
    private String applicantNotFoundStatus;

    @BeforeEach
    void init() throws ParseException, UserNotFoundException {

        this.user = new User();
        this.user.setName("testName");
        this.user.setSurname("testSurname");
        this.user.setEmail("emailtest@test.com");
        this.user.setPassword("testPassword");
        this.user.setBirthDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.user.setDescription("testDescription");

        when(userRepository.save(refEq(user))).thenReturn(user);

        this.wrongUser = new User();

        when(userRepository.save(refEq(wrongUser))).thenThrow(IllegalArgumentException.class);

        this.userId = 1;
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.ofNullable(user));

        //when(userRepository.delete(refEq(wrongUser))).thenThrow(IllegalArgumentException.class);
        doThrow(new IllegalArgumentException()).when(userRepository).delete(wrongUser);

        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        when(userRepository.findByEmail(wrongUser.getEmail())).thenThrow(IllegalArgumentException.class);

        this.applicant = new Applicant();
        this.applicant.setName("testName");
        this.applicant.setSurname("testSurname");
        this.applicant.setEmail("emailtest@test.com");
        this.applicant.setPassword("testPassword");
        this.applicant.setBirthDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.applicant.setDescription("testDescription");
        this.applicant.setStatus(Constants.REGISTRATION_PENDING);

        when(applicantRepository.findById(userId)).thenReturn(java.util.Optional.ofNullable(applicant));

        this.applicantList = new ArrayList<>();
        applicantList.add(applicant);

        when(applicantRepository.findByStatus(applicant.getStatus())).thenReturn(applicantList);

        this.applicantNotFoundStatus = Constants.REGISTRATION_BLOCKED;

        when(applicantRepository.findByStatus(applicantNotFoundStatus)).thenThrow(UserNotFoundException.class);
    }

    @Test
    void getAllTest() {
        assertThat(userService.getAll()).isNotNull();
    }

    @Test
    void saveTest() throws UserSavingException {
        User userSaved = userService.save(user);
        assertThat(user.equals(userSaved));
    }

    @Test
    void saveThrowsExTest() {
        Exception exp = assertThrows(UserSavingException.class, () -> userService.save(wrongUser));
        assertThat(exp).isNotNull();
    }

    @Test
    void getByIdTest() throws UserNotFoundException {
        User userFound = userService.getById(userId);
        assertThat(user.equals(userFound));
    }

    @Test
    void getByIdThrowsExTest() {
        Exception exp = assertThrows(UserNotFoundException.class, () -> userService.getById(wrongUser.getId()));
        assertThat(exp).isNotNull();
    }

    @Test
    void deleteTest() throws UserNotFoundException {
        User userDeleted = userService.delete(user);
        assertThat(user.equals(userDeleted));
    }

    @Test
    void deleteThrowsExTest() {
        Exception exp = assertThrows(UserNotFoundException.class, () -> userService.delete(wrongUser));
        assertThat(exp).isNotNull();
    }

    @Test
    void getByEmailTest() throws UserNotFoundException {
        User userFound = userService.getByEmail(user.getEmail());
        assertThat(user.equals(userFound));
    }

    @Test
    void getByEmailThrowsExTest() {
        Exception exp = assertThrows(UserNotFoundException.class, () -> userService.getByEmail(wrongUser.getEmail()));
        assertThat(exp).isNotNull();
    }

    @Test
    void getApplicantByIdTest() throws UserNotFoundException {
        Applicant applicantFound = userService.getApplicantById(userId);
        assertThat(applicant.equals(applicantFound));
    }

    @Test
    void getApplicantByIdThrowsExTest() {
        Exception exp = assertThrows(UserNotFoundException.class, () -> userService.getApplicantById(wrongUser.getId()));
        assertThat(exp).isNotNull();
    }

    @Test
    void getApplicantByStatusTest() throws UserNotFoundException {
        List<Applicant> applicantFoundList = userService.getApplicantByStatus(applicant.getStatus());
        assertThat(applicantList.equals(applicantFoundList));
    }

    @Test
    void getApplicantByStatusThrowsExTest() {
        Exception exp = assertThrows(UserNotFoundException.class, () -> userService.getApplicantByStatus(applicantNotFoundStatus));
        assertThat(exp).isNotNull();
    }


}
