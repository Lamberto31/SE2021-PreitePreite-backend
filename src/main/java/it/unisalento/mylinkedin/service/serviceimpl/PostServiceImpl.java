package it.unisalento.mylinkedin.service.serviceimpl;

import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dao.*;
import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.InvalidValueException;
import it.unisalento.mylinkedin.exception.post.*;
import it.unisalento.mylinkedin.exception.user.UserNotFoundException;
import it.unisalento.mylinkedin.service.iservice.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    StructureRepository structureRepository;

    @Autowired
    AttributeRepository attributeRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    StructureAttributeRepository structureAttributeRepository;

    @Autowired
    UserInterestedPostRepository userInterestedPostRepository;


    @Override
    @Transactional
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    @Transactional(rollbackOn = PostSavingException.class)
    public Post save(Post post) throws PostSavingException {
        try {
            return postRepository.save(post);
        } catch (Exception e) {
            throw new PostSavingException();
        }
    }

    @Override
    @Transactional(rollbackOn = PostNotFoundException.class)
    public Post getById(int id) throws PostNotFoundException {
        return postRepository.findById(id).orElseThrow(PostNotFoundException::new);
    }

    @Override
    @Transactional(rollbackOn = PostNotFoundException.class)
    public Post delete(Post post) throws PostNotFoundException {
        try {
            postRepository.delete(post);
            return post;
        } catch (Exception e) {
            throw new PostNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = PostNotFoundException.class)
    public List<Post> getByIsPrivate(boolean isPrivate) throws PostNotFoundException {
        try {
            List<Post> postFoundList = postRepository.findByIsPrivate(isPrivate);
            if (postFoundList.isEmpty()) {
                throw new PostNotFoundException();
            }
            return postFoundList;
        } catch (Exception e) {
            throw new PostNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = PostNotFoundException.class)
    public void updateIsHidden(boolean isHidden, int id) throws PostNotFoundException {
        try {
            postRepository.findById(id).orElseThrow(PostNotFoundException::new);
            postRepository.updateIsHidden(isHidden, id);
        } catch (Exception e) {
            throw new PostNotFoundException();
        }
    }

    @Override
    public User getUser(Post post) throws UserNotFoundException {
        try {
            User userFound = postRepository.findUserById(post);
            if (userFound == null) {
                throw new UserNotFoundException();
            }
            return userFound;
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    @Transactional
    public List<Structure> getAllStructure() {
        return structureRepository.findAll();
    }

    @Override
    @Transactional(rollbackOn = StructureSavingException.class)
    public Structure saveStructure(Structure structure) throws StructureSavingException {
        try {
            return structureRepository.save(structure);
        } catch (Exception e) {
            throw new StructureSavingException();
        }
    }

    @Override
    @Transactional(rollbackOn = StructureNotFoundException.class)
    public Structure getStructureById(int id) throws StructureNotFoundException {
        return structureRepository.findById(id).orElseThrow(StructureNotFoundException::new);
    }

    @Override
    @Transactional(rollbackOn = StructureNotFoundException.class)
    public Structure deleteStructure(Structure structure) throws StructureNotFoundException {
        try {
            structureRepository.delete(structure);
            return structure;
        } catch (Exception e) {
            throw new StructureNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = StructureNotFoundException.class)
    public List<Structure> getStructureByUserCanPublish(String userCanPublish) throws StructureNotFoundException, InvalidValueException {
        try {
            if (!Constants.CAN_PUBLISH_LIST.contains(userCanPublish)) {
                throw new InvalidValueException();
            }
        } catch (Exception e) {
            throw new InvalidValueException();
        }
        try {
            List<Structure> structureFoundList = structureRepository.findByUserCanPublish(userCanPublish);
            if (structureFoundList.isEmpty()) {
                throw new StructureNotFoundException();
            }
            return structureFoundList;
        } catch (Exception e) {
            throw new StructureNotFoundException();
        }
    }

    @Override
    @Transactional
    public List<Attribute> getAllAttribute() {
        return attributeRepository.findAll();
    }

    @Override
    @Transactional(rollbackOn = AttributeSavingException.class)
    public Attribute saveAttribute(Attribute attribute) throws AttributeSavingException {
        try {
            return attributeRepository.save(attribute);
        } catch (Exception e) {
            throw new AttributeSavingException();
        }
    }

    @Override
    @Transactional(rollbackOn = AttributeNotFoundException.class)
    public Attribute getAttributeById(int id) throws AttributeNotFoundException {
        return attributeRepository.findById(id).orElseThrow(AttributeNotFoundException::new);
    }

    @Override
    @Transactional(rollbackOn = AttributeNotFoundException.class)
    public Attribute deleteAttribute(Attribute attribute) throws AttributeNotFoundException {
        try {
            attributeRepository.delete(attribute);
            return attribute;
        } catch (Exception e) {
            throw new AttributeNotFoundException();
        }
    }

    @Override
    @Transactional
    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    @Override
    @Transactional(rollbackOn = CommentSavingException.class)
    public Comment saveComment(Comment comment) throws CommentSavingException {
        try {
            return commentRepository.save(comment);
        } catch (Exception e) {
            throw new CommentSavingException();
        }
    }

    @Override
    @Transactional(rollbackOn = CommentNotFoundException.class)
    public Comment getCommentById(int id) throws CommentNotFoundException {
        return commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
    }

    @Override
    @Transactional(rollbackOn = CommentNotFoundException.class)
    public Comment deleteComment(Comment comment) throws CommentNotFoundException {
        try {
            commentRepository.delete(comment);
            return comment;
        } catch (Exception e) {
            throw new CommentNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = CommentNotFoundException.class)
    public List<Comment> getCommentByPost(Post post) throws CommentNotFoundException {
        try {
            List<Comment> commentFoundList = commentRepository.getByPost(post);
            if (commentFoundList.isEmpty()) {
                throw new CommentNotFoundException();
            }
            return commentFoundList;
        } catch (Exception e) {
            throw new CommentNotFoundException();
        }
    }

    @Override
    @Transactional
    public List<StructureAttribute> getAllStructureAttribute() {
        return structureAttributeRepository.findAll();
    }

    @Override
    @Transactional(rollbackOn = StructureAttributeSavingException.class)
    public StructureAttribute saveStructureAttribute(StructureAttribute structureAttribute) throws StructureAttributeSavingException {
        try {
            return structureAttributeRepository.save(structureAttribute);
        } catch (Exception e) {
            throw new StructureAttributeSavingException();
        }
    }

    @Override
    @Transactional(rollbackOn = StructureAttributeSavingException.class)
    public StructureAttribute getStructureAttributeById(int id) throws StructureAttributeNotFoundException {
        return structureAttributeRepository.findById(id).orElseThrow(StructureAttributeNotFoundException::new);
    }

    @Override
    @Transactional(rollbackOn = StructureAttributeSavingException.class)
    public StructureAttribute deleteStructureAttribute(StructureAttribute structureAttribute) throws StructureAttributeNotFoundException {
        try {
            structureAttributeRepository.delete(structureAttribute);
            return structureAttribute;
        } catch (Exception e) {
            throw new StructureAttributeNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = AttributeNotFoundException.class)
    public List<Attribute> getAttributeByStructure(Structure structure) throws AttributeNotFoundException {
        try {
            List<Attribute> attributeFoundList = structureAttributeRepository.findAttributeByStructure(structure);
            if (attributeFoundList.isEmpty()) {
                throw new AttributeNotFoundException();
            }
            return attributeFoundList;
        } catch (Exception e) {
            throw new AttributeNotFoundException();
        }
    }

    @Override
    @Transactional
    public List<UserInterestedPost> getAllUserInterestedPost() {
        return userInterestedPostRepository.findAll();
    }

    @Override
    @Transactional(rollbackOn = UserInterestedPostSavingException.class)
    public UserInterestedPost saveUserInterestedPost(UserInterestedPost userInterestedPost) throws UserInterestedPostSavingException {
        try {
            return userInterestedPostRepository.save(userInterestedPost);
        } catch (Exception e) {
            throw new UserInterestedPostSavingException();
        }
    }

    @Override
    @Transactional(rollbackOn = UserInterestedPostNotFoundException.class)
    public UserInterestedPost getUserInterestedPostById(int id) throws UserInterestedPostNotFoundException {
        return userInterestedPostRepository.findById(id).orElseThrow(UserInterestedPostNotFoundException::new);
    }

    @Override
    @Transactional(rollbackOn = UserInterestedPostNotFoundException.class)
    public UserInterestedPost deleteUserInterestedPost(UserInterestedPost userInterestedPost) throws UserInterestedPostNotFoundException {
        try {
            userInterestedPostRepository.delete(userInterestedPost);
            return userInterestedPost;
        } catch (Exception e) {
            throw new UserInterestedPostNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = UserNotFoundException.class)
    public List<User> getUserByInterestedPost(Post post) throws UserNotFoundException {
        try {
            List<User> userFoundList = userInterestedPostRepository.findUserByPost(post);
            if (userFoundList.isEmpty()) {
                throw new UserNotFoundException();
            }
            return userFoundList;
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }
}
