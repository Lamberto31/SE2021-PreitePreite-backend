package it.unisalento.mylinkedin.iservice;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dao.*;
import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.InvalidValueException;
import it.unisalento.mylinkedin.exception.post.PostNotFoundException;
import it.unisalento.mylinkedin.exception.post.PostSavingException;
import it.unisalento.mylinkedin.exception.post.StructureNotFoundException;
import it.unisalento.mylinkedin.exception.post.StructureSavingException;
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
}
