package it.unisalento.mylinkedin.service.serviceimpl;

import it.unisalento.mylinkedin.dao.UserRepository;
import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.service.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
//TODO: Definire tutti i metodi e mettere Transactional Annotation
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<Applicant> getApplicantByStatus(String status) {
        return null;
    }

    @Override
    public List<Offeror> getOfferorByStatus(String status) {
        return null;
    }

    @Override
    public void updateStatusRegistration(String status, int id) {

    }

    @Override
    public List<ProfileImage> getAllProfileImage() {
        return null;
    }

    @Override
    public ProfileImage saveProfileImage(ProfileImage profileImage) {
        return null;
    }

    @Override
    public ProfileImage getProfileImageById(int id) {
        return null;
    }

    @Override
    public void deleteProfileImage(int id) {

    }

    @Override
    public List<Message> getAllMessage() {
        return null;
    }

    @Override
    public Message saveMessage(Message message) {
        return null;
    }

    @Override
    public Message getMessageById(int id) {
        return null;
    }

    @Override
    public void deleteMessage(int id) {

    }

    @Override
    public List<Company> getAllCompany() {
        return null;
    }

    @Override
    public Company saveCompany(Company company) {
        return null;
    }

    @Override
    public Company getCompanyById(int id) {
        return null;
    }

    @Override
    public void deleteCompany(int id) {

    }
}
