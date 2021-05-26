package it.unisalento.mylinkedin.service.iservice;


import it.unisalento.mylinkedin.entities.Attribute;
import it.unisalento.mylinkedin.entities.Comment;
import it.unisalento.mylinkedin.entities.Post;
import it.unisalento.mylinkedin.entities.Structure;
import it.unisalento.mylinkedin.exception.post.*;
import it.unisalento.mylinkedin.exception.user.UserStatusInvalidException;

import java.util.List;

public interface IPostService {
    List<Post> getAll();

    Post save(Post post) throws SavingPostException;

    Post getById(int id) throws PostNotFoundException;

    void delete(int id) throws PostNotFoundException;

    List<Post> getByIsPrivate(boolean isPrivate) throws PostNotFoundException;

    void updateIsHidden(boolean isHidden, int id) throws PostNotFoundException, UserStatusInvalidException;


    //STRUCTURE
    List<Structure> getAllStructure();

    Structure saveStructure(Structure structure) throws SavingStructureException;

    Structure getStructureById(int id) throws StructureNotFoundException;

    void deleteStructure(int id) throws StructureNotFoundException;

    List<Structure> getByUserCanPublish(String userCanPublish) throws StructureNotFoundException;


    //ATTRIBUTE
    List<Attribute> getAllAttribute();

    Attribute saveAttribute(Attribute attribute) throws SavingAttributeException;

    Attribute getAttributeById(int id) throws AttributeNotFoundException;

    void deleteAttribute(int id) throws AttributeNotFoundException;


    //COMMENT
    List<Comment> getAllComment();

    Comment saveComment(Comment comment) throws SavingCommentException;

    Comment getCommentById(int id) throws CommentNotFoundException;

    void deleteComment(int id) throws CommentNotFoundException;



}
