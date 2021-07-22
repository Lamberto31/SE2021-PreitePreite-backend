package it.unisalento.mylinkedin.service.iservice;


import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.InvalidValueException;
import it.unisalento.mylinkedin.exception.post.*;
import it.unisalento.mylinkedin.exception.user.UserNotFoundException;

import java.util.Date;
import java.util.List;

public interface IPostService {
    List<Post> getAll();

    Post save(Post post) throws PostSavingException;

    Post getById(int id) throws PostNotFoundException;

    Post delete(Post post) throws PostNotFoundException;

    List<Post> getByIsPrivateAndIsHidden(boolean isPrivate, boolean isHidden) throws PostNotFoundException;

    List<Post> getByIsHidden(boolean isHidden) throws PostNotFoundException;

    void updateIsHidden(boolean isHidden, int id) throws PostNotFoundException, InvalidValueException;

    User getUser(Post post) throws UserNotFoundException;

    List<Post> getAllOrderByPubblicationDateDesc();

    List<Post> getJobOfferByOfferorAndByPubblicationDateBetweenAndSkill(User offeror, Date firstDate, Date lastDate, String skill) throws PostNotFoundException;


    //STRUCTURE
    List<Structure> getAllStructure();

    Structure saveStructure(Structure structure) throws StructureSavingException;

    Structure getStructureById(int id) throws StructureNotFoundException;

    Structure deleteStructure(Structure structure) throws StructureNotFoundException;

    List<Structure> getStructureByUserCanPublish(String userCanPublish) throws StructureNotFoundException, InvalidValueException;


    //ATTRIBUTE
    List<Attribute> getAllAttribute();

    Attribute saveAttribute(Attribute attribute) throws AttributeSavingException;

    Attribute getAttributeById(int id) throws AttributeNotFoundException;

    Attribute deleteAttribute(Attribute attribute) throws AttributeNotFoundException;


    //COMMENT
    List<Comment> getAllComment();

    Comment saveComment(Comment comment) throws CommentSavingException;

    Comment getCommentById(int id) throws CommentNotFoundException;

    Comment deleteComment(Comment comment) throws CommentNotFoundException;

    List<Comment> getCommentByPost(Post post) throws CommentNotFoundException;


    //STRUCTURE_ATTRIBUTE
    List<StructureAttribute> getAllStructureAttribute();

    StructureAttribute saveStructureAttribute(StructureAttribute structureAttribute) throws StructureAttributeSavingException;

    StructureAttribute getStructureAttributeById(int id) throws StructureAttributeNotFoundException;

    StructureAttribute deleteStructureAttribute(StructureAttribute structureAttribute) throws StructureAttributeNotFoundException;

    List<Attribute> getAttributeByStructure(Structure structure) throws AttributeNotFoundException;


    //USER_INTERESTED_POST
    List<UserInterestedPost> getAllUserInterestedPost();

    UserInterestedPost saveUserInterestedPost(UserInterestedPost userInterestedPost) throws UserInterestedPostSavingException;

    UserInterestedPost getUserInterestedPostById(int id) throws UserInterestedPostNotFoundException;

    UserInterestedPost deleteUserInterestedPost(UserInterestedPost userInterestedPost) throws UserInterestedPostNotFoundException;

    List<User> getUserByInterestedPost(Post post) throws UserNotFoundException;
}
