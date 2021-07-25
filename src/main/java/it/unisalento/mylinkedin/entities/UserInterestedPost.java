package it.unisalento.mylinkedin.entities;

import javax.persistence.*;

@Entity
public class UserInterestedPost {
    public UserInterestedPost() {}

    public UserInterestedPost(int id, boolean alreadyNotified, User user, Post post) {
        this.id = id;
        this.alreadyNotified = alreadyNotified;
        this.user = user;
        this.post = post;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    boolean alreadyNotified;

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

    public boolean isAlreadyNotified() {
        return alreadyNotified;
    }

    public void setAlreadyNotified(boolean alreadyNotified) {
        this.alreadyNotified = alreadyNotified;
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
