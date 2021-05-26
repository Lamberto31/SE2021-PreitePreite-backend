package it.unisalento.mylinkedin.service.serviceimpl;

import it.unisalento.mylinkedin.dao.UserRepository;
import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.InvalidValueException;
import it.unisalento.mylinkedin.exception.user.*;
import it.unisalento.mylinkedin.service.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//TODO: Definire tutti i metodi e mettere Transactional Annotation
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;


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
        return null;
    }

    @Override
    public List<Applicant> getApplicantByStatus(String status) throws UserNotFoundException {
        return null;
    }

    @Override
    public List<Offeror> getOfferorByStatus(String status) throws UserNotFoundException {
        return null;
    }

    @Override
    public void updateStatusRegistration(String status, int id) throws UserNotFoundException, InvalidValueException {

    }

    @Override
    public List<ProfileImage> getAllProfileImage() {
        return null;
    }

    @Override
    public ProfileImage saveProfileImage(ProfileImage profileImage) throws UserSavingException {
        return null;
    }

    @Override
    public ProfileImage getProfileImageById(int id) throws ProfileImageNotFoundException {
        return null;
    }

    @Override
    public void deleteProfileImage(int id) throws ProfileImageNotFoundException {

    }

    @Override
    public List<Message> getAllMessage() {
        return null;
    }

    @Override
    public Message saveMessage(Message message) throws MessageSavingException {
        return null;
    }

    @Override
    public Message getMessageById(int id) throws MessageNotFoundException {
        return null;
    }

    @Override
    public void deleteMessage(int id) throws MessageNotFoundException {

    }

    @Override
    public List<Company> getAllCompany() {
        return null;
    }

    @Override
    public Company saveCompany(Company company) throws CompanySavingException {
        return null;
    }

    @Override
    public Company getCompanyById(int id) throws CompanyNotFoundException {
        return null;
    }

    @Override
    public void deleteCompany(int id) throws CompanyNotFoundException {

    }
}
