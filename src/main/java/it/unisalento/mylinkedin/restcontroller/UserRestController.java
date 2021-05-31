package it.unisalento.mylinkedin.restcontroller;

import it.unisalento.mylinkedin.dto.*;
import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.post.AttributeNotFoundException;
import it.unisalento.mylinkedin.exception.post.PostNotFoundException;
import it.unisalento.mylinkedin.exception.post.StructureNotFoundException;
import it.unisalento.mylinkedin.exception.user.*;
import it.unisalento.mylinkedin.service.iservice.IPostService;
import it.unisalento.mylinkedin.service.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/user")
public class UserRestController {

    @Autowired
    IUserService userService;

    @Autowired
    IPostService postService;

    @GetMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getById(@PathVariable int id) throws UserNotFoundException {

        User user = userService.getById(id);
        return new UserDTO().convertToDto(user);
    }

    @PostMapping(value="/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO save(@RequestBody @Valid UserDTO userDTO) throws UserSavingException, ParseException {

        User user = new User().convertToEntity(userDTO);
        User userSaved = userService.save(user);
        userDTO.setId(userSaved.getId());
        return userDTO;
    }

    @GetMapping(value = "/profileImage/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProfileImageDTO getProfileImagetById(@PathVariable int id) throws ProfileImageNotFoundException {

        ProfileImage profileImage = userService.getProfileImageById(id);
        return new ProfileImageDTO().convertToDto(profileImage);
    }

    @PostMapping(value="/profileImage/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProfileImageDTO saveProfileImage(@RequestBody @Valid ProfileImageDTO profileImageDTO) throws ParseException, ProfileImageSavingException {

        ProfileImage profileImage = new ProfileImage().convertToEntity(profileImageDTO);
        ProfileImage profileImageSaved = userService.saveProfileImage(profileImage);
        profileImageDTO.setId(profileImageSaved.getId());
        return profileImageDTO;
    }

    @GetMapping(value = "/company/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CompanyDTO getCompanyById(@PathVariable int id) throws CompanyNotFoundException {

        Company company = userService.getCompanyById(id);
        return new CompanyDTO().convertToDto(company);
    }

    @PostMapping(value="/company/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CompanyDTO saveProfileImage(@RequestBody @Valid CompanyDTO companyDTO) throws CompanySavingException {

        Company company = new Company().convertToEntity(companyDTO);
        Company companySaved = userService.saveCompany(company);
        companyDTO.setId(companySaved.getId());
        return companyDTO;
    }

    @GetMapping(value = "/company/getCompanyByOfferorId/{offerorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CompanyDTO getCompanyByOfferorId(@PathVariable int offerorId) throws UserNotFoundException {
        Offeror offeror =  userService.getOfferorById(offerorId);
        Company company = offeror.getCompany();
        return new CompanyDTO().convertToDto(company);
    }

    @GetMapping(value = "/post/getPublic", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostDTO> getPostPublic() throws PostNotFoundException {

        List<Post> postList = postService.getByIsPrivate(false);
        List<PostDTO> postDTOList = new ArrayList<>();
        for(Post post: postList) {
            postDTOList.add(new PostDTO().convertToDto(post));
        }
        return postDTOList;
    }

    @GetMapping(value= "/getByInterested/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getByInterestedPost(@PathVariable("postId") int postId) throws PostNotFoundException, UserNotFoundException {
        Post post = postService.getById(postId);

        List<User> userList = postService.getUserByInterestedPost(post);
        List<UserDTO> userDTOList = new ArrayList<>();
        for(User user: userList) {
            userDTOList.add(new UserDTO().convertToDto(user));
        }
        return userDTOList;
    }


}
