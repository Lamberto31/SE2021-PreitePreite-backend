package it.unisalento.mylinkedin.entities;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.CommentDTO;
import it.unisalento.mylinkedin.dto.PostDTO;
import it.unisalento.mylinkedin.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Entity
public class Comment {

    public Comment() {}

    public Comment(int id, String text, Date pubblicationDate, User user, Post post, Comment answeredComment, List<Comment> answerCommentList) {
        this.id = id;
        this.text = text;
        this.pubblicationDate = pubblicationDate;
        this.user = user;
        this.post = post;
        this.answeredComment = answeredComment;
        this.answerCommentList = answerCommentList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String text;
    Date pubblicationDate;

    @ManyToOne(optional = false)
    User user;
    @ManyToOne(optional = false)
    Post post;
    @ManyToOne
    Comment answeredComment;
    @OneToMany(mappedBy = "answeredComment", targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Comment> answerCommentList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPubblicationDate() {
        return pubblicationDate;
    }

    public void setPubblicationDate(Date pubblicationDate) {
        this.pubblicationDate = pubblicationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getAnsweredComment() {
        return answeredComment;
    }

    public void setAnsweredComment(Comment answeredComment) {
        this.answeredComment = answeredComment;
    }

    public List<Comment> getAnswerCommentList() {
        return answerCommentList;
    }

    public void setAnswerCommentList(List<Comment> answerCommentList) {
        this.answerCommentList = answerCommentList;
    }

    public Comment convertToEntity(CommentDTO dto) throws ParseException {
        ModelMapper modelMapper =  new ModelMapper();
        modelMapper.addMappings(new PropertyMap<CommentDTO, Comment>() {
            @Override
            protected void configure() {
                skip(destination.getPubblicationDate());
            }
        });

        Comment entity = modelMapper.map(dto, Comment.class);
        try {
            entity.setPubblicationDate(dto.getPubblicationDate(Constants.timezone));
        } catch (Exception e) {
            entity.setPubblicationDate(null);
        }
        return entity;
    }
}
