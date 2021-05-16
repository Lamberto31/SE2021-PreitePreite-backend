package it.unisalento.mylinkedin.entities;

import javax.persistence.*;
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

    @ManyToOne
    User user;
    @ManyToOne
    Post post;
    @ManyToOne
    Comment answeredComment;
    @OneToMany
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
}
