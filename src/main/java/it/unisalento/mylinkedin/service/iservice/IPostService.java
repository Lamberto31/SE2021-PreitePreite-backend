package it.unisalento.mylinkedin.service.iservice;


import it.unisalento.mylinkedin.entities.Attribute;
import it.unisalento.mylinkedin.entities.Comment;
import it.unisalento.mylinkedin.entities.Post;
import it.unisalento.mylinkedin.entities.Structure;
import it.unisalento.mylinkedin.exception.post.*;
import it.unisalento.mylinkedin.exception.InvalidValueException;

import java.util.List;

public interface IPostService {
    List<Post> getAll();

    Post save(Post post) throws PostSavingException;

    Post getById(int id) throws PostNotFoundException;

    void delete(Post post) throws PostNotFoundException;

    List<Post> getByIsPrivate(boolean isPrivate) throws PostNotFoundException;

    void updateIsHidden(boolean isHidden, int id) throws PostNotFoundException, InvalidValueException;


    //STRUCTURE
    List<Structure> getAllStructure();

    Structure saveStructure(Structure structure) throws StructureSavingException;

    Structure getStructureById(int id) throws StructureNotFoundException;

    void deleteStructure(Structure structure) throws StructureNotFoundException;

    List<Structure> getByUserCanPublish(String userCanPublish) throws StructureNotFoundException;


    //ATTRIBUTE
    List<Attribute> getAllAttribute();

    Attribute saveAttribute(Attribute attribute) throws AttributeSavingException;

    Attribute getAttributeById(int id) throws AttributeNotFoundException;

    void deleteAttribute(Attribute attribute) throws AttributeNotFoundException;


    //COMMENT
    List<Comment> getAllComment();

    Comment saveComment(Comment comment) throws CommentSavingException;

    Comment getCommentById(int id) throws CommentNotFoundException;

    void deleteComment(Comment comment) throws CommentNotFoundException;
}
