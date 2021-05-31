package it.unisalento.mylinkedin.restcontroller;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.*;
import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.post.CommentSavingException;
import it.unisalento.mylinkedin.exception.post.PostNotFoundException;
import it.unisalento.mylinkedin.exception.post.PostSavingException;
import it.unisalento.mylinkedin.exception.post.StructureNotFoundException;
import it.unisalento.mylinkedin.exception.user.MessageNotFoundException;
import it.unisalento.mylinkedin.exception.user.MessageSavingException;
import it.unisalento.mylinkedin.exception.user.UserNotFoundException;
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
@RequestMapping("/registeredUser")
public class RegisteredUserRestController {

    @Autowired
    IUserService userService;

    @Autowired
    IPostService postService;

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

    @GetMapping(value = "/post/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostDTO> getAllPost() {
        List<Post> postList = postService.getAll();
        List<PostDTO> postDTOList = new ArrayList<>();
        for(Post post: postList) {
            postDTOList.add(new PostDTO().convertToDto(post));
        }
        return postDTOList;
    }

    @GetMapping(value = "/post/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PostDTO getPostById(@PathVariable int id) throws PostNotFoundException {

        Post post = postService.getById(id);
        return new PostDTO().convertToDto(post);
    }

    @PostMapping(value="/post/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PostDTO savePost(@RequestBody @Valid PostDTO postDTO) throws ParseException, PostSavingException {

        Post post = new Post().convertToEntity(postDTO);
        Post postSaved = postService.save(post);
        postDTO.setId(postSaved.getId());
        return postDTO;
    }

    @GetMapping(value = "/structure/getBothCanPublish", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StructureDTO> getStructureBothCanPublish() throws StructureNotFoundException {

        List<Structure> structureList = postService.getStructureByUserCanPublish(Constants.CAN_PUBLISH_BOTH);
        List<StructureDTO> structureDTOList = new ArrayList<>();
        for(Structure structure: structureList) {
            structureDTOList.add(new StructureDTO().convertToDto(structure));
        }
        return structureDTOList;
    }

    // TODO: Gestire con spring security per far usare solo ad offeror
    @GetMapping(value = "/offeror/structure/getOfferorCanPublish", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StructureDTO> getStructureOfferorCanPublish() throws StructureNotFoundException {

        List<Structure> structureList = postService.getStructureByUserCanPublish(Constants.CAN_PUBLISH_OFFEROR);
        List<StructureDTO> structureDTOList = new ArrayList<>();
        for(Structure structure: structureList) {
            structureDTOList.add(new StructureDTO().convertToDto(structure));
        }
        return structureDTOList;
    }

    // TODO: Gestire con spring security per far usare solo ad applicant
    @GetMapping(value = "/applicant/structure/getApplicantCanPublish", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StructureDTO> getStructureApplicantCanPublish() throws StructureNotFoundException {

        List<Structure> structureList = postService.getStructureByUserCanPublish(Constants.CAN_PUBLISH_APPLICANT);
        List<StructureDTO> structureDTOList = new ArrayList<>();
        for(Structure structure: structureList) {
            structureDTOList.add(new StructureDTO().convertToDto(structure));
        }
        return structureDTOList;
    }

    @PostMapping(value="/comment/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommentDTO savePost(@RequestBody @Valid CommentDTO commentDTO) throws ParseException, CommentSavingException {

        Comment comment = new Comment().convertToEntity(commentDTO);
        Comment commentSaved = postService.saveComment(comment);
        commentDTO.setId(commentSaved.getId());
        return commentDTO;
    }

    @GetMapping(value= "/comment/getByPost/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommentDTO> getMessageBySenderAndReceiver(@PathVariable("postId") int postId) throws PostNotFoundException {
        Post post = postService.getById(postId);

        List<Comment> commentList = postService.getCommentByPost(post);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for(Comment comment: commentList) {
            commentDTOList.add(new CommentDTO().convertToDto(comment));
        }
        return commentDTOList;
    }
}
