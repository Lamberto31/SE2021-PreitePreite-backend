package it.unisalento.mylinkedin.service.iservice;

import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.InvalidValueException;
import it.unisalento.mylinkedin.exception.user.*;

import java.util.List;

public interface IUserService {
    List<User> getAll();

    User save(User user) throws UserSavingException;

    User getById(int id) throws UserNotFoundException;

    void delete(User user) throws UserNotFoundException;

    User getByEmail(String email) throws UserNotFoundException;


    //APPLICANT
    List<Applicant> getApplicantByStatus(String status) throws UserNotFoundException ;

    //OFFEROR
    List<Offeror> getOfferorByStatus(String status) throws UserNotFoundException;

    //OFFEROR E APPLICANT
    void updateStatusRegistration(String status, int id) throws UserNotFoundException, InvalidValueException;


    //PROFILE IMAGE
    List<ProfileImage> getAllProfileImage();

    ProfileImage saveProfileImage(ProfileImage profileImage) throws ProfileImageSavingException;

    ProfileImage getProfileImageById(int id) throws ProfileImageNotFoundException;

    void deleteProfileImage(ProfileImage profileImage) throws ProfileImageNotFoundException;


    //MESSAGE
    List<Message> getAllMessage();

    Message saveMessage(Message message) throws MessageSavingException;

    Message getMessageById(int id) throws MessageNotFoundException;

    void deleteMessage(Message message) throws MessageNotFoundException;

    List<Message> getMessageBySenderAndReceiver(User sender, User receiver) throws MessageNotFoundException;


    //COMPANY
    List<Company> getAllCompany();

    Company saveCompany(Company company) throws CompanySavingException;

    Company getCompanyById(int id) throws CompanyNotFoundException;

    void deleteCompany(Company company) throws CompanyNotFoundException;
}
