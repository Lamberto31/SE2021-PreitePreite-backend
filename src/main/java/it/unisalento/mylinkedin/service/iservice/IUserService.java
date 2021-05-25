package it.unisalento.mylinkedin.service.iservice;

import it.unisalento.mylinkedin.entities.Applicant;
import it.unisalento.mylinkedin.entities.Offeror;
import it.unisalento.mylinkedin.entities.ProfileImage;
import it.unisalento.mylinkedin.entities.User;

import java.util.List;

public interface IUserService {
    List<User> getAll();

    User save(User user);

    User getById(int id);

    void delete(int id);

    User getByEmail(String email);


    //APPLICANT
    List<Applicant> getApplicantByStatus(String status);

    //OFFEROR
    List<Offeror> getOfferorByStatus(String status);

    //OFFEROR E APPLICANT
    void updateStatusRegistration(String status, int id);

    //PROFILE IMAGE
    List<ProfileImage> getAllProfileImage();

    ProfileImage saveProfileImage(ProfileImage profileImage);

    ProfileImage getProfileImageById(int id);

    void deleteProfileImage(int id);

}
