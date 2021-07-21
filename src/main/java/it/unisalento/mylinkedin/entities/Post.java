package it.unisalento.mylinkedin.entities;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dto.PostDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import javax.persistence.*;
import java.text.ParseException;
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

    @Column(nullable = false)
    String title;
    Date pubblicationDate;
    @Column(nullable = false)
    boolean isHidden;
    @Column(nullable = false)
    boolean isPrivate;
    @Column(length = 1000, nullable = false) //JSON
    String data;

    @ManyToOne(optional = false)
    User user;
    @OneToMany(mappedBy = "post", targetEntity = UserInterestedPost.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<UserInterestedPost> userInterestedPostList;
    @OneToMany(mappedBy = "post", targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Comment> commentList;
    @ManyToOne(optional = false)
    Structure structure;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Post convertToEntity(PostDTO dto) throws ParseException {
        ModelMapper modelMapper =  new ModelMapper();
        modelMapper.addMappings(new PropertyMap<PostDTO, Post>() {
            @Override
            protected void configure() {
                skip(destination.getPubblicationDate());
            }
        });

        Post entity = modelMapper.map(dto, Post.class);
        try {
            entity.setPubblicationDate(dto.getPubblicationDate(Constants.timezone));
        } catch (Exception e) {
            entity.setPubblicationDate(null);
        }
        return entity;
    }

    public static class Attributevalue {

        public Attributevalue() {}

        String title;
        String value;
        String type;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class Skill {

        public Skill() {}

        String identifier;
        String area;
        String level;

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }

}