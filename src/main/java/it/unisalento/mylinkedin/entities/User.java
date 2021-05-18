package it.unisalento.mylinkedin.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class User {

    //Variabili statiche per tipo utente
    public final static String TYPE_ADMIN = "admin";
    public final static String TYPE_OFFEROR = "offeror";
    public final static String TYPE_APPLICANT = "applicant";

    //Variabili statiche per stato registrazione
    public final static String REGISTRATION_PENDING = "pending";
    public final static String REGISTRATION_ACCEPTED = "accepted";
    public final static String REGISTRATION_BLOCKED = "blocked";

    public User() {}

    public User(int id, String name, String surname, String email, String password, Date birthDate, String description, List<ProfileImage> profileImage, List<Message> sentMessageList, List<Message> receivedMessageList, List<Comment> commentList, List<UserInterestedPost> userInterestedPostList, List<Post> postList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.description = description;
        this.profileImage = profileImage;
        this.sentMessageList = sentMessageList;
        this.receivedMessageList = receivedMessageList;
        this.commentList = commentList;
        this.userInterestedPostList = userInterestedPostList;
        this.postList = postList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String surname;
    @Column(unique = true, nullable = false)
    String email;
    @Column(nullable = false)
    String password;
    Date birthDate;
    @Column(length = 500)
    String description;

    @OneToMany(mappedBy = "user", targetEntity = ProfileImage.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<ProfileImage> profileImage;
    @OneToMany(mappedBy = "sender", targetEntity = Message.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Message> sentMessageList;
    @OneToMany(mappedBy = "receiver", targetEntity = Message.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Message> receivedMessageList;
    @OneToMany(mappedBy = "user", targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Comment> commentList;
    @OneToMany(mappedBy = "user", targetEntity = UserInterestedPost.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<UserInterestedPost> userInterestedPostList;
    @OneToMany(mappedBy = "user", targetEntity = Post.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Post> postList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProfileImage> getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(List<ProfileImage> profileImage) {
        this.profileImage = profileImage;
    }

    public List<Message> getSentMessageList() {
        return sentMessageList;
    }

    public void setSentMessageList(List<Message> sentMessageList) {
        this.sentMessageList = sentMessageList;
    }

    public List<Message> getReceivedMessageList() {
        return receivedMessageList;
    }

    public void setReceivedMessageList(List<Message> receivedMessageList) {
        this.receivedMessageList = receivedMessageList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<UserInterestedPost> getUserInterestedPostList() {
        return userInterestedPostList;
    }

    public void setUserInterestedPostList(List<UserInterestedPost> userInterestedPostList) {
        this.userInterestedPostList = userInterestedPostList;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
