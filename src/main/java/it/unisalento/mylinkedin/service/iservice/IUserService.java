package it.unisalento.mylinkedin.service.iservice;

import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.InvalidValueException;
import it.unisalento.mylinkedin.exception.user.*;

import java.util.List;

public interface IUserService {
    List<User> getAll();

    User save(User user) throws UserSavingException;

    User getById(int id) throws UserNotFoundException;

    void delete(int id) throws UserNotFoundException;

    User getByEmail(String email) throws UserNotFoundException;


    //APPLICANT
    List<Applicant> getApplicantByStatus(String status) throws UserNotFoundException ;

    //OFFEROR
    List<Offeror> getOfferorByStatus(String status) throws UserNotFoundException;

    //OFFEROR E APPLICANT
    void updateStatusRegistration(String status, int id) throws UserNotFoundException, InvalidValueException;


    //PROFILE IMAGE
    List<ProfileImage> getAllProfileImage();

    ProfileImage saveProfileImage(ProfileImage profileImage) throws UserSavingException;

    ProfileImage getProfileImageById(int id) throws ProfileImageNotFoundException;

    void deleteProfileImage(int id) throws ProfileImageNotFoundException;


    //MESSAGE
    List<Message> getAllMessage();

    Message saveMessage(Message message) throws MessageSavingException;

    Message getMessageById(int id) throws MessageNotFoundException;

    void deleteMessage(int id) throws MessageNotFoundException;


    //COMPANY
    List<Company> getAllCompany();

    Company saveCompany(Company company) throws CompanySavingException;

    Company getCompanyById(int id) throws CompanyNotFoundException;

    void deleteCompany(int id) throws CompanyNotFoundException;



}
