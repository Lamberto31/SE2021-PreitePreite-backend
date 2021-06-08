package it.unisalento.mylinkedin.iservice;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dao.*;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.refEq;
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

    @BeforeEach
    void init() throws ParseException {

        this.user = new User();
        this.user.setName("testName");
        this.user.setSurname("testSurname");
        this.user.setEmail("emailtest@test.com");
        this.user.setPassword("testPassword");
        this.user.setBirthDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.user.setDescription("testDescription");

        this.wrongUser = new User();
        when(userRepository.save(refEq(user))).thenReturn(user);
        when(userRepository.save(refEq(wrongUser))).thenThrow(IllegalArgumentException.class);

        this.userId = 1;
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.ofNullable(user));
        when(userRepository.findById(wrongUser.getId())).thenReturn(null);
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
}
