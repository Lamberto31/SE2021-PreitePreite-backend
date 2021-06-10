package it.unisalento.mylinkedin.iservice;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dao.*;
import it.unisalento.mylinkedin.entities.Applicant;
import it.unisalento.mylinkedin.entities.Offeror;
import it.unisalento.mylinkedin.entities.ProfileImage;
import it.unisalento.mylinkedin.entities.User;
import it.unisalento.mylinkedin.exception.InvalidValueException;
import it.unisalento.mylinkedin.exception.user.ProfileImageNotFoundException;
import it.unisalento.mylinkedin.exception.user.ProfileImageSavingException;
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
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
    private int correctId;
    private Applicant applicant;
    private List<Applicant> applicantList;
    private String registeredUserNotFoundStatus;
    private Offeror offeror;
    private List<Offeror> offerorList;
    private String wrongUserRegisteredStatus;
    private ProfileImage profileImage;
    private ProfileImage wrongProfileImage;

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

        this.correctId = 1;
        when(userRepository.findById(correctId)).thenReturn(java.util.Optional.ofNullable(user));

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

        when(applicantRepository.findById(correctId)).thenReturn(java.util.Optional.ofNullable(applicant));

        this.applicantList = new ArrayList<>();
        applicantList.add(applicant);

        when(applicantRepository.findByStatus(applicant.getStatus())).thenReturn(applicantList);

        this.registeredUserNotFoundStatus = Constants.REGISTRATION_BLOCKED;

        when(applicantRepository.findByStatus(registeredUserNotFoundStatus)).thenThrow(UserNotFoundException.class);

        this.offeror = new Offeror();
        this.offeror.setName("testName");
        this.offeror.setSurname("testSurname");
        this.offeror.setEmail("emailtest@test.com");
        this.offeror.setPassword("testPassword");
        this.offeror.setBirthDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.offeror.setDescription("testDescription");
        this.offeror.setStatus(Constants.REGISTRATION_PENDING);

        when(offerorRepository.findById(correctId)).thenReturn(java.util.Optional.ofNullable(offeror));

        this.offerorList = new ArrayList<>();
        offerorList.add(offeror);

        when(offerorRepository.findByStatus(offeror.getStatus())).thenReturn(offerorList);

        when(offerorRepository.findByStatus(registeredUserNotFoundStatus)).thenThrow(UserNotFoundException.class);

        wrongUserRegisteredStatus = "wrong";

        this.profileImage = new ProfileImage();
        this.profileImage.setDescription("testDescription");
        this.profileImage.setPubblicationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.profileImage.setImagePath("testImagePath");

        when(profileImageRepository.save(refEq(profileImage))).thenReturn(profileImage);

        this.wrongProfileImage = new ProfileImage();

        when(profileImageRepository.save(refEq(wrongProfileImage))).thenThrow(IllegalArgumentException.class);

        when(profileImageRepository.findById(correctId)).thenReturn(java.util.Optional.ofNullable(profileImage));

        doThrow(new IllegalArgumentException()).when(profileImageRepository).delete(wrongProfileImage);


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
        User userFound = userService.getById(correctId);
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
        Applicant applicantFound = userService.getApplicantById(correctId);
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
        Exception exp = assertThrows(UserNotFoundException.class, () -> userService.getApplicantByStatus(registeredUserNotFoundStatus));
        assertThat(exp).isNotNull();
    }

    @Test
    void getOfferorByIdTest() throws UserNotFoundException {
        Offeror offerorFound = userService.getOfferorById(correctId);
        assertThat(offeror.equals(offerorFound));
    }

    @Test
    void getOfferorByIdThrowsExTest() {
        Exception exp = assertThrows(UserNotFoundException.class, () -> userService.getOfferorById(wrongUser.getId()));
        assertThat(exp).isNotNull();
    }

    @Test
    void getOfferorByStatusTest() throws UserNotFoundException {
        List<Offeror> offerorFoundList = userService.getOfferorByStatus(offeror.getStatus());
        assertThat(offerorList.equals(offerorFoundList));
    }

    @Test
    void getOfferorByStatusThrowsExTest() {
        Exception exp = assertThrows(UserNotFoundException.class, () -> userService.getOfferorByStatus(registeredUserNotFoundStatus));
        assertThat(exp).isNotNull();
    }

    @Test
    void updateApplicantStatusRegistrationTest() {
        assertDoesNotThrow(() -> userService.updateApplicantStatusRegistration(applicant.getStatus(), correctId));
    }

    @Test
    void updateApplicantStatusRegistrationThrowsUserNotFoundExTest() {
        Exception exp = assertThrows(UserNotFoundException.class, () -> userService.updateApplicantStatusRegistration(applicant.getStatus(), wrongUser.getId()));
        assertThat(exp).isNotNull();
    }

    @Test
    void updateApplicantStatusRegistrationThrowsInvalidValueExTest() {
        Exception exp = assertThrows(InvalidValueException.class, () -> userService.updateApplicantStatusRegistration(wrongUserRegisteredStatus, correctId));
        assertThat(exp).isNotNull();
    }

    @Test
    void updateOfferorStatusRegistrationTest() {
        assertDoesNotThrow(() -> userService.updateOfferorStatusRegistration(offeror.getStatus(), correctId));
    }

    @Test
    void updateOfferorStatusRegistrationThrowsUserNotFoundExTest() {
        Exception exp = assertThrows(UserNotFoundException.class, () -> userService.updateOfferorStatusRegistration(offeror.getStatus(), wrongUser.getId()));
        assertThat(exp).isNotNull();
    }

    @Test
    void updateOfferorStatusRegistrationThrowsInvalidValueExTest() {
        Exception exp = assertThrows(InvalidValueException.class, () -> userService.updateOfferorStatusRegistration(wrongUserRegisteredStatus, correctId));
        assertThat(exp).isNotNull();
    }

    @Test
    void getAllProfileImageTest() {
        assertThat(userService.getAllProfileImage()).isNotNull();
    }

    @Test
    void saveProfileImageTest() throws ProfileImageSavingException {
        ProfileImage profileImageSaved = userService.saveProfileImage(profileImage);
        assertThat(profileImage.equals(profileImageSaved));
    }

    @Test
    void saveProfileImageThrowsExTest() {
        Exception exp = assertThrows(ProfileImageSavingException.class, () -> userService.saveProfileImage(wrongProfileImage));
        assertThat(exp).isNotNull();
    }

    @Test
    void getProfileImageByIdTest() throws ProfileImageNotFoundException {
        ProfileImage profileImageFound = userService.getProfileImageById(correctId);
        assertThat(profileImage.equals(profileImageFound));
    }

    @Test
    void getProfileImageByIdThrowsExTest() {
        Exception exp = assertThrows(ProfileImageNotFoundException.class, () -> userService.getProfileImageById(wrongProfileImage.getId()));
        assertThat(exp).isNotNull();
    }

    @Test
    void deleteProfileImageTest() throws ProfileImageNotFoundException {
        ProfileImage profileImageDeleted = userService.deleteProfileImage(profileImage);
        assertThat(profileImage.equals(profileImageDeleted));
    }

    @Test
    void deleteProfileImageThrowsExTest() {
        Exception exp = assertThrows(ProfileImageNotFoundException.class, () -> userService.deleteProfileImage(wrongProfileImage));
        assertThat(exp).isNotNull();
    }



}
