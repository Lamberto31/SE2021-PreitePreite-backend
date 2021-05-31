package it.unisalento.mylinkedin.service.serviceimpl;

import it.unisalento.mylinkedin.dao.*;
import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.InvalidValueException;
import it.unisalento.mylinkedin.exception.post.*;
import it.unisalento.mylinkedin.exception.user.CompanyNotFoundException;
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
    public void delete(Post post) throws PostNotFoundException {
        try {
            postRepository.delete(post);
        } catch (Exception e) {
            throw new PostNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = PostNotFoundException.class)
    public List<Post> getByIsPrivate(boolean isPrivate) throws PostNotFoundException {
        try {
            return postRepository.findByIsPrivate(isPrivate);
        } catch (Exception e) {
            throw new PostNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = PostNotFoundException.class)
    public void updateIsHidden(boolean isHidden, int id) throws PostNotFoundException, InvalidValueException {
        try {
            postRepository.updateIsHidden(isHidden, id);
        } catch (Exception e) {
            throw new PostNotFoundException();
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
    public void deleteStructure(Structure structure) throws StructureNotFoundException {
        try {
            structureRepository.delete(structure);
        } catch (Exception e) {
            throw new StructureNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = StructureNotFoundException.class)
    public List<Structure> getStructureByUserCanPublish(String userCanPublish) throws StructureNotFoundException {
        try {
            return structureRepository.findByUserCanPublish(userCanPublish);
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
    public void deleteAttribute(Attribute attribute) throws AttributeNotFoundException {
        try {
            attributeRepository.delete(attribute);
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
    public void deleteComment(Comment comment) throws CommentNotFoundException {
        try {
            commentRepository.delete(comment);
        } catch (Exception e) {
            throw new CommentNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = CommentNotFoundException.class)
    public List<Comment> getCommentByPost(Post post) throws CommentNotFoundException {
        try {
            return commentRepository.getByPost(post);
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
    public void deleteStructureAttribute(StructureAttribute structureAttribute) throws StructureAttributeNotFoundException {
        try {
            structureAttributeRepository.delete(structureAttribute);
        } catch (Exception e) {
            throw new StructureAttributeNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = AttributeNotFoundException.class)
    public List<Attribute> getAttributeByStructure(Structure structure) throws AttributeNotFoundException {
        try {
            return structureAttributeRepository.findAttributeByStructure(structure);
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
    public void deleteUserInterestedPost(UserInterestedPost userInterestedPost) throws UserInterestedPostNotFoundException {
        try {
            userInterestedPostRepository.delete(userInterestedPost);
        } catch (Exception e) {
            throw new UserInterestedPostNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = UserNotFoundException.class)
    public List<User> getUserByInterestedPost(Post post) throws UserNotFoundException {
        try {
            return userInterestedPostRepository.findUserByPost(post);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }
}
