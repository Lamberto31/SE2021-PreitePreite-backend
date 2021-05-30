package it.unisalento.mylinkedin.restcontroller;

import it.unisalento.mylinkedin.dto.*;
import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.InvalidValueException;
import it.unisalento.mylinkedin.exception.post.PostNotFoundException;
import it.unisalento.mylinkedin.exception.post.PostSavingException;
import it.unisalento.mylinkedin.exception.post.StructureNotFoundException;
import it.unisalento.mylinkedin.exception.post.StructureSavingException;
import it.unisalento.mylinkedin.exception.user.MessageNotFoundException;
import it.unisalento.mylinkedin.exception.user.UserNotFoundException;
import it.unisalento.mylinkedin.service.iservice.IPostService;
import it.unisalento.mylinkedin.service.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/admin")
public class AdminRestController {

    @Autowired
    IUserService userService;

    @Autowired
    IPostService postService;

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

    @PostMapping(value = "/post/updateIsHidden/{id}/{isHidden}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDTO> updatePostIsHidden(@PathVariable("id") int id, @PathVariable("isHidden") boolean isHidden) throws PostNotFoundException, InvalidValueException {
        postService.updateIsHidden(isHidden, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/structure/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StructureDTO> getALlStructure() {
        List<Structure> structureList = postService.getAllStructure();
        List<StructureDTO> structureDTOList = new ArrayList<>();
        for(Structure structure: structureList) {
            structureDTOList.add(new StructureDTO().convertToDto(structure));
        }
        return structureDTOList;
    }

    @PostMapping(value="/structure/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StructureDTO saveStructure(@RequestBody @Valid StructureDTO structureDTO) throws StructureSavingException {
        Structure structure = new Structure().convertToEntity(structureDTO);
        Structure structureSaved = postService.saveStructure(structure);
        structureDTO.setId(structureSaved.getId());
        return structureDTO;
    }

    @GetMapping(value = "/structure/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StructureDTO getStructureById(@PathVariable int id) throws StructureNotFoundException {
        Structure structure = postService.getStructureById(id);
        return new StructureDTO().convertToDto(structure);
    }

    @DeleteMapping(value = "/structure/delete/{id}")
    public ResponseEntity<StructureDTO> deleteStructure(@PathVariable("id") int id) throws StructureNotFoundException {
        Structure structure = postService.getStructureById(id);
        StructureDTO structureDTO = new StructureDTO().convertToDto(structure);
        postService.deleteStructure(structure);
        return new ResponseEntity<>(structureDTO, HttpStatus.OK);
    }


}
