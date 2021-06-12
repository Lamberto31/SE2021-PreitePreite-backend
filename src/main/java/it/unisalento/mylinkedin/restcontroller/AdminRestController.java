package it.unisalento.mylinkedin.restcontroller;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.*;
import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.InvalidValueException;
import it.unisalento.mylinkedin.exception.post.*;
import it.unisalento.mylinkedin.exception.user.UserNotFoundException;
import it.unisalento.mylinkedin.service.iservice.IPostService;
import it.unisalento.mylinkedin.service.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value= Constants.URI_ADMIN)
public class AdminRestController {

    @Autowired
    IUserService userService;

    @Autowired
    IPostService postService;

    @DeleteMapping(value = Constants.URI_DELETE)
    public ResponseEntity<UserDTO> delete(@PathVariable("id") int id) throws UserNotFoundException {
        User user = userService.getById(id);
        UserDTO userDTO = new UserDTO().convertToDto(user);
        userService.delete(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping(value= Constants.URI_APPLICANT+Constants.URI_GETBYSTATUS, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ApplicantDTO> getApplicantByStatus(@PathVariable("status") String status) throws UserNotFoundException {
        List<Applicant> applicantList = userService.getApplicantByStatus(status);
        List<ApplicantDTO> applicantDTOList = new ArrayList<>();
        for(Applicant applicant: applicantList) {
            applicantDTOList.add(new ApplicantDTO().convertToDto(applicant));
        }
        return applicantDTOList;
    }

    @GetMapping(value= Constants.URI_OFFEROR+Constants.URI_GETBYSTATUS, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OfferorDTO> getOfferorByStatus(@PathVariable("status") String status) throws UserNotFoundException {
        List<Offeror> offerorList = userService.getOfferorByStatus(status);
        List<OfferorDTO> offerorDTOList = new ArrayList<>();
        for(Offeror offeror: offerorList) {
            offerorDTOList.add(new OfferorDTO().convertToDto(offeror));
        }
        return offerorDTOList;
    }

    @PutMapping(value = Constants.URI_APPLICANT + Constants.URI_UPDATESTATUSREGISTRATION, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApplicantDTO> updateApplicantStatusRegistration(@PathVariable("id") int id, @PathVariable("status") String status) throws UserNotFoundException, InvalidValueException {
        userService.updateApplicantStatusRegistration(status, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = Constants.URI_OFFEROR + Constants.URI_UPDATESTATUSREGISTRATION, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferorDTO> updateOfferorStatusRegistration(@PathVariable("id") int id, @PathVariable("status") String status) throws UserNotFoundException, InvalidValueException {
        userService.updateOfferorStatusRegistration(status, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = Constants.URI_POST+Constants.URI_UPDATEISHIDDEN, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDTO> updatePostIsHidden(@PathVariable("id") int id, @PathVariable("isHidden") boolean isHidden) throws PostNotFoundException, InvalidValueException {
        postService.updateIsHidden(isHidden, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = Constants.URI_STRUCTURE+Constants.URI_GETALL, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StructureDTO> getAllStructure() {
        List<Structure> structureList = postService.getAllStructure();
        List<StructureDTO> structureDTOList = new ArrayList<>();
        for(Structure structure: structureList) {
            structureDTOList.add(new StructureDTO().convertToDto(structure));
        }
        return structureDTOList;
    }

    @PostMapping(value=Constants.URI_STRUCTURE+Constants.URI_SAVE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StructureDTO saveStructure(@RequestBody @Valid StructureDTO structureDTO) throws StructureSavingException {
        Structure structure = new Structure().convertToEntity(structureDTO);
        Structure structureSaved = postService.saveStructure(structure);
        structureDTO.setId(structureSaved.getId());
        return structureDTO;
    }

    @GetMapping(value = Constants.URI_STRUCTURE+Constants.URI_GETBYID, produces = MediaType.APPLICATION_JSON_VALUE)
    public StructureDTO getStructureById(@PathVariable int id) throws StructureNotFoundException {
        Structure structure = postService.getStructureById(id);
        return new StructureDTO().convertToDto(structure);
    }

    @DeleteMapping(value = Constants.URI_STRUCTURE+Constants.URI_DELETE)
    public ResponseEntity<StructureDTO> deleteStructure(@PathVariable("id") int id) throws StructureNotFoundException {
        Structure structure = postService.getStructureById(id);
        StructureDTO structureDTO = new StructureDTO().convertToDto(structure);
        postService.deleteStructure(structure);
        return new ResponseEntity<>(structureDTO, HttpStatus.OK);
    }

    @GetMapping(value = Constants.URI_ATTRIBUTE+Constants.URI_GETALL, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AttributeDTO> getALlAttribute() {
        List<Attribute> attributeList = postService.getAllAttribute();
        List<AttributeDTO> attributeDTOList = new ArrayList<>();
        for(Attribute attribute: attributeList) {
            attributeDTOList.add(new AttributeDTO().convertToDto(attribute));
        }
        return attributeDTOList;
    }

    @PostMapping(value=Constants.URI_ATTRIBUTE+Constants.URI_SAVE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public AttributeDTO saveAttribute(@RequestBody @Valid AttributeDTO attributeDTO) throws AttributeSavingException {
        Attribute attribute = new Attribute().convertToEntity(attributeDTO);
        Attribute attributeSaved = postService.saveAttribute(attribute);
        attributeDTO.setId(attributeSaved.getId());
        return attributeDTO;
    }

    @DeleteMapping(value = Constants.URI_ATTRIBUTE+Constants.URI_DELETE)
    public ResponseEntity<AttributeDTO> deleteAttribute(@PathVariable("id") int id) throws AttributeNotFoundException {
        Attribute attribute = postService.getAttributeById(id);
        AttributeDTO attributeDTO = new AttributeDTO().convertToDto(attribute);
        postService.deleteAttribute(attribute);
        return new ResponseEntity<>(attributeDTO, HttpStatus.OK);
    }


}
