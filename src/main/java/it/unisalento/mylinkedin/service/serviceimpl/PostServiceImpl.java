package it.unisalento.mylinkedin.service.serviceimpl;

import it.unisalento.mylinkedin.dao.PostRepository;
import it.unisalento.mylinkedin.entities.Attribute;
import it.unisalento.mylinkedin.entities.Comment;
import it.unisalento.mylinkedin.entities.Post;
import it.unisalento.mylinkedin.entities.Structure;
import it.unisalento.mylinkedin.service.iservice.IPostService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
//TODO: Definire tutti i metodi e mettere Transactional Annotation
public class PostServiceImpl implements IPostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> getAll() {
        return null;
    }

    @Override
    public Post save(Post post) {
        return null;
    }

    @Override
    public Post getById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Post> getByIsPrivate(boolean isPrivate) {
        return null;
    }

    @Override
    public void updateIsHidden(boolean isHidden, int id) {

    }

    @Override
    public List<Structure> getAllStructure() {
        return null;
    }

    @Override
    public Structure saveStructure(Structure structure) {
        return null;
    }

    @Override
    public Structure getStructureById(int id) {
        return null;
    }

    @Override
    public void deleteStructure(int id) {

    }

    @Override
    public List<Structure> getByUserCanPublish(String userCanPublish) {
        return null;
    }

    @Override
    public List<Attribute> getAllAttribute() {
        return null;
    }

    @Override
    public Attribute saveAttribute(Attribute attribute) {
        return null;
    }

    @Override
    public Attribute getAttributeById(int id) {
        return null;
    }

    @Override
    public void deleteAttribute(int id) {

    }

    @Override
    public List<Comment> getAllComment() {
        return null;
    }

    @Override
    public Comment saveComment(Comment comment) {
        return null;
    }

    @Override
    public Comment getCommentById(int id) {
        return null;
    }

    @Override
    public void deleteComment(int id) {

    }
}
