package it.unisalento.mylinkedin.iservice;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dao.*;
import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.InvalidValueException;
import it.unisalento.mylinkedin.exception.post.PostNotFoundException;
import it.unisalento.mylinkedin.exception.user.*;
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

    private Message message;
    private Message wrongMessage;
    private List<Message> messageList;
    private boolean isRead;
    private User user2;
    private Message message2;
    private Message message3;
    private List<Message> messageList2;
    private List<Message> orderedMessageList;

    private Company company;
    private Company wrongCompany;

    @BeforeEach
    void init() throws ParseException {

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

        //when(applicantRepository.findByStatus(registeredUserNotFoundStatus)).thenThrow(UserNotFoundException.class);

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

        wrongUserRegisteredStatus = "wrong";

        //ProfileImage

        this.profileImage = new ProfileImage();
        this.profileImage.setDescription("testDescription");
        this.profileImage.setPubblicationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.profileImage.setImagePath("testImagePath");

        when(profileImageRepository.save(refEq(profileImage))).thenReturn(profileImage);

        this.wrongProfileImage = new ProfileImage();

        when(profileImageRepository.save(refEq(wrongProfileImage))).thenThrow(IllegalArgumentException.class);

        when(profileImageRepository.findById(correctId)).thenReturn(java.util.Optional.ofNullable(profileImage));

        doThrow(new IllegalArgumentException()).when(profileImageRepository).delete(wrongProfileImage);

        //Message

        this.user2 = new User();
        this.user2.setName("testName2");
        this.user2.setSurname("testSurname2");
        this.user2.setEmail("emailtest2@test.com");
        this.user2.setPassword("testPassword2");
        this.user2.setBirthDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.user2.setDescription("testDescription2");

        this.message = new Message();
        this.message.setText("testName");
        this.message.setImagePath("testImagePath");
        this.message.setPubblicationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 01:00"));
        this.message.setSender(user);
        this.message.setReceiver(user2);

        when(messageRepository.save(refEq(message))).thenReturn(message);

        this.wrongMessage = new Message();

        when(messageRepository.save(refEq(wrongMessage))).thenThrow(IllegalArgumentException.class);

        when(messageRepository.findById(correctId)).thenReturn(java.util.Optional.ofNullable(message));

        doThrow(new IllegalArgumentException()).when(messageRepository).delete(wrongMessage);

        this.messageList = new ArrayList<>();
        messageList.add(message);

        when(messageRepository.findBySender(user)).thenReturn(messageList);

        when(messageRepository.findByReceiver(user)).thenReturn(messageList);

        when(messageRepository.findBySenderOrReceiverOrderByPubblicationDateDesc(user, user)).thenReturn(messageList);

        this.isRead = false;

        when(messageRepository.findByReceiverAndIsRead(user, isRead)).thenReturn(messageList);

        this.message2 = new Message();
        this.message2.setText("testName");
        this.message2.setImagePath("testImagePath");
        this.message2.setPubblicationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.message2.setSender(user2);
        this.message2.setReceiver(user);

        this.message3 = new Message();
        this.message3.setText("testName");
        this.message3.setImagePath("testImagePath");
        this.message3.setPubblicationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 02:00"));
        this.message3.setSender(user2);
        this.message3.setReceiver(user);

        this.messageList2 = new ArrayList<>();
        messageList2.add(message2);
        messageList2.add(message3);

        this.orderedMessageList = new ArrayList<>();
        orderedMessageList.add(message2);
        orderedMessageList.add(message);
        orderedMessageList.add(message3);

        when(messageRepository.findBySenderAndReceiver(user, user2)).thenReturn(messageList);

        when(messageRepository.findBySenderAndReceiver(user2, user)).thenReturn(messageList2);

        when(messageRepository.findBySenderAndReceiverAndIsRead(user, user, isRead)).thenReturn(messageList);

        //Company

        this.company = new Company();
        this.company.setName("testName");
        this.company.setDescription("testDescription");
        this.company.setPartitaIva("testPartIva");
        this.company.setAddress("testAddress");

        when(companyRepository.save(refEq(company))).thenReturn(company);

        this.wrongCompany = new Company();

        when(companyRepository.save(refEq(wrongCompany))).thenThrow(IllegalArgumentException.class);

        when(companyRepository.findById(correctId)).thenReturn(java.util.Optional.ofNullable(company));

        doThrow(new IllegalArgumentException()).when(companyRepository).delete(wrongCompany);

    }

    @Test
    void getAllTest() {
        assertThat(userService.getAll()).isNotNull();
    }

    @Test
    void saveTest() throws UserSavingException {
        User userSaved = userService.save(user);
        assertThat(user.equals(userSaved)).isTrue();
    }

    @Test
    void saveThrowsExTest() {
        Exception exp = assertThrows(UserSavingException.class, () -> userService.save(wrongUser));
        assertThat(exp).isNotNull();
    }

    @Test
    void getByIdTest() throws UserNotFoundException {
        User userFound = userService.getById(correctId);
        assertThat(user.equals(userFound)).isTrue();
    }

    @Test
    void getByIdThrowsExTest() {
        Exception exp = assertThrows(UserNotFoundException.class, () -> userService.getById(wrongUser.getId()));
        assertThat(exp).isNotNull();
    }

    @Test
    void deleteTest() throws UserNotFoundException {
        User userDeleted = userService.delete(user);
        assertThat(user.equals(userDeleted)).isTrue();
    }

    @Test
    void deleteThrowsExTest() {
        Exception exp = assertThrows(UserNotFoundException.class, () -> userService.delete(wrongUser));
        assertThat(exp).isNotNull();
    }

    @Test
    void getByEmailTest() throws UserNotFoundException {
        User userFound = userService.getByEmail(user.getEmail());
        assertThat(user.equals(userFound)).isTrue();
    }

    @Test
    void getByEmailThrowsExTest() {
        Exception exp = assertThrows(UserNotFoundException.class, () -> userService.getByEmail(wrongUser.getEmail()));
        assertThat(exp).isNotNull();
    }

    @Test
    void getAllApplicantTest() {
        assertThat(userService.getAll()).isNotNull();
    }

    @Test
    void getApplicantByIdTest() throws UserNotFoundException {
        Applicant applicantFound = userService.getApplicantById(correctId);
        assertThat(applicant.equals(applicantFound)).isTrue();
    }

    @Test
    void getApplicantByIdThrowsExTest() {
        Exception exp = assertThrows(UserNotFoundException.class, () -> userService.getApplicantById(wrongUser.getId()));
        assertThat(exp).isNotNull();
    }

    @Test
    void getApplicantByStatusTest() throws UserNotFoundException {
        List<Applicant> applicantFoundList = userService.getApplicantByStatus(applicant.getStatus());
        assertThat(applicantList.equals(applicantFoundList)).isTrue();
    }

    @Test
    void getApplicantByStatusThrowsExTest() {
        Exception exp = assertThrows(UserNotFoundException.class, () -> userService.getApplicantByStatus(registeredUserNotFoundStatus));
        assertThat(exp).isNotNull();
    }

    @Test
    void getAllOfferorTest() {
        assertThat(userService.getAll()).isNotNull();
    }

    @Test
    void getOfferorByIdTest() throws UserNotFoundException {
        Offeror offerorFound = userService.getOfferorById(correctId);
        assertThat(offeror.equals(offerorFound)).isTrue();
    }

    @Test
    void getOfferorByIdThrowsExTest() {
        Exception exp = assertThrows(UserNotFoundException.class, () -> userService.getOfferorById(wrongUser.getId()));
        assertThat(exp).isNotNull();
    }

    @Test
    void getOfferorByStatusTest() throws UserNotFoundException {
        List<Offeror> offerorFoundList = userService.getOfferorByStatus(offeror.getStatus());
        assertThat(offerorList.equals(offerorFoundList)).isTrue();
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

    //ProfileImage
    @Test
    void getAllProfileImageTest() {
        assertThat(userService.getAllProfileImage()).isNotNull();
    }

    @Test
    void saveProfileImageTest() throws ProfileImageSavingException {
        ProfileImage profileImageSaved = userService.saveProfileImage(profileImage);
        assertThat(profileImage.equals(profileImageSaved)).isTrue();
    }

    @Test
    void saveProfileImageThrowsExTest() {
        Exception exp = assertThrows(ProfileImageSavingException.class, () -> userService.saveProfileImage(wrongProfileImage));
        assertThat(exp).isNotNull();
    }

    @Test
    void getProfileImageByIdTest() throws ProfileImageNotFoundException {
        ProfileImage profileImageFound = userService.getProfileImageById(correctId);
        assertThat(profileImage.equals(profileImageFound)).isTrue();
    }

    @Test
    void getProfileImageByIdThrowsExTest() {
        Exception exp = assertThrows(ProfileImageNotFoundException.class, () -> userService.getProfileImageById(wrongProfileImage.getId()));
        assertThat(exp).isNotNull();
    }

    @Test
    void deleteProfileImageTest() throws ProfileImageNotFoundException {
        ProfileImage profileImageDeleted = userService.deleteProfileImage(profileImage);
        assertThat(profileImage.equals(profileImageDeleted)).isTrue();
    }

    @Test
    void deleteProfileImageThrowsExTest() {
        Exception exp = assertThrows(ProfileImageNotFoundException.class, () -> userService.deleteProfileImage(wrongProfileImage));
        assertThat(exp).isNotNull();
    }

    //Message
    @Test
    void getAllMessageTest() {
        assertThat(userService.getAllMessage()).isNotNull();
    }

    @Test
    void saveMessageTest() throws MessageSavingException {
        Message messageSaved = userService.saveMessage(message);
        assertThat(message.equals(messageSaved)).isTrue();
    }

    @Test
    void saveMessageThrowsExTest() {
        Exception exp = assertThrows(MessageSavingException.class, () -> userService.saveMessage(wrongMessage));
        assertThat(exp).isNotNull();
    }

    @Test
    void getMessageByIdTest() throws MessageNotFoundException {
        Message messageFound = userService.getMessageById(correctId);
        assertThat(message.equals(messageFound)).isTrue();
    }

    @Test
    void getMessageByIdThrowsExTest() {
        Exception exp = assertThrows(MessageNotFoundException.class, () -> userService.getMessageById(wrongMessage.getId()));
        assertThat(exp).isNotNull();
    }

    @Test
    void deleteMessageTest() throws MessageNotFoundException {
        Message messageDeleted = userService.deleteMessage(message);
        assertThat(message.equals(messageDeleted)).isTrue();
    }

    @Test
    void deleteMessageThrowsExTest() {
        Exception exp = assertThrows(MessageNotFoundException.class, () -> userService.deleteMessage(wrongMessage));
        assertThat(exp).isNotNull();
    }

    @Test
    void getMessageBySenderAndReceiverTest() throws MessageNotFoundException {
        List<Message> messageFoundList = userService.getMessageBetweenTwoUser(user, user2);
        assertThat(orderedMessageList.equals(messageFoundList)).isTrue();
    }

    @Test
    void getMessageBySenderAndReceiverThrowsExTest() {
        Exception exp = assertThrows(MessageNotFoundException.class, () -> userService.getMessageBetweenTwoUser(wrongUser, wrongUser));
        assertThat(exp).isNotNull();
    }

    @Test
    void getMessageBySenderTest() throws MessageNotFoundException {
        List<Message> messageFoundList = userService.getMessageBySender(user);
        assertThat(messageList.equals(messageFoundList)).isTrue();
    }

    @Test
    void getMessageBySenderThrowsExTest() {
        Exception exp = assertThrows(MessageNotFoundException.class, () -> userService.getMessageBySender(wrongUser));
        assertThat(exp).isNotNull();
    }

    @Test
    void getMessageByReceiverTest() throws MessageNotFoundException {
        List<Message> messageFoundList = userService.getMessageByReceiver(user);
        assertThat(messageList.equals(messageFoundList)).isTrue();
    }

    @Test
    void getMessageByReceiverThrowsExTest() {
        Exception exp = assertThrows(MessageNotFoundException.class, () -> userService.getMessageByReceiver(wrongUser));
        assertThat(exp).isNotNull();
    }

    @Test
    void getMessageSentOrReceivedByUserTest() throws MessageNotFoundException {
        List<Message> messageFoundList = userService.getMessageSentOrReceivedByUser(user);
        assertThat(messageList.equals(messageFoundList)).isTrue();
    }

    @Test
    void getMessageSentOrReceivedByUserThrowsExTest() {
        Exception exp = assertThrows(MessageNotFoundException.class, () -> userService.getMessageSentOrReceivedByUser(wrongUser));
        assertThat(exp).isNotNull();
    }

    @Test
    void getMessageByReceiverAndNotReadTest() throws MessageNotFoundException {
        List<Message> messageFoundList = userService.getMessageByReceiverAndNotRead(user);
        assertThat(messageList.equals(messageFoundList)).isTrue();
    }

    @Test
    void getMessageByReceiverAndNotReadTestThrowsExTest() {
        Exception exp = assertThrows(MessageNotFoundException.class, () -> userService.getMessageByReceiverAndNotRead(wrongUser));
        assertThat(exp).isNotNull();
    }

    @Test
    void updateMessageIsReadTest() {
        assertDoesNotThrow(() -> userService.updateMessageIsRead(message.isRead(), correctId));
    }

    @Test
    void updateMessageIsReadThrowsExTest() {
        Exception exp = assertThrows(MessageNotFoundException.class, () -> userService.updateMessageIsRead(message.isRead(), wrongMessage.getId()));
        assertThat(exp).isNotNull();
    }

    @Test
    void getMessageBySenderAndReceiverAndNotReadTest() throws MessageNotFoundException {
        List<Message> messageFoundList = userService.getMessageBySenderAndReceiverAndNotRead(user, user);
        assertThat(messageList.equals(messageFoundList)).isTrue();
    }

    @Test
    void getMessageBySenderAndReceiverAndNotReadThrowsExTest() {
        Exception exp = assertThrows(MessageNotFoundException.class, () -> userService.getMessageBySenderAndReceiverAndNotRead(wrongUser, wrongUser));
        assertThat(exp).isNotNull();
    }

    //Company
    @Test
    void getAllCompanyTest() {
        assertThat(userService.getAllCompany()).isNotNull();
    }

    @Test
    void saveCompanyTest() throws CompanySavingException {
        Company companySaved = userService.saveCompany(company);
        assertThat(company.equals(companySaved)).isTrue();
    }

    @Test
    void saveCompanyThrowsExTest() {
        Exception exp = assertThrows(CompanySavingException.class, () -> userService.saveCompany(wrongCompany));
        assertThat(exp).isNotNull();
    }

    @Test
    void getCompanyByIdTest() throws CompanyNotFoundException {
        Company companyFound = userService.getCompanyById(correctId);
        assertThat(company.equals(companyFound)).isTrue();
    }

    @Test
    void getCompanyByIdThrowsExTest() {
        Exception exp = assertThrows(CompanyNotFoundException.class, () -> userService.getCompanyById(wrongCompany.getId()));
        assertThat(exp).isNotNull();
    }

    @Test
    void deleteCompanyTest() throws CompanyNotFoundException {
        Company companyDeleted = userService.deleteCompany(company);
        assertThat(company.equals(companyDeleted)).isTrue();
    }

    @Test
    void deleteCompanyThrowsExTest() {
        Exception exp = assertThrows(CompanyNotFoundException.class, () -> userService.deleteCompany(wrongCompany));
        assertThat(exp).isNotNull();
    }

}
