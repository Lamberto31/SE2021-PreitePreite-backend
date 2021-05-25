package it.unisalento.mylinkedin.service.iservice;


import it.unisalento.mylinkedin.entities.Attribute;
import it.unisalento.mylinkedin.entities.Comment;
import it.unisalento.mylinkedin.entities.Post;
import it.unisalento.mylinkedin.entities.Structure;

import java.util.List;

public interface IPostService {
    List<Post> getAll();

    Post save(Post post);

    Post getById(int id);

    void delete(int id);

    List<Post> getByIsPrivate(boolean isPrivate);

    void updateIsHidden(boolean isHidden, int id);


    //STRUCTURE
    List<Structure> getAllStructure();

    Structure saveStructure(Structure structure);

    Structure getStructureById(int id);

    void deleteStructure(int id);

    List<Structure> getByUserCanPublish(String userCanPublish);


    //ATTRIBUTE
    List<Attribute> getAllAttribute();

    Attribute saveAttribute(Attribute attribute);

    Attribute getAttributeById(int id);

    void deleteAttribute(int id);


    //COMMENT
    List<Comment> getAllComment();

    Comment saveComment(Comment comment);

    Comment getCommentById(int id);

    void deleteComment(int id);



}
