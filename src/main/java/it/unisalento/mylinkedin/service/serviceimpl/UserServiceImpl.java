package it.unisalento.mylinkedin.service.serviceimpl;

import it.unisalento.mylinkedin.dao.*;
import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.InvalidValueException;
import it.unisalento.mylinkedin.exception.user.*;
import it.unisalento.mylinkedin.service.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//TODO: Mettere Transactional Annotation e definire metodi mancanti
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
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) throws UserSavingException {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UserSavingException();
        }
    }

    @Override
    public User getById(int id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void delete(User user) throws UserNotFoundException {
        try {
            userRepository.delete(user);
        }catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public User getByEmail(String email) throws UserNotFoundException {
        try {
            return userRepository.findByEmail(email);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<Applicant> getApplicantByStatus(String status) throws UserNotFoundException {
        try {
            return applicantRepository.findByStatus(status);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<Offeror> getOfferorByStatus(String status) throws UserNotFoundException {
        try {
            return offerorRepository.findByStatus(status);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public void updateStatusRegistration(String status, int id) throws UserNotFoundException, InvalidValueException {
        try {
            applicantRepository.updateStatusRegistration(status, id);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<ProfileImage> getAllProfileImage() {
        return profileImageRepository.findAll();
    }

    @Override
    public ProfileImage saveProfileImage(ProfileImage profileImage) throws UserSavingException {
        try {
            return profileImageRepository.save(profileImage);
        } catch (Exception e) {
            throw new UserSavingException();
        }
    }

    @Override
    public ProfileImage getProfileImageById(int id) throws ProfileImageNotFoundException {
        return profileImageRepository.findById(id).orElseThrow(ProfileImageNotFoundException::new);
    }

    @Override
    public void deleteProfileImage(ProfileImage profileImage) throws ProfileImageNotFoundException {
        try {
            profileImageRepository.delete(profileImage);
        } catch (Exception e) {
            throw new ProfileImageNotFoundException();
        }
    }

    @Override
    public List<Message> getAllMessage() {
        return messageRepository.findAll();
    }

    @Override
    public Message saveMessage(Message message) throws MessageSavingException {
        try {
            return messageRepository.save(message);
        } catch (Exception e) {
            throw new MessageSavingException();
        }
    }

    @Override
    public Message getMessageById(int id) throws MessageNotFoundException {
        return messageRepository.findById(id).orElseThrow(MessageNotFoundException::new);
    }

    @Override
    public void deleteMessage(Message message) throws MessageNotFoundException {
        try {
            messageRepository.delete(message);
        } catch (Exception e) {
            throw new MessageNotFoundException();
        }
    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public Company saveCompany(Company company) throws CompanySavingException {
        try {
            return companyRepository.save(company);
        } catch (Exception e) {
            throw new CompanySavingException();
        }
    }

    @Override
    public Company getCompanyById(int id) throws CompanyNotFoundException {
        return companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
    }

    @Override
    public void deleteCompany(Company company) throws CompanyNotFoundException {
        try {
            companyRepository.delete(company);
        } catch (Exception e) {
            throw new CompanyNotFoundException();
        }
    }
}
