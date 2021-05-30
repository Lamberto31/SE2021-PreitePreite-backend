package it.unisalento.mylinkedin.restcontroller;

import it.unisalento.mylinkedin.dto.MessageDTO;
import it.unisalento.mylinkedin.entities.Message;
import it.unisalento.mylinkedin.entities.User;
import it.unisalento.mylinkedin.exception.user.MessageNotFoundException;
import it.unisalento.mylinkedin.exception.user.MessageSavingException;
import it.unisalento.mylinkedin.exception.user.UserNotFoundException;
import it.unisalento.mylinkedin.service.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/registeredUser")
public class RegisteredUserRestController {

    @Autowired
    IUserService userService;

    @GetMapping(value = "/message/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MessageDTO getMessagetById(@PathVariable int id) throws MessageNotFoundException {

        Message message = userService.getMessageById(id);
        return new MessageDTO().convertToDto(message);
    }

    @PostMapping(value="/message/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MessageDTO saveMessage(@RequestBody @Valid MessageDTO messageDTO) throws ParseException, MessageSavingException {

        Message message = new Message().convertToEntity(messageDTO);
        Message messageSaved = userService.saveMessage(message);
        messageDTO.setId(messageSaved.getId());
        return messageDTO;
    }

    @GetMapping(value= "/message/getBySenderAndReceiver/{senderId}/{receiverId}", produces = MediaType.APPLICATION_JSON_VALUE)
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
