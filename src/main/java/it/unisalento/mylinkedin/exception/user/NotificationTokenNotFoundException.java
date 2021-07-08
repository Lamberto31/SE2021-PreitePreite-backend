package it.unisalento.mylinkedin.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Notification Token not found")
public class NotificationTokenNotFoundException extends Exception {
}
