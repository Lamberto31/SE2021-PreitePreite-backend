package it.unisalento.mylinkedin.entities;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class User {

    public User() {}

    public User(int id, String name, String surname, String email, String password, Date birthDate, String description, List<ProfileImage> profileImage, List<Message> sentMessageList, List<Message> receivedMessageList, List<Comment> commentList, List<UserInterestedPost> userInterestedPostList, List<Post> postList, List<NotificationToken> notificationTokenList) {
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
        this.notificationTokenList = notificationTokenList;
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
    @Column(nullable = false)
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
    @OneToMany(mappedBy = "user", targetEntity = NotificationToken.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<NotificationToken> notificationTokenList;

    @Transient
    public String getType() {
        try {
            return this.getClass().getAnnotation(DiscriminatorValue.class).value();
        } catch (Exception e) {
            return null;
        }
    }

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

    public List<NotificationToken> getNotificationTokenList() {
        return notificationTokenList;
    }

    public void setNotificationTokenList(List<NotificationToken> notificationTokenList) {
        this.notificationTokenList = notificationTokenList;
    }

    public User convertToEntity(UserDTO dto) throws ParseException {
        ModelMapper modelMapper =  new ModelMapper();
        modelMapper.addMappings(new PropertyMap<UserDTO, User>() {
            @Override
            protected void configure() {
                skip(destination.getBirthDate());
            }
        });

        User entity = modelMapper.map(dto, User.class);
        try {
            entity.setBirthDate(dto.getBirthDate(Constants.timezone));
        } catch (Exception e) {
            entity.setBirthDate(null);
        }
        return entity;
    }
}
