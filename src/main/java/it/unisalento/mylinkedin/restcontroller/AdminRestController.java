package it.unisalento.mylinkedin.restcontroller;

import it.unisalento.mylinkedin.dto.ApplicantDTO;
import it.unisalento.mylinkedin.dto.OfferorDTO;
import it.unisalento.mylinkedin.dto.UserDTO;
import it.unisalento.mylinkedin.entities.Applicant;
import it.unisalento.mylinkedin.entities.Offeror;
import it.unisalento.mylinkedin.entities.User;
import it.unisalento.mylinkedin.exception.InvalidValueException;
import it.unisalento.mylinkedin.exception.user.UserNotFoundException;
import it.unisalento.mylinkedin.service.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/admin")
public class AdminRestController {

    @Autowired
    IUserService userService;

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable("id") int id) throws UserNotFoundException {
        User user = userService.getById(id);
        UserDTO userDTO = new UserDTO().convertToDto(user);
        userService.delete(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping(value= "/geApplicantyStatus/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ApplicantDTO> getApplicantByStatus(@PathVariable("status") String status) throws UserNotFoundException {
        List<Applicant> applicantList = userService.getApplicantByStatus(status);
        List<ApplicantDTO> applicantDTOList = new ArrayList<>();
        for(Applicant applicant: applicantList) {
            applicantDTOList.add(new ApplicantDTO().convertToDto(applicant));
        }
        return applicantDTOList;
    }

    @GetMapping(value= "/geOfferoryStatus/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OfferorDTO> getOfferorByStatus(@PathVariable("status") String status) throws UserNotFoundException {
        List<Offeror> offerorList = userService.getOfferorByStatus(status);
        List<OfferorDTO> offerorDTOList = new ArrayList<>();
        for(Offeror offeror: offerorList) {
            offerorDTOList.add(new OfferorDTO().convertToDto(offeror));
        }
        return offerorDTOList;
    }

    @PostMapping(value = "/updateStatusRegistration/{id}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateStatusRegistration(@PathVariable("id") int id, @PathVariable("status") String status) throws UserNotFoundException, InvalidValueException {
        userService.updateStatusRegistration(status, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
