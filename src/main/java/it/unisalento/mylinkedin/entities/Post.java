package it.unisalento.mylinkedin.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Post {

    public Post() {}

    public Post(int id, Date pubblicationDate, boolean isHidden, boolean isPrivate, String data, User user, List<UserInterestedPost> userInterestedPostList, List<Comment> commentList, Structure structure) {
        this.id = id;
        this.pubblicationDate = pubblicationDate;
        this.isHidden = isHidden;
        this.isPrivate = isPrivate;
        this.data = data;
        this.user = user;
        this.userInterestedPostList = userInterestedPostList;
        this.commentList = commentList;
        this.structure = structure;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    Date pubblicationDate;
    @Column(nullable = false)
    boolean isHidden;
    @Column(nullable = false)
    boolean isPrivate;
    @Column(length = 1000, nullable = false)
    String data;

    @ManyToOne
    User user;
    @OneToMany //TODO: mappedby e altri
    List<UserInterestedPost> userInterestedPostList;
    @OneToMany //TODO: mappedby e altri
    List<Comment> commentList;
    @ManyToOne(optional = false)
    Structure structure;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPubblicationDate() {
        return pubblicationDate;
    }

    public void setPubblicationDate(Date pubblicationDate) {
        this.pubblicationDate = pubblicationDate;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserInterestedPost> getUserInterestedPostList() {
        return userInterestedPostList;
    }

    public void setUserInterestedPostList(List<UserInterestedPost> userInterestedPostList) {
        this.userInterestedPostList = userInterestedPostList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }
}
