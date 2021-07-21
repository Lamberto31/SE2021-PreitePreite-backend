package it.unisalento.mylinkedin.restcontroller;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.*;
import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.InvalidValueException;
import it.unisalento.mylinkedin.exception.post.*;
import it.unisalento.mylinkedin.exception.user.*;
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
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(Constants.URI_REGISTEREDUSER)
public class RegisteredUserRestController {

    @Autowired
    IUserService userService;

    @Autowired
    IPostService postService;

    @GetMapping(value = Constants.URI_LOGIN)
    public ResponseEntity<UserDTO> registeredUserLogin(@PathVariable("email") String email) throws UserNotFoundException {
        User user = userService.getByEmail(email);
        UserDTO userDTO = new UserDTO().convertToDto(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping(value=Constants.URI_MESSAGE+Constants.URI_SAVE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public MessageDTO saveMessage(@RequestBody @Valid MessageDTO messageDTO) throws ParseException, MessageSavingException {

        Message message = new Message().convertToEntity(messageDTO);
        Message messageSaved = userService.saveMessage(message);
        messageDTO.setId(messageSaved.getId());
        return messageDTO;
    }

    @GetMapping(value= Constants.URI_MESSAGE+Constants.URI_GETBYTWOUSER, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageDTO> getMessageBetweenTwoUser(@PathVariable("user1Id") int user1Id, @PathVariable("user2Id") int user2Id) throws MessageNotFoundException, UserNotFoundException {
        User user1 = userService.getById(user1Id);
        User user2 = userService.getById(user2Id);

        List<Message> messageList = userService.getMessageBetweenTwoUser(user1, user2);
        List<MessageDTO> messageDTOList = new ArrayList<>();
        for(Message message: messageList) {
            messageDTOList.add(new MessageDTO().convertToDto(message));
        }
        return messageDTOList;
    }

    @GetMapping(value= Constants.URI_MESSAGE+Constants.URI_GETBYSENDER, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageDTO> getMessageBySender(@PathVariable("senderId") int senderId) throws MessageNotFoundException, UserNotFoundException {
        User sender = userService.getById(senderId);

        List<Message> messageList = userService.getMessageBySender(sender);
        List<MessageDTO> messageDTOList = new ArrayList<>();
        for(Message message: messageList) {
            messageDTOList.add(new MessageDTO().convertToDto(message));
        }
        return messageDTOList;
    }

    @GetMapping(value= Constants.URI_MESSAGE+Constants.URI_GETBYRECEIVER, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageDTO> getMessageByReceiver(@PathVariable("receiverId") int receiverId) throws MessageNotFoundException, UserNotFoundException {
        User receiver = userService.getById(receiverId);

        List<Message> messageList = userService.getMessageByReceiver(receiver);
        List<MessageDTO> messageDTOList = new ArrayList<>();
        for(Message message: messageList) {
            messageDTOList.add(new MessageDTO().convertToDto(message));
        }
        return messageDTOList;
    }

    @GetMapping(value= Constants.URI_MESSAGE+Constants.URI_GETBYUSERSENTORRECEIVED, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageDTO> getMessageSentOrReceivedByUser(@PathVariable("userId") int userId) throws MessageNotFoundException, UserNotFoundException {
        User user = userService.getById(userId);

        List<Message> messageList = userService.getMessageSentOrReceivedByUser(user);
        List<MessageDTO> messageDTOList = new ArrayList<>();
        for(Message message: messageList) {
            messageDTOList.add(new MessageDTO().convertToDto(message));
        }
        return messageDTOList;
    }

    @GetMapping(value= Constants.URI_MESSAGE+Constants.URI_GETBYRECEIVERANDNOTREAD, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageDTO> getMessageByReceiverAndNotRead(@PathVariable("receiverId") int receiverId) throws MessageNotFoundException, UserNotFoundException {
        User receiver = userService.getById(receiverId);

        List<Message> messageList = userService.getMessageByReceiverAndNotRead(receiver);
        List<MessageDTO> messageDTOList = new ArrayList<>();
        for(Message message: messageList) {
            messageDTOList.add(new MessageDTO().convertToDto(message));
        }
        return messageDTOList;
    }

    @PutMapping(value = Constants.URI_MESSAGE+Constants.URI_UPDATEISREAD, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDTO> updateMessageIsRead(@PathVariable("messageId") int messageId, @PathVariable("isRead") boolean isRead) throws MessageNotFoundException {
        userService.updateMessageIsRead(isRead, messageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value= Constants.URI_MESSAGE+Constants.URI_GETBYSENDERANDRECEIVERANDNOTREAD, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageDTO> getMessageBySenderAndReceiverAndNotRead(@PathVariable("senderId") int senderId, @PathVariable("receiverId") int receiverId) throws MessageNotFoundException, UserNotFoundException {
        User sender = userService.getById(senderId);
        User receiver = userService.getById(receiverId);

        List<Message> messageList = userService.getMessageBySenderAndReceiverAndNotRead(sender, receiver);
        List<MessageDTO> messageDTOList = new ArrayList<>();
        for(Message message: messageList) {
            messageDTOList.add(new MessageDTO().convertToDto(message));
        }
        return messageDTOList;
    }

    @GetMapping(value = Constants.URI_POST+Constants.URI_GETALL, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostDTO> getAllPost() {
        List<Post> postList = postService.getAllOrderByPubblicationDateDesc();
        List<PostDTO> postDTOList = new ArrayList<>();
        for(Post post: postList) {
            postDTOList.add(new PostDTO().convertToDto(post));
        }
        return postDTOList;
    }

    @GetMapping(value = Constants.URI_POST+Constants.URI_GETSHOWN, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostDTO> getPostPublic() throws PostNotFoundException {

        List<Post> postList = postService.getByIsHidden(false);
        List<PostDTO> postDTOList = new ArrayList<>();
        for(Post post: postList) {
            postDTOList.add(new PostDTO().convertToDto(post));
        }
        return postDTOList;
    }

    @GetMapping(value = Constants.URI_POST+Constants.URI_GETBYID, produces = MediaType.APPLICATION_JSON_VALUE)
    public PostDTO getPostById(@PathVariable int id) throws PostNotFoundException {

        Post post = postService.getById(id);
        return new PostDTO().convertToDto(post);
    }

    @PostMapping(value=Constants.URI_POST+Constants.URI_SAVE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PostDTO savePost(@RequestBody @Valid PostDTO postDTO) throws ParseException, PostSavingException {

        Post post = new Post().convertToEntity(postDTO);
        Post postSaved = postService.save(post);
        postDTO.setId(postSaved.getId());
        return postDTO;
    }


    @GetMapping(value = Constants.URI_STRUCTURE+Constants.URI_GETCANPUBLISH, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StructureDTO> getStructureBothCanPublish() throws StructureNotFoundException, InvalidValueException {

        List<Structure> structureList = postService.getStructureByUserCanPublish(Constants.CAN_PUBLISH_BOTH);
        List<StructureDTO> structureDTOList = new ArrayList<>();
        for(Structure structure: structureList) {
            structureDTOList.add(new StructureDTO().convertToDto(structure));
        }
        return structureDTOList;
    }

    // TODO: Gestire con spring security per far usare solo ad offeror

    @GetMapping(value = Constants.URI_OFFEROR+Constants.URI_STRUCTURE+Constants.URI_GETCANPUBLISH, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StructureDTO> getStructureOfferorCanPublish() throws StructureNotFoundException, InvalidValueException {

        List<Structure> structureList = postService.getStructureByUserCanPublish(Constants.CAN_PUBLISH_OFFEROR);
        List<StructureDTO> structureDTOList = new ArrayList<>();
        for(Structure structure: structureList) {
            structureDTOList.add(new StructureDTO().convertToDto(structure));
        }
        return structureDTOList;
    }

    // TODO: Gestire con spring security per far usare solo ad applicant
    @GetMapping(value = Constants.URI_APPLICANT+Constants.URI_STRUCTURE+Constants.URI_GETCANPUBLISH, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StructureDTO> getStructureApplicantCanPublish() throws StructureNotFoundException, InvalidValueException {

        List<Structure> structureList = postService.getStructureByUserCanPublish(Constants.CAN_PUBLISH_APPLICANT);
        List<StructureDTO> structureDTOList = new ArrayList<>();
        for(Structure structure: structureList) {
            structureDTOList.add(new StructureDTO().convertToDto(structure));
        }
        return structureDTOList;
    }

    @PostMapping(value=Constants.URI_COMMENT+Constants.URI_SAVE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommentDTO saveComment(@RequestBody @Valid CommentDTO commentDTO) throws ParseException, CommentSavingException {
        Comment comment = new Comment().convertToEntity(commentDTO);
        Comment commentSaved = postService.saveComment(comment);
        commentDTO.setId(commentSaved.getId());
        return commentDTO;
    }

    @GetMapping(value= Constants.URI_COMMENT+Constants.URI_GETBYPOST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommentDTO> getCommentByPost(@PathVariable("postId") int postId) throws PostNotFoundException, CommentNotFoundException {
        Post post = postService.getById(postId);

        List<Comment> commentList = postService.getCommentByPost(post);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for(Comment comment: commentList) {
            commentDTOList.add(new CommentDTO().convertToDto(comment));
        }
        return commentDTOList;
    }

    @GetMapping(value= Constants.URI_ATTRIBUTE+Constants.URI_GETBYSTRUCTURE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AttributeDTO> getAttributeByStructure(@PathVariable("structureId") int structureId) throws StructureNotFoundException, AttributeNotFoundException {
        Structure structure = postService.getStructureById(structureId);

        List<Attribute> attributeList = postService.getAttributeByStructure(structure);
        List<AttributeDTO> attributeDTOList = new ArrayList<>();
        for(Attribute attribute: attributeList) {
            attributeDTOList.add(new AttributeDTO().convertToDto(attribute));
        }
        return attributeDTOList;
    }

    @GetMapping(value = Constants.URI_GETBYID, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUserById(@PathVariable int id) throws UserNotFoundException {

        User user = userService.getById(id);
        return new UserDTO().convertToDto(user);
    }

    @PostMapping(value=Constants.URI_NOTIFICATIONTOKEN+Constants.URI_SAVE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public NotificationTokenDTO saveNotificationToken(@RequestBody @Valid NotificationTokenDTO notificationTokenDTO) throws NotificationTokenSavingException {
        NotificationToken notificationToken = new NotificationToken().convertToEntity(notificationTokenDTO);
        NotificationToken notificationTokenSaved = userService.saveNotificationToken(notificationToken);
        notificationTokenDTO.setId(notificationTokenSaved.getId());
        return notificationTokenDTO;
    }

    @GetMapping(value = Constants.URI_NOTIFICATIONTOKEN+Constants.URI_GETBYID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NotificationTokenDTO getNotificationTokenById(@PathVariable int id) throws NotificationTokenNotFoundException {
        NotificationToken notificationToken = userService.getNotificationTokenById(id);
        return new NotificationTokenDTO().convertToDto(notificationToken);
    }

    @PostMapping(value=Constants.URI_POST+Constants.URI_SAVE +Constants.URI_USERINTERESTEDPOSTID, produces = MediaType.APPLICATION_JSON_VALUE)
    public PostDTO saveUserInterestedPost(@PathVariable("userId") int userId, @PathVariable("postId") int postId) throws UserNotFoundException, PostNotFoundException, UserInterestedPostSavingException {

        UserInterestedPost userInterestedPost= new UserInterestedPost();
        User user = userService.getById(userId);
        Post post = postService.getById(postId);

        userInterestedPost.setUser(user);
        userInterestedPost.setPost(post);

        UserInterestedPost userInterestedPostSaved = postService.saveUserInterestedPost(userInterestedPost);

        return new PostDTO().convertToDto(post);
    }

    @GetMapping(value = Constants.URI_POST+Constants.URI_GETFILTEREDJOBOFFER, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostDTO> getFilteredJobOffer(@PathVariable("offerorId") int offerorId, @PathVariable("firstDate") String firstDate, @PathVariable("lastDate") String lastDate, @PathVariable("skillIdentifier") String skillIdentifier) throws UserNotFoundException, PostNotFoundException, ParseException {
        User offeror = null;
        if (offerorId != 0) {
            offeror = userService.getById(offerorId);
        }

        Date dateFirstDate = null;
        if (!firstDate.equals("null")) {
            dateFirstDate = Constants.SIMPLE_DATE_FORMAT_ONLYDATE.parse(firstDate);
        }

        Date dateLastDate = null;
        if (!lastDate.equals("null")) {
            dateLastDate = Constants.SIMPLE_DATE_FORMAT_ONLYDATE.parse(lastDate);
        }
        if (skillIdentifier.equals("null")) {
            skillIdentifier = null;
        }

        List<Post> postList = postService.getJobOfferByOfferorAndByPubblicationDateBetweenAndSkill(offeror, dateFirstDate, dateLastDate, skillIdentifier);
        List<PostDTO> postDTOList = new ArrayList<>();
        for(Post post: postList) {
            postDTOList.add(new PostDTO().convertToDto(post));
        }
        return postDTOList;
    }
}
