package it.unisalento.mylinkedin.restcontroller;

import it.unisalento.mylinkedin.dto.UserDTO;
import it.unisalento.mylinkedin.entities.User;
import it.unisalento.mylinkedin.exception.user.UserNotFoundException;
import it.unisalento.mylinkedin.exception.user.UserSavingException;
import it.unisalento.mylinkedin.service.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

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
}
