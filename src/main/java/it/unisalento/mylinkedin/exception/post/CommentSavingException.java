package it.unisalento.mylinkedin.exception.post;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Saving error")
public class CommentSavingException extends Exception {
}
