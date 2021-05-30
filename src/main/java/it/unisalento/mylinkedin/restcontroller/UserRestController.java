package it.unisalento.mylinkedin.restcontroller;

import it.unisalento.mylinkedin.dto.MessageDTO;
import it.unisalento.mylinkedin.dto.OfferorDTO;
import it.unisalento.mylinkedin.dto.ProfileImageDTO;
import it.unisalento.mylinkedin.dto.UserDTO;
import it.unisalento.mylinkedin.entities.Message;
import it.unisalento.mylinkedin.entities.Offeror;
import it.unisalento.mylinkedin.entities.ProfileImage;
import it.unisalento.mylinkedin.entities.User;
import it.unisalento.mylinkedin.exception.user.*;
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

    @GetMapping(value = "/ProfileImage/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProfileImageDTO getProfileImagetById(@PathVariable int id) throws ProfileImageNotFoundException {

        ProfileImage profileImage = userService.getProfileImageById(id);
        return new ProfileImageDTO().convertToDto(profileImage);
    }

    @PostMapping(value="/ProfileImage/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProfileImageDTO saveProfileImage(@RequestBody @Valid ProfileImageDTO profileImageDTO) throws ParseException, ProfileImageSavingException {

        ProfileImage profileImage = new ProfileImage().convertToEntity(profileImageDTO);
        ProfileImage profileImageSaved = userService.saveProfileImage(profileImage);
        profileImageDTO.setId(profileImageSaved.getId());
        return profileImageDTO;
    }

    @GetMapping(value = "/Message/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MessageDTO getMessagetById(@PathVariable int id) throws MessageNotFoundException {

        Message message = userService.getMessageById(id);
        return new MessageDTO().convertToDto(message);
    }

    @PostMapping(value="/Message/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MessageDTO saveMessage(@RequestBody @Valid MessageDTO messageDTO) throws ParseException, MessageSavingException {

        Message message = new Message().convertToEntity(messageDTO);
        Message messageSaved = userService.saveMessage(message);
        messageDTO.setId(messageSaved.getId());
        return messageDTO;
    }

    @GetMapping(value= "/Message/getBySenderAndReceiver/{senderId}/{receiverId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageDTO> getMessageBySenderAndReceiver(@PathVariable("senderId") int senderId, @PathVariable("receiverId") int receiverId) throws MessageNotFoundException, UserNotFoundException {
        User sender = userService.getById(senderId);
        User receiver = userService.getById(receiverId);

        List<Message> messageList = userService.getMessageBySenderAndReceiver(sender, receiver);
        List<MessageDTO> messageDTOList = new ArrayList<>();
        for(Message message: messageList) {
            messageDTOList.add(new MessageDTO().convertToDto(message));
        }
        return messageDTOList;
    }
}
