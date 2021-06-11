package it.unisalento.mylinkedin.iservice;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dao.*;
import it.unisalento.mylinkedin.entities.Company;
import it.unisalento.mylinkedin.entities.Post;
import it.unisalento.mylinkedin.entities.User;
import it.unisalento.mylinkedin.exception.post.PostNotFoundException;
import it.unisalento.mylinkedin.exception.post.PostSavingException;
import it.unisalento.mylinkedin.exception.user.CompanyNotFoundException;
import it.unisalento.mylinkedin.exception.user.CompanySavingException;
import it.unisalento.mylinkedin.service.iservice.IPostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;
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
}
