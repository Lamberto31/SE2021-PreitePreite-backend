package it.unisalento.mylinkedin.service.serviceimpl;

import it.unisalento.mylinkedin.dao.*;
import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.InvalidValueException;
import it.unisalento.mylinkedin.exception.user.*;
import it.unisalento.mylinkedin.service.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public Offeror getOfferorById(int id) throws UserNotFoundException {
        return offerorRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional(rollbackOn = UserNotFoundException.class)
    public List<Offeror> getOfferorByStatus(String status) throws UserNotFoundException {
        try {
            return offerorRepository.findByStatus(status);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = UserNotFoundException.class)
    public void updateStatusRegistration(String status, int id) throws UserNotFoundException {
        try {
            applicantRepository.updateStatusRegistration(status, id);
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
    public List<Message> getMessageBySenderAndReceiver(User sender, User receiver) throws MessageNotFoundException {
        try {
            return messageRepository.findBySenderAndReceiver(sender, receiver);
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
}
