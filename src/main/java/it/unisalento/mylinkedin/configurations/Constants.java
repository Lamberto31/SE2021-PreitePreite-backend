package it.unisalento.mylinkedin.configurations;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public interface Constants {

    //Variabili statiche per tipo utente
    String TYPE_ADMIN = "admin";
    String TYPE_OFFEROR = "offeror";
    String TYPE_APPLICANT = "applicant";

    //Variabili tipo utente per spring security
    String ROLE_ADMIN = "ROLE_ADMIN";
    String ROLE_REGISTEREDUSER = "ROLE_REGISTEREDUSER";

    //Variabili statiche per stato registrazione
    String REGISTRATION_PENDING = "pending";
    String REGISTRATION_ACCEPTED = "accepted";
    String REGISTRATION_BLOCKED = "blocked";

    List<String> REGISTRATION_STATUS_LIST = new ArrayList<>() {{
        add(REGISTRATION_PENDING);
        add(REGISTRATION_ACCEPTED);
        add(REGISTRATION_BLOCKED);
    }};

    //Variabili statiche per tipo utente
    String CAN_PUBLISH_OFFEROR = "offeror";
    String CAN_PUBLISH_APPLICANT = "applicant";
    String CAN_PUBLISH_BOTH = "both";

    List<String> CAN_PUBLISH_LIST = new ArrayList<>() {{
        add(CAN_PUBLISH_OFFEROR);
        add(CAN_PUBLISH_APPLICANT);
        add(CAN_PUBLISH_BOTH);
    }};

    //Formato data
    String DATE_FORMAT = "dd/MM/yyyy HH:mm";
    SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT);
    String timezone = "Europe/Rome";


    //REST URI

    //Common
    String URI_GETBYID = "/getById/{id}";
    String URI_GETALL = "/getAll";
    String URI_SAVE = "/save";
    String URI_DELETE = "/delete/{id}";
    String URI_LOGIN = "/login/{email}";

    //AdminRestController
    String URI_ADMIN = "/admin";

    String URI_APPLICANT = "/applicant";
    String URI_OFFEROR = "/offeror";

    String URI_GETBYSTATUS = "/getByStatus/{status}";
    String URI_UPDATESTATUSREGISTRATION = "/updateStatusRegistration/{id}/{status}";

    String URI_POST = "/post";
    String URI_UPDATEISHIDDEN = "/updateIsHidden/{id}/{isHidden}";

    String URI_STRUCTURE = "/structure";

    String URI_ATTRIBUTE = "/attribute";

    String URI_STRUCTUREATTRIBUTEID = "/structureAttribute/{structureId}/{attributeId}";

    //RegisteredUserRestController
    String URI_REGISTEREDUSER = "/registeredUser";

    String URI_MESSAGE = "/message";
    String URI_GETBYTWOUSER = "/getByTwoUser/{user1Id}/{user2Id}";
    String URI_GETBYSENDER = "/getBySender/{senderId}";
    String URI_GETBYRECEIVER = "/getByReceiver/{receiverId}";
    String URI_GETBYUSERSENTORRECEIVED = "/getByUserSentOrReceived/{userId}";
    String URI_GETBYRECEIVERANDNOTREAD = "/getByReceiverAndNotRead/{receiverId}";
    String URI_UPDATEISREAD = "/updateIsRead/{messageId}/{isRead}";
    String URI_GETBYSENDERANDRECEIVERANDNOTREAD = "/getBySenderAndReceiverAndNotRead/{senderId}/{receiverId}";

    String URI_GETCANPUBLISH = "/getCanPublish";

    String URI_COMMENT = "/comment";
    String URI_GETBYPOST = "/getByPost/{postId}";

    String URI_GETBYSTRUCTURE = "/getByStructure/{structureId}";

    String URI_NOTIFICATIONTOKEN = "/notificationToken";

    String URI_GETFILTEREDJOBOFFER = "/getByOfferorAndByPubblicationDateBetweenAndSkill/{offerorId}/{firstDate}/{lastDate}/{skillIdentifier}";

    //UserRestController
    String URI_USER = "/user";

    String URI_PROFILEIMAGE = "/profileImage";
    String URI_GETBYUSER = "/getByUser/{userId}";

    String URI_COMPANY = "/company";
    String URI_GETBYOFFERORID = "/getCompanyByOfferorId/{offerorId}";

    String URI_GETPUBLIC = "/getPublic";

    String URI_GETSHOWN = "/getShown";

    String URI_GETBYINTERESTED = "/getByInterested/{postId}";

    String S3_IMAGEPREFIX = "https://mylinkedinpp-picture.s3.amazonaws.com/";

    String S3_USERPROFILEIMAGEDEFAULT = "https://mylinkedinpp-picture.s3.amazonaws.com/UserProfileImageDefault.png";

    String S3_COMPANYPROFILEIMAGEDEFAULT = "https://mylinkedinpp-picture.s3.amazonaws.com/CompanyProfileImageDefault.jpg";

    String URI_USERINTERESTEDPOSTID = "/userInterestedPost/{userId}/{postId}";


    //AWS API GATEWAY REST URI

    String AWS_URI_API = "https://9d9g5q4w06.execute-api.us-east-1.amazonaws.com/beta";

    String AWS_URI_PUSHNOTIFICATION = "/pushNotification";

    String AWS_URI_TOKEN = "/token";

}
