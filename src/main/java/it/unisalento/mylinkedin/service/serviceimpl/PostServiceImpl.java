package it.unisalento.mylinkedin.service.serviceimpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.mylinkedin.configurations.Constants;
import it.unisalento.mylinkedin.dao.*;
import it.unisalento.mylinkedin.entities.*;
import it.unisalento.mylinkedin.exception.InvalidValueException;
import it.unisalento.mylinkedin.exception.post.*;
import it.unisalento.mylinkedin.exception.user.NotificationNotSentException;
import it.unisalento.mylinkedin.exception.user.UserNotFoundException;
import it.unisalento.mylinkedin.service.iservice.IPostService;
import it.unisalento.mylinkedin.service.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    IUserService userService;


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
    public List<Post> getByIsPrivateAndIsHidden(boolean isPrivate, boolean isHidden) throws PostNotFoundException {
        try {
            List<Post> postFoundList = postRepository.findByIsPrivateAndIsHiddenOrderByPubblicationDateDesc(isPrivate, isHidden);
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
    public List<Post> getByIsHidden(boolean isHidden) throws PostNotFoundException {
        try {
            List<Post> postFoundList = postRepository.findByIsHiddenOrderByPubblicationDateDesc(isHidden);
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
    @Transactional(rollbackOn = UserNotFoundException.class)
    public User getUser(Post post) throws UserNotFoundException {
        try {
            User userFound = postRepository.findUserById(post.getId());
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
    public List<Post> getAllOrderByPubblicationDateDesc() {
        return postRepository.findAllByOrderByPubblicationDateDesc();
    }

    @Override
    @Transactional(rollbackOn = PostNotFoundException.class)
    public List<Post> getJobOfferByOfferorAndByPubblicationDateBetweenAndSkill(User offeror, Date firstDate, Date lastDate, String skillIdentifier) throws PostNotFoundException {
        try {
            Structure jobOffer = structureRepository.findByTitle("job offer");
            List<Post> postFoundList = postRepository.findByStructureAndIsHidden(jobOffer, false);
            List<Post> postFilteredList = new ArrayList<>(postFoundList);

            if (postFoundList.isEmpty()) {
                throw new PostNotFoundException();
            }

            boolean userFilter = offeror != null;
            boolean dateFilter = (firstDate != null || lastDate != null);
            boolean skillFilter = skillIdentifier != null;

            if (dateFilter) {
                if (firstDate == null) {
                    firstDate = Constants.SIMPLE_DATE_FORMAT_ONLYDATE.parse("00-00-0000");
                } else if (lastDate == null) {
                    lastDate = Constants.SIMPLE_DATE_FORMAT_ONLYDATE.parse(Constants.SIMPLE_DATE_FORMAT_ONLYDATE.format(new Date()));
                }
                lastDate.setTime(lastDate.getTime() + TimeUnit.HOURS.toMillis(23) + TimeUnit.MINUTES.toMillis(59) + TimeUnit.SECONDS.toMillis(59));
            }

            ObjectMapper mapper = new ObjectMapper();
            for (Post post: postFoundList) {

                if (userFilter && post.getUser().getId() != offeror.getId()) {
                    postFilteredList.remove(post);
                    continue;
                }

                if(dateFilter) {
                    if (!firstDate.before(post.getPubblicationDate())) {
                        postFilteredList.remove(post);
                        continue;
                    } else if (!lastDate.after(post.getPubblicationDate())) {
                        postFilteredList.remove(post);
                        continue;
                    }
                }

                if (skillFilter) {
                    List<Post.Attributevalue> postDataList = mapper.readValue(post.getData(), new TypeReference<>() {
                    });

                    for (Post.Attributevalue postData: postDataList){
                        if (postData.getType().equals("skills")) {
                           List<Post.Skill> postDataSkillList = mapper.readValue(postData.getValue(), new TypeReference<>() {
                           });

                            for (Post.Skill postDataSkill: postDataSkillList) {
                                if (!postDataSkill.getIdentifier().equals(skillIdentifier)) {
                                    postFilteredList.remove(post);
                                }
                            }
                        }
                    }
                }
            }
            if (postFilteredList.isEmpty()) {
                throw new PostNotFoundException();
            }
            return postFilteredList;
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
            List<Attribute> attributeFoundList = structureAttributeRepository.findAttributeByStructure(structure.getId());
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
        User newUserInterested = userInterestedPost.getUser();
        List<User> UserAlreadyInterestedList = userInterestedPostRepository.findUserByPost(userInterestedPost.getPost().getId());

        for(User userAlreadyInterested: UserAlreadyInterestedList) {
            if (newUserInterested.getId() == userAlreadyInterested.getId()) {
                throw new UserInterestedPostSavingException();
            }
        }
        try {
            UserInterestedPost userInterestedPostSaved = userInterestedPostRepository.save(userInterestedPost);

            Optional<Post> post = postRepository.findById(userInterestedPostSaved.getPost().getId());
            if (post.isEmpty()) {
                throw new UserInterestedPostSavingException();
            }

            String notificationTitle = "New user is interested in your post!";
            String notificationBody = "User "+ newUserInterested.getName() + newUserInterested.getSurname() +" is interested in your "+ post.get().getStructure().getTitle() +"! ";
            User notificationUser = post.get().getUser();
            List<User> notificationUserList = new ArrayList<>(Collections.singletonList(notificationUser));
            userService.sendAwsPushNotification(notificationTitle, notificationBody, notificationUserList);
            return userInterestedPostSaved;
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
            List<User> userFoundList = userInterestedPostRepository.findUserByPost(post.getId());
            if (userFoundList.isEmpty()) {
                throw new UserNotFoundException();
            }
            return userFoundList;
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    @Transactional(rollbackOn = UserInterestedPostNotFoundException.class)
    @Scheduled(cron = "0 0 18 * * MON-FRI")
    public void updateUserInterestedPostIsNotified() throws UserInterestedPostNotFoundException, NotificationNotSentException {
        List<UserInterestedPost> userInterestedPostList = userInterestedPostRepository.findByIsNotified(false);

        HashMap<User, List<Post>> notificationList = new HashMap<>();

        // Costruzione lista con utenti e post da notificare
        for (UserInterestedPost userInterestedPost: userInterestedPostList) {

            Optional<Post> post = postRepository.findById(userInterestedPost.getPost().getId());
            if (post.isEmpty()) {
                throw new UserInterestedPostNotFoundException();
            }
            if (!post.get().getStructure().getTitle().equals("job offer")) {
                continue;
            }

            User user = post.get().getUser();
            if (notificationList.containsKey(user)) {
                List<Post> notifications = notificationList.get(user);
                notifications.add(post.get());
            } else {
                notificationList.put(user, Arrays.asList(post.get()));
            }
        }

        // Costruzione ed invio bulk notification
        for (User user : notificationList.keySet()) {
            String notificationTitle = "Today some users have shown interest on your post!";
            String notificationBody = "Here is the list of posts:";
            List<User> notificationUserList = new ArrayList<>(Collections.singletonList(user));
            for (Post post: notificationList.get(user)) {
                notificationBody = notificationBody.concat(System.getProperty("line.separator"));
                notificationBody = notificationBody.concat("- "+ post.getTitle());
            }
            userService.sendAwsPushNotification(notificationTitle, notificationBody, notificationUserList);
        }
        for (UserInterestedPost userInterestedPost: userInterestedPostList) {
            userInterestedPostRepository.updateIsNotified(true, userInterestedPost.getId());
        }
    }
}
