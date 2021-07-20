package it.unisalento.mylinkedin.service.serviceimpl;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dao.*;
import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.InvalidValueException;
import it.unisalento.mylinkedin.exception.user.*;
import it.unisalento.mylinkedin.service.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdministratorRepository administratorRepository;

    @Autowired
    ApplicantRepository applicantRepository;

    @Autowired
    OfferorRepository offerorRepository;

    @Autowired
    ProfileImageRepository profileImageRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    NotificationTokenRepository notificationTokenRepository;


    @Override
    @Transactional
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(rollbackOn = UserSavingException.class)
    public User save(User user) throws UserSavingException {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UserSavingException();
        }
    }

    @Override
    @Transactional(rollbackOn = UserNotFoundException.class)
    public User getById(int id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional(rollbackOn = UserNotFoundException.class)
    public User delete(User user) throws UserNotFoundException {
        try {
            userRepository.delete(user);
            return user;
        }catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = UserNotFoundException.class)
    public User getByEmail(String email) throws UserNotFoundException {
        try {
            return userRepository.findByEmail(email);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = UserSavingException.class)
    public Applicant saveApplicant(Applicant applicant) throws UserSavingException {
        try {
            return applicantRepository.save(applicant);
        } catch (Exception e) {
            throw new UserSavingException();
        }
    }

    @Override
    @Transactional
    public List<Applicant> getAllApplicant() {
        return applicantRepository.findAll();
    }

    @Override
    @Transactional(rollbackOn = UserNotFoundException.class)
    public Applicant getApplicantById(int id) throws UserNotFoundException {
        return applicantRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional(rollbackOn = UserNotFoundException.class)
    public List<Applicant> getApplicantByStatus(String status) throws UserNotFoundException {
        try {
            List<Applicant> applicantFoundList = applicantRepository.findByStatus(status);
            if (applicantFoundList.isEmpty()) {
                throw new UserNotFoundException();
            }
            return applicantFoundList;
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = UserSavingException.class)
    public Offeror saveOfferor(Offeror offeror) throws UserSavingException {
        try {
            return offerorRepository.save(offeror);
        } catch (Exception e) {
            throw new UserSavingException();
        }
    }

    @Override
    @Transactional
    public List<Offeror> getAllOfferor() {
        return offerorRepository.findAll();
    }

    @Override
    @Transactional(rollbackOn = UserNotFoundException.class)
    public Offeror getOfferorById(int id) throws UserNotFoundException {
        return offerorRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional(rollbackOn = UserNotFoundException.class)
    public List<Offeror> getOfferorByStatus(String status) throws UserNotFoundException {
        try {
            List<Offeror> offerorFoundList = offerorRepository.findByStatus(status);
            if (offerorFoundList.isEmpty()) {
                throw new UserNotFoundException();
            }
            return offerorFoundList;
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = UserNotFoundException.class)
    public void updateApplicantStatusRegistration(String status, int id) throws UserNotFoundException, InvalidValueException {
        try {
            if (!Constants.REGISTRATION_STATUS_LIST.contains(status)) {
                throw new InvalidValueException();
            }
        } catch (Exception e) {
            throw new InvalidValueException();
        }
        try {
            applicantRepository.findById(id).orElseThrow(UserNotFoundException::new);
            applicantRepository.updateStatusRegistration(status, id);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = UserNotFoundException.class)
    public void updateOfferorStatusRegistration(String status, int id) throws UserNotFoundException, InvalidValueException {
        try {
            if (!Constants.REGISTRATION_STATUS_LIST.contains(status)) {
                throw new InvalidValueException();
            }
        } catch (Exception e) {
            throw new InvalidValueException();
        }
        try {
            offerorRepository.findById(id).orElseThrow(UserNotFoundException::new);
            offerorRepository.updateStatusRegistration(status, id);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    @Transactional
    public List<ProfileImage> getAllProfileImage() {
        return profileImageRepository.findAll();
    }

    @Override
    @Transactional(rollbackOn = ProfileImageSavingException.class)
    public ProfileImage saveProfileImage(ProfileImage profileImage) throws ProfileImageSavingException {
        try {
            return profileImageRepository.save(profileImage);
        } catch (Exception e) {
            throw new ProfileImageSavingException();
        }
    }

    @Override
    @Transactional(rollbackOn = ProfileImageNotFoundException.class)
    public ProfileImage getProfileImageById(int id) throws ProfileImageNotFoundException {
        return profileImageRepository.findById(id).orElseThrow(ProfileImageNotFoundException::new);
    }

    @Override
    @Transactional(rollbackOn = ProfileImageNotFoundException.class)
    public ProfileImage deleteProfileImage(ProfileImage profileImage) throws ProfileImageNotFoundException {
        try {
            profileImageRepository.delete(profileImage);
            return profileImage;
        } catch (Exception e) {
            throw new ProfileImageNotFoundException();
        }
    }

    @Override
    @Transactional
    public List<Message> getAllMessage() {
        return messageRepository.findAll();
    }

    @Override
    @Transactional(rollbackOn = MessageSavingException.class)
    public Message saveMessage(Message message) throws MessageSavingException {
        try {
            return messageRepository.save(message);
        } catch (Exception e) {
            throw new MessageSavingException();
        }
    }

    @Override
    @Transactional(rollbackOn = MessageNotFoundException.class)
    public Message getMessageById(int id) throws MessageNotFoundException {
        return messageRepository.findById(id).orElseThrow(MessageNotFoundException::new);
    }

    @Override
    @Transactional(rollbackOn = MessageNotFoundException.class)
    public Message deleteMessage(Message message) throws MessageNotFoundException {
        try {
            messageRepository.delete(message);
            return message;
        } catch (Exception e) {
            throw new MessageNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = MessageNotFoundException.class)
    public List<Message> getMessageBetweenTwoUser(User user1, User user2) throws MessageNotFoundException {
        try {
            List<Message> message1to2FoundList = messageRepository.findBySenderAndReceiver(user1, user2);
            List<Message> message2to1FoundList = messageRepository.findBySenderAndReceiver(user2, user1);
            if (message1to2FoundList.isEmpty() && message2to1FoundList.isEmpty()) {
                throw new MessageNotFoundException();
            }
            List<Message> messageFoundList = new ArrayList<>(message1to2FoundList);
            messageFoundList.addAll(message2to1FoundList);
            messageFoundList.sort(Comparator.comparing(Message::getPubblicationDate));
            return messageFoundList;
        } catch (Exception e) {
            throw new MessageNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = MessageNotFoundException.class)
    public List<Message> getMessageBySender(User sender) throws MessageNotFoundException {
        try {
            List<Message> messageFoundList = messageRepository.findBySender(sender);
            if (messageFoundList.isEmpty()) {
                throw new MessageNotFoundException();
            }
            return messageFoundList;
        } catch (Exception e) {
            throw new MessageNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = MessageNotFoundException.class)
    public List<Message> getMessageByReceiver(User receiver) throws MessageNotFoundException {
        try {
            List<Message> messageFoundList = messageRepository.findByReceiver(receiver);
            if (messageFoundList.isEmpty()) {
                throw new MessageNotFoundException();
            }
            return messageFoundList;
        } catch (Exception e) {
            throw new MessageNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = MessageNotFoundException.class)
    public List<Message> getMessageSentOrReceivedByUser(User user) throws MessageNotFoundException {
        try {
            List<Message> messageFoundList = messageRepository.findBySenderOrReceiverOrderByPubblicationDateDesc(user, user);
            if (messageFoundList.isEmpty()) {
                throw new MessageNotFoundException();
            }
            return messageFoundList;
        } catch (Exception e) {
            throw new MessageNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = MessageNotFoundException.class)
    public List<Message> getMessageByReceiverAndNotRead(User receiver) throws MessageNotFoundException {
        try {
            List<Message> messageFoundList = messageRepository.findByReceiverAndIsRead(receiver, false);
            if (messageFoundList.isEmpty()) {
                throw new MessageNotFoundException();
            }
            return messageFoundList;
        } catch (Exception e) {
            throw new MessageNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = MessageNotFoundException.class)
    public void updateMessageIsRead(boolean isRead, int id) throws MessageNotFoundException {
        try {
            messageRepository.findById(id).orElseThrow(MessageNotFoundException::new);
            messageRepository.updateIsRead(isRead, id);
        } catch (MessageNotFoundException e) {
            throw new MessageNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = MessageNotFoundException.class)
    public List<Message> getMessageBySenderAndReceiverAndNotRead(User sender, User receiver) throws MessageNotFoundException {
        try {
            List<Message> messageFoundList = messageRepository.findBySenderAndReceiverAndIsRead(sender, receiver, false);
            if (messageFoundList.isEmpty()) {
                throw new MessageNotFoundException();
            }
            return messageFoundList;
        } catch (Exception e) {
            throw new MessageNotFoundException();
        }
    }

    @Override
    @Transactional
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    @Transactional(rollbackOn = CompanySavingException.class)
    public Company saveCompany(Company company) throws CompanySavingException {
        try {
            return companyRepository.save(company);
        } catch (Exception e) {
            throw new CompanySavingException();
        }
    }

    @Override
    @Transactional(rollbackOn = CompanyNotFoundException.class)
    public Company getCompanyById(int id) throws CompanyNotFoundException {
        return companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
    }

    @Override
    @Transactional(rollbackOn = CompanyNotFoundException.class)
    public Company deleteCompany(Company company) throws CompanyNotFoundException {
        try {
            companyRepository.delete(company);
            return company;
        } catch (Exception e) {
            throw new CompanyNotFoundException();
        }
    }

    @Override
    @Transactional
    public List<NotificationToken> getAllNotificationToken() {
        return notificationTokenRepository.findAll();
    }

    @Override
    @Transactional(rollbackOn = NotificationTokenSavingException.class)
    public NotificationToken saveNotificationToken(NotificationToken notificationToken) throws NotificationTokenSavingException {
        try {
            notificationToken = saveAwsEndpointArn(notificationToken);
            return notificationTokenRepository.save(notificationToken);
        } catch (Exception e) {
            throw new NotificationTokenSavingException();
        }
    }

    @Override
    @Transactional(rollbackOn = NotificationTokenNotFoundException.class)
    public NotificationToken getNotificationTokenById(int id) throws NotificationTokenNotFoundException {
        return notificationTokenRepository.findById(id).orElseThrow(NotificationTokenNotFoundException::new);
    }

    @Override
    @Transactional(rollbackOn = NotificationTokenNotFoundException.class)
    public NotificationToken deleteNotificationToken(NotificationToken notificationToken) throws NotificationTokenNotFoundException {
        try {
            notificationTokenRepository.delete(notificationToken);
            return notificationToken;
        } catch (Exception e) {
            throw new NotificationTokenNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = NotificationTokenNotFoundException.class)
    public List<NotificationToken> getNotificationTokenByUser(User user) throws NotificationTokenNotFoundException {
        try {
            List<NotificationToken> notificationTokenFoundList = notificationTokenRepository.findByUser(user);
            if (notificationTokenFoundList.isEmpty()) {
                throw new NotificationTokenNotFoundException();
            }
            return notificationTokenFoundList;
        } catch (Exception e) {
            throw new NotificationTokenNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = NotificationTokenNotFoundException.class)
    public NotificationToken getNotificationTokenByToken(String token) throws NotificationTokenNotFoundException {
        try {
            NotificationToken notificationTokenFound = notificationTokenRepository.findByToken(token);
            if (notificationTokenFound == null) {
                throw new NotificationTokenNotFoundException();
            }
            return notificationTokenFound;
        } catch (Exception e) {
            throw new NotificationTokenNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = NotificationTokenSavingException.class)
    public NotificationToken saveAwsEndpointArn(NotificationToken notificationToken) throws NotificationTokenSavingException {
        RestTemplate restTemplate = new RestTemplate();

        String uri = Constants.AWS_URI_API + Constants.AWS_URI_PUSHNOTIFICATION +Constants.AWS_URI_TOKEN;

        try {
            if ( notificationToken.getToken() == null) {
                throw new NotificationTokenSavingException();
            }
            String reqBody = "{\"token\":\"" + notificationToken.getToken() + "\"}";

            NotificationToken result = restTemplate.postForObject(uri, reqBody, NotificationToken.class);
            assert result != null : "Result is null";
            notificationToken.setAwsEndpointArn(result.getAwsEndpointArn());
            return notificationToken;
        } catch (Exception e) {
            throw new NotificationTokenSavingException();
        }
    }
}
