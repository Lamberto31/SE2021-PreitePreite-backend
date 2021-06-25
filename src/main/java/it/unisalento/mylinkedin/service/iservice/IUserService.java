package it.unisalento.mylinkedin.service.iservice;

import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.InvalidValueException;
import it.unisalento.mylinkedin.exception.post.PostNotFoundException;
import it.unisalento.mylinkedin.exception.user.*;

import java.util.List;

public interface IUserService {
    List<User> getAll();

    User save(User user) throws UserSavingException;

    User getById(int id) throws UserNotFoundException;

    User delete(User user) throws UserNotFoundException;

    User getByEmail(String email) throws UserNotFoundException;


    //APPLICANT
    Applicant getApplicantById(int id) throws UserNotFoundException;

    List<Applicant> getApplicantByStatus(String status) throws UserNotFoundException ;

    void updateApplicantStatusRegistration(String status, int id) throws UserNotFoundException, InvalidValueException;


    //OFFEROR
    Offeror getOfferorById(int id) throws UserNotFoundException;

    List<Offeror> getOfferorByStatus(String status) throws UserNotFoundException;

    void updateOfferorStatusRegistration(String status, int id) throws UserNotFoundException, InvalidValueException;


    //PROFILE IMAGE
    List<ProfileImage> getAllProfileImage();

    ProfileImage saveProfileImage(ProfileImage profileImage) throws ProfileImageSavingException;

    ProfileImage getProfileImageById(int id) throws ProfileImageNotFoundException;

    ProfileImage deleteProfileImage(ProfileImage profileImage) throws ProfileImageNotFoundException;


    //MESSAGE
    List<Message> getAllMessage();

    Message saveMessage(Message message) throws MessageSavingException;

    Message getMessageById(int id) throws MessageNotFoundException;

    Message deleteMessage(Message message) throws MessageNotFoundException;

    List<Message> getMessageBetweenTwoUser(User user1, User user2) throws MessageNotFoundException;

    List<Message> getMessageBySender(User sender) throws MessageNotFoundException;

    List<Message> getMessageByReceiver(User receiver) throws MessageNotFoundException;

    List<Message> getMessageSentOrReceivedByUser(User user) throws MessageNotFoundException;

    List<Message> getMessageByReceiverAndNotRead(User receiver) throws MessageNotFoundException;

    void updateMessageIsRead(boolean isRead, int id) throws MessageNotFoundException;


    //COMPANY
    List<Company> getAllCompany();

    Company saveCompany(Company company) throws CompanySavingException;

    Company getCompanyById(int id) throws CompanyNotFoundException;

    Company deleteCompany(Company company) throws CompanyNotFoundException;
}
