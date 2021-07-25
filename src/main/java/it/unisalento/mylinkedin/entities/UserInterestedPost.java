package it.unisalento.mylinkedin.entities;

import javax.persistence.*;

@Entity
public class UserInterestedPost {
    public UserInterestedPost() {}

    public UserInterestedPost(int id, boolean isNotified, User user, Post post) {
        this.id = id;
        this.isNotified = isNotified;
        this.user = user;
        this.post = post;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    boolean isNotified;

    @ManyToOne(optional = false)
    User user;
    @ManyToOne(optional = false)
    Post post;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isNotified() {
        return isNotified;
    }

    public void setNotified(boolean alreadyNotified) {
        this.isNotified = alreadyNotified;
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
}
