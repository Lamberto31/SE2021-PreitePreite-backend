package it.unisalento.mylinkedin.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User status not valid")
public class UserStatusInvalidException extends Exception {
}
