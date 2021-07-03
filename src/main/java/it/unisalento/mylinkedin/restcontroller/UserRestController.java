package it.unisalento.mylinkedin.restcontroller;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.CompanyDTO;
import it.unisalento.mylinkedin.dto.PostDTO;
import it.unisalento.mylinkedin.dto.ProfileImageDTO;
import it.unisalento.mylinkedin.dto.UserDTO;
import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.post.PostNotFoundException;
import it.unisalento.mylinkedin.exception.user.*;
import it.unisalento.mylinkedin.service.iservice.IPostService;
import it.unisalento.mylinkedin.service.iservice.IS3Service;
import it.unisalento.mylinkedin.service.iservice.IUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value= Constants.URI_USER)
public class UserRestController {

    @Autowired
    IUserService userService;

    @Autowired
    IPostService postService;

    @Autowired
    IS3Service s3Service;

    @Value("${jsa.s3.uploadfile}")
    private String uploadFilePath;

    @Value("${jsa.s3.key}")
    private String downloadKey;

    @GetMapping(value = Constants.URI_GETBYID, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getById(@PathVariable int id) throws UserNotFoundException {

        User user = userService.getById(id);
        return new UserDTO().convertToDto(user);
    }

    @PostMapping(value=Constants.URI_SAVE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO save(@RequestBody @Valid UserDTO userDTO) throws UserSavingException, ParseException {

        User user = new User().convertToEntity(userDTO);
        User userSaved = userService.save(user);
        userDTO.setId(userSaved.getId());
        return userDTO;
    }

    @GetMapping(value = Constants.URI_PROFILEIMAGE+Constants.URI_GETBYID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProfileImageDTO getProfileImageById(@PathVariable int id) throws ProfileImageNotFoundException {

        ProfileImage profileImage = userService.getProfileImageById(id);
        return new ProfileImageDTO().convertToDto(profileImage);
    }

    @PostMapping(value=Constants.URI_PROFILEIMAGE+Constants.URI_SAVE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProfileImageDTO saveProfileImage(@RequestBody @Valid ProfileImageDTO profileImageDTO) throws ParseException, ProfileImageSavingException {

        String identifier = RandomStringUtils.randomAlphanumeric(32);
        s3Service.uploadFile(identifier, profileImageDTO.getImagePath());
        // s3Service.downloadFile("jsa-s3-upload-file.jpg");
        profileImageDTO.setImagePath(Constants.S3_IMAGEPREFIX+identifier);
        ProfileImage profileImage = new ProfileImage().convertToEntity(profileImageDTO);
        ProfileImage profileImageSaved = userService.saveProfileImage(profileImage);
        profileImageDTO.setId(profileImageSaved.getId());

        return profileImageDTO;
    }

    @GetMapping(value = Constants.URI_PROFILEIMAGE+Constants.URI_GETBYUSER, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProfileImageDTO> getProfileImageByUser(@PathVariable int userId) throws UserNotFoundException {
        User user =  userService.getById(userId);
        List<ProfileImage> profileImageList = user.getProfileImage();
        List<ProfileImageDTO> profileImageDTOList = new ArrayList<>();
        for(ProfileImage profileImage: profileImageList) {
            profileImageDTOList.add(new ProfileImageDTO().convertToDto(profileImage));
        }
        return profileImageDTOList;
    }

    @GetMapping(value = Constants.URI_COMPANY+Constants.URI_GETBYID, produces = MediaType.APPLICATION_JSON_VALUE)
    public CompanyDTO getCompanyById(@PathVariable int id) throws CompanyNotFoundException {

        Company company = userService.getCompanyById(id);
        return new CompanyDTO().convertToDto(company);
    }

    @PostMapping(value=Constants.URI_COMPANY+Constants.URI_SAVE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CompanyDTO saveCompany(@RequestBody @Valid CompanyDTO companyDTO) throws CompanySavingException {

        Company company = new Company().convertToEntity(companyDTO);
        Company companySaved = userService.saveCompany(company);
        companyDTO.setId(companySaved.getId());
        return companyDTO;
    }

    @GetMapping(value = Constants.URI_COMPANY+Constants.URI_GETBYOFFERORID, produces = MediaType.APPLICATION_JSON_VALUE)
    public CompanyDTO getCompanyByOfferorId(@PathVariable int offerorId) throws UserNotFoundException {
        Offeror offeror =  userService.getOfferorById(offerorId);
        Company company = offeror.getCompany();
        return new CompanyDTO().convertToDto(company);
    }

    @GetMapping(value = Constants.URI_POST+Constants.URI_GETPUBLIC, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostDTO> getPostPublic() throws PostNotFoundException {

        List<Post> postList = postService.getByIsPrivate(false);
        List<PostDTO> postDTOList = new ArrayList<>();
        for(Post post: postList) {
            postDTOList.add(new PostDTO().convertToDto(post));
        }
        return postDTOList;
    }

    @GetMapping(value= Constants.URI_GETBYINTERESTED, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getByInterestedPost(@PathVariable("postId") int postId) throws PostNotFoundException, UserNotFoundException {
        Post post = postService.getById(postId);

        List<User> userList = postService.getUserByInterestedPost(post);
        List<UserDTO> userDTOList = new ArrayList<>();
        for(User user: userList) {
            userDTOList.add(new UserDTO().convertToDto(user));
        }
        return userDTOList;
    }

    @GetMapping(value = Constants.URI_GETBYPOST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getByPost(@PathVariable int postId) throws UserNotFoundException, PostNotFoundException {
        Post post = postService.getById(postId);

        User user = postService.getUser(post);
        return new UserDTO().convertToDto(user);
    }

    @GetMapping(value = Constants.URI_GETALL, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUser() {
        List<User> userList = userService.getAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for(User user: userList) {
            userDTOList.add(new UserDTO().convertToDto(user));
        }
        return userDTOList;
    }
}
