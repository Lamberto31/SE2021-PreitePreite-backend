package it.unisalento.mylinkedin.configurations;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public interface Constants {

    //Variabili statiche per tipo utente
    String TYPE_ADMIN = "admin";
    String TYPE_OFFEROR = "offeror";
    String TYPE_APPLICANT = "applicant";

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

    //RegisteredUserRestController
    String URI_REGISTEREDUSER = "/registeredUser";

    String URI_MESSAGE = "/message";
    String URI_GETBYSENDERANDRECEIVER = "/getBySenderAndReceiver/{senderId}/{receiverId}";
    String URI_GETBYSENDER = "/getBySender/{senderId}";
    String URI_GETBYRECEIVER = "/getByReceiver/{receiverId}";

    String URI_GETCANPUBLISH = "/getCanPublish";

    String URI_COMMENT = "/comment";
    String URI_GETBYPOST = "/getByPost/{postId}";

    String URI_GETBYSTRUCTURE = "/getByStructure/{structureId}";

    //UserRestController
    String URI_USER = "/user";

    String URI_PROFILEIMAGE = "/profileImage";
    String URI_GETBYUSER = "/getByUser/{userId}";

    String URI_COMPANY = "/company";
    String URI_GETBYOFFERORID = "/getCompanyByOfferorId/{offerorId}";

    String URI_GETPUBLIC = "/getPublic";

    String URI_GETBYINTERESTED = "/getByInterested/{postId}";



}
