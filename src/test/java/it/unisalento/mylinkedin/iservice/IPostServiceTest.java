package it.unisalento.mylinkedin.iservice;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dao.*;
import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.InvalidValueException;
import it.unisalento.mylinkedin.exception.post.*;
import it.unisalento.mylinkedin.exception.user.UserNotFoundException;
import it.unisalento.mylinkedin.service.iservice.IPostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IPostServiceTest {

    @Autowired
    IPostService postService;

    @MockBean
    PostRepository postRepository;

    @MockBean
    StructureRepository structureRepository;

    @MockBean
    AttributeRepository attributeRepository;

    @MockBean
    CommentRepository commentRepository;

    @MockBean
    StructureAttributeRepository structureAttributeRepository;

    @MockBean
    UserInterestedPostRepository userInterestedPostRepository;


    private Post post;
    private Post wrongPost;
    private int correctId;
    private List<Post> postList;
    private boolean postNotFoundIsPrivate;

    private Structure structure;
    private Structure wrongStructure;
    private List<Structure> structureList;
    private String structureNotFoundUserCanPublish;
    private String wrongStructureUserCanPublish;

    private Attribute attribute;
    private Attribute wrongAttribute;

    private Comment comment;
    private Comment wrongComment;
    private List<Comment> commentList;

    private StructureAttribute structureAttribute;
    private StructureAttribute wrongStructureAttribute;

    private UserInterestedPost userInterestedPost;
    private UserInterestedPost wrongUserInterestedPost;
    private User user;

    void init() throws ParseException {

        //Post
        this.post = new Post();
        this.post.setPubblicationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.post.setHidden(true);
        this.post.setPrivate(true);
        this.post.setData("testData");

        when(postRepository.save(refEq(post))).thenReturn(post);

        this.wrongPost = new Post();

        when(postRepository.save(refEq(wrongPost))).thenThrow(IllegalArgumentException.class);

        this.correctId = 1;
        when(postRepository.findById(correctId)).thenReturn(java.util.Optional.ofNullable(post));

        doThrow(new IllegalArgumentException()).when(postRepository).delete(wrongPost);

        this.postList = new ArrayList<>();
        postList.add(post);

        when(postRepository.findByIsPrivate(post.isPrivate())).thenReturn(postList);

        //Structure
        this.structure = new Structure();
        this.structure.setTitle("testTitle");
        this.structure.setDescription("testDescription");
        this.structure.setUserCanPublish(Constants.CAN_PUBLISH_BOTH);

        when(structureRepository.save(refEq(structure))).thenReturn(structure);

        this.wrongStructure = new Structure();

        when(structureRepository.save(refEq(wrongStructure))).thenThrow(IllegalArgumentException.class);

        when(structureRepository.findById(correctId)).thenReturn(java.util.Optional.ofNullable(structure));

        doThrow(new IllegalArgumentException()).when(structureRepository).delete(wrongStructure);

        this.structureList = new ArrayList<>();
        structureList.add(structure);

        when(structureRepository.findByUserCanPublish(structure.getUserCanPublish())).thenReturn(structureList);

        this.structureNotFoundUserCanPublish = Constants.CAN_PUBLISH_OFFEROR;

        this.wrongStructureUserCanPublish = "wrong";

        //Attribute
        this.attribute = new Attribute();
        this.attribute.setTitle("testTitle");
        this.attribute.setType("testType");

        when(attributeRepository.save(refEq(attribute))).thenReturn(attribute);

        this.wrongAttribute = new Attribute();

        when(attributeRepository.save(refEq(wrongAttribute))).thenThrow(IllegalArgumentException.class);

        when(attributeRepository.findById(correctId)).thenReturn(java.util.Optional.ofNullable(attribute));

        doThrow(new IllegalArgumentException()).when(attributeRepository).delete(wrongAttribute);

        //Comment
        this.comment = new Comment();
        this.comment.setText("testText");
        this.comment.setPubblicationDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));

        when(commentRepository.save(refEq(comment))).thenReturn(comment);

        this.wrongComment = new Comment();

        when(commentRepository.save(refEq(wrongComment))).thenThrow(IllegalArgumentException.class);

        when(commentRepository.findById(correctId)).thenReturn(java.util.Optional.ofNullable(comment));

        doThrow(new IllegalArgumentException()).when(commentRepository).delete(wrongComment);

        this.commentList = new ArrayList<>();
        commentList.add(comment);

        when(commentRepository.getByPost(post)).thenReturn(commentList);

        //StructureAttribute
        this.structureAttribute = new StructureAttribute();
        this.structureAttribute.setStructure(structure);
        this.structureAttribute.setAttribute(attribute);

        when(structureAttributeRepository.save(refEq(structureAttribute))).thenReturn(structureAttribute);

        this.wrongStructureAttribute = new StructureAttribute();

        when(structureAttributeRepository.save(refEq(wrongStructureAttribute))).thenThrow(IllegalArgumentException.class);

        when(structureAttributeRepository.findById(correctId)).thenReturn(java.util.Optional.ofNullable(structureAttribute));

        doThrow(new IllegalArgumentException()).when(structureAttributeRepository).delete(wrongStructureAttribute);

        //UserInterestedPost
        this.user = new User();
        this.user.setName("testName");
        this.user.setSurname("testSurname");
        this.user.setEmail("emailtest@test.com");
        this.user.setPassword("testPassword");
        this.user.setBirthDate(Constants.SIMPLE_DATE_FORMAT.parse("01/01/2000 00:00"));
        this.user.setDescription("testDescription");

        this.userInterestedPost = new UserInterestedPost();
        this.userInterestedPost.setUser(user);
        this.userInterestedPost.setPost(post);

        when(userInterestedPostRepository.save(refEq(userInterestedPost))).thenReturn(userInterestedPost);

        this.wrongUserInterestedPost = new UserInterestedPost();

        when(userInterestedPostRepository.save(refEq(wrongUserInterestedPost))).thenThrow(IllegalArgumentException.class);

        when(userInterestedPostRepository.findById(correctId)).thenReturn(java.util.Optional.ofNullable(userInterestedPost));

        doThrow(new IllegalArgumentException()).when(userInterestedPostRepository).delete(wrongUserInterestedPost);
    }

    //Post
    @Test
    void getAllTest() {
        assertThat(postService.getAll()).isNotNull();
    }

    @Test
    void saveTest() throws PostSavingException {
        Post postSaved = postService.save(post);
        assertThat(post.equals(postSaved));
    }

    @Test
    void saveThrowsExTest() {
        Exception exp = assertThrows(PostSavingException.class, () -> postService.save(wrongPost));
        assertThat(exp).isNotNull();
    }

    @Test
    void getByIdTest() throws PostNotFoundException {
        Post postFound = postService.getById(correctId);
        assertThat(post.equals(postFound));
    }

    @Test
    void getByIdThrowsExTest() {
        Exception exp = assertThrows(PostNotFoundException.class, () -> postService.getById(wrongPost.getId()));
        assertThat(exp).isNotNull();
    }

    @Test
    void deleteTest() throws PostNotFoundException {
        Post postDeleted = postService.delete(post);
        assertThat(post.equals(postDeleted));
    }

    @Test
    void deleteThrowsExTest() {
        Exception exp = assertThrows(PostNotFoundException.class, () -> postService.delete(wrongPost));
        assertThat(exp).isNotNull();
    }

    @Test
    void getByIsPrivateTest() throws PostNotFoundException{
        List<Post> postFoundList = postService.getByIsPrivate(post.isPrivate());
        assertThat(postList.equals(postFoundList));
    }

    @Test
    void getByIsPrivateThrowsExTest() {
        Exception exp = assertThrows(PostNotFoundException.class, () -> postService.getByIsPrivate(postNotFoundIsPrivate));
        assertThat(exp).isNotNull();
    }

    @Test
    void updateIsHiddenTest() {
        assertDoesNotThrow(() -> postService.updateIsHidden(post.isHidden(), correctId));
    }

    @Test
    void updateIsHiddenThrowsExTest() {
        Exception exp = assertThrows(PostNotFoundException.class, () -> postService.updateIsHidden(post.isHidden(), correctId));
        assertThat(exp).isNotNull();
    }

    //Structure
    @Test
    void getAllStructureTest() {
        assertThat(postService.getAllStructure()).isNotNull();
    }

    @Test
    void saveStructureTest() throws StructureSavingException {
        Structure structureSaved = postService.saveStructure(structure);
        assertThat(structure.equals(structureSaved));
    }

    @Test
    void saveStructureThrowsExTest() {
        Exception exp = assertThrows(StructureSavingException.class, () -> postService.saveStructure(wrongStructure));
        assertThat(exp).isNotNull();
    }

    @Test
    void getStructureByIdTest() throws StructureNotFoundException {
        Structure structureFound = postService.getStructureById(correctId);
        assertThat(structure.equals(structureFound));
    }

    @Test
    void getStructureByIdThrowsExTest() {
        Exception exp = assertThrows(StructureNotFoundException.class, () -> postService.getStructureById(wrongStructure.getId()));
        assertThat(exp).isNotNull();
    }

    @Test
    void deleteStructureTest() throws StructureNotFoundException {
        Structure structureDeleted = postService.deleteStructure(structure);
        assertThat(structure.equals(structureDeleted));
    }

    @Test
    void deleteStructureThrowsExTest() {
        Exception exp = assertThrows(PostNotFoundException.class, () -> postService.deleteStructure(wrongStructure));
        assertThat(exp).isNotNull();
    }

    @Test
    void getStructureByUserCanPublishTest() throws StructureNotFoundException {
        List<Structure> structureFoundList = postService.getStructureByUserCanPublish(structure.getUserCanPublish());
        assertThat(structureList.equals(structureFoundList));
    }

    @Test
    void getStructureByUserCanPublishThrowsStructureNotFoundExTest() {
        Exception exp = assertThrows(UserNotFoundException.class, () -> postService.getStructureByUserCanPublish(structureNotFoundUserCanPublish));
        assertThat(exp).isNotNull();
    }

    @Test
    void getStructureByUserCanPublishThrowsInvalidValueExTest() {
        Exception exp = assertThrows(InvalidValueException.class, () -> postService.getStructureByUserCanPublish(wrongStructureUserCanPublish));
        assertThat(exp).isNotNull();
    }

    //Attribute
    @Test
    void getAllAttributeTest() {
        assertThat(postService.getAllAttribute()).isNotNull();
    }

    @Test
    void saveAttributeTest() throws AttributeSavingException {
        Attribute attributeSaved = postService.saveAttribute(attribute);
        assertThat(attribute.equals(attributeSaved));
    }

    @Test
    void saveAttributeThrowsExTest() {
        Exception exp = assertThrows(AttributeSavingException.class, () -> postService.saveAttribute(wrongAttribute));
        assertThat(exp).isNotNull();
    }

    @Test
    void getAttributeByIdTest() throws AttributeNotFoundException {
        Attribute attributeFound = postService.getAttributeById(correctId);
        assertThat(attribute.equals(attributeFound));
    }

    @Test
    void getAttributeByIdThrowsExTest() {
        Exception exp = assertThrows(AttributeNotFoundException.class, () -> postService.getAttributeById(wrongAttribute.getId()));
        assertThat(exp).isNotNull();
    }

    @Test
    void deleteAttributeTest() throws AttributeNotFoundException {
        Attribute attributeDeleted = postService.deleteAttribute(attribute);
        assertThat(attribute.equals(attributeDeleted));
    }

    @Test
    void deleteAttributeThrowsExTest() {
        Exception exp = assertThrows(AttributeNotFoundException.class, () -> postService.deleteAttribute(wrongAttribute));
        assertThat(exp).isNotNull();
    }

    //Comment
    @Test
    void getAllCommentTest() {
        assertThat(postService.getAllComment()).isNotNull();
    }

    @Test
    void saveCommentTest() throws CommentSavingException {
        Comment commentSaved = postService.saveComment(comment);
        assertThat(comment.equals(commentSaved));
    }

    @Test
    void saveCommentThrowsExTest() {
        Exception exp = assertThrows(CommentSavingException.class, () -> postService.saveComment(wrongComment));
        assertThat(exp).isNotNull();
    }

    @Test
    void getCommentByIdTest() throws CommentNotFoundException {
        Comment commentFound = postService.getCommentById(correctId);
        assertThat(comment.equals(commentFound));
    }

    @Test
    void getCommentByIdThrowsExTest() {
        Exception exp = assertThrows(CommentNotFoundException.class, () -> postService.getCommentById(wrongComment.getId()));
        assertThat(exp).isNotNull();
    }

    @Test
    void deleteCommentTest() throws CommentNotFoundException {
        Comment commentDeleted = postService.deleteComment(comment);
        assertThat(comment.equals(commentDeleted));
    }

    @Test
    void deleteCommentThrowsExTest() {
        Exception exp = assertThrows(CommentNotFoundException.class, () -> postService.deleteComment(wrongComment));
        assertThat(exp).isNotNull();
    }

    @Test
    void getCommentByPostTest() throws CommentNotFoundException {
        List<Comment> commentFoundList = postService.getCommentByPost(post);
        assertThat(commentList.equals(commentFoundList));
    }

    @Test
    void getCommentByPostThrowsExTest() {
        Exception exp = assertThrows(CommentNotFoundException.class, () -> postService.getCommentByPost(wrongPost));
        assertThat(exp).isNotNull();
    }

    //StructureAttribute
    @Test
    void getAllStructureAttributeTest() {
        assertThat(postService.getAllStructureAttribute()).isNotNull();
    }

    @Test
    void saveStructureAttributeTest() throws StructureAttributeSavingException {
        StructureAttribute structureAttributeSaved = postService.saveStructureAttribute(structureAttribute);
        assertThat(structureAttribute.equals(structureAttributeSaved));
    }

    @Test
    void saveStructureAttributeThrowsExTest() {
        Exception exp = assertThrows(StructureAttributeSavingException.class, () -> postService.saveStructureAttribute(wrongStructureAttribute));
        assertThat(exp).isNotNull();
    }

    @Test
    void getStructureAttributeByIdTest() throws StructureAttributeNotFoundException {
        StructureAttribute structureAttributeFound = postService.getStructureAttributeById(correctId);
        assertThat(structureAttribute.equals(structureAttributeFound));
    }

    @Test
    void getStructureAttributeByIdThrowsExTest() {
        Exception exp = assertThrows(StructureAttributeNotFoundException.class, () -> postService.getStructureAttributeById(wrongStructureAttribute.getId()));
        assertThat(exp).isNotNull();
    }

    @Test
    void deleteStructureAttributeTest() throws StructureAttributeNotFoundException {
        StructureAttribute structureAttributeDeleted = postService.deleteStructureAttribute(structureAttribute);
        assertThat(structureAttribute.equals(structureAttributeDeleted));
    }

    @Test
    void deleteStructureAttributeThrowsExTest() {
        Exception exp = assertThrows(StructureAttributeNotFoundException.class, () -> postService.deleteStructureAttribute(wrongStructureAttribute));
        assertThat(exp).isNotNull();
    }

    //UserInterestedPost
    @Test
    void getAllUserInterestedPostTest() {
        assertThat(postService.getAllUserInterestedPost()).isNotNull();
    }

    @Test
    void saveUserInterestedPostTest() {
        UserInterestedPost userInterestedPostSaved = postService.saveUserInterestedPost(userInterestedPost);
        assertThat(userInterestedPost.equals(userInterestedPostSaved));
    }

    @Test
    void saveUserInterestedPostThrowsExTest() {
        Exception exp = assertThrows(UserInterestedPostSavingException.class, () -> postService.saveUserInterestedPost(wrongUserInterestedPost));
        assertThat(exp).isNotNull();
    }

    @Test
    void getUserInterestedPostByIdTest() throws UserInterestedPostNotFoundException {
        UserInterestedPost userInterestedPostFound = postService.getUserInterestedPostById(correctId);
        assertThat(userInterestedPost.equals(userInterestedPostFound));
    }

    @Test
    void getUserInterestedPostByIdThrowsExTest() {
        Exception exp = assertThrows(UserInterestedPostNotFoundException.class, () -> postService.getUserInterestedPostById(wrongUserInterestedPost.getId()));
        assertThat(exp).isNotNull();
    }

    @Test
    void deleteUserInterestedPostTest() throws UserInterestedPostNotFoundException {
        UserInterestedPost userInterestedPostDeleted = postService.deleteUserInterestedPost(userInterestedPost);
        assertThat(userInterestedPostDeleted.equals(userInterestedPostDeleted));
    }

    @Test
    void deleteUserInterestedPostThrowsExTest() {
        Exception exp = assertThrows(UserInterestedPostNotFoundException.class, () -> postService.deleteUserInterestedPost(wrongUserInterestedPost));
        assertThat(exp).isNotNull();
    }
}
