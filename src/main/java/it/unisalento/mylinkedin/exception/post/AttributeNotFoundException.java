package it.unisalento.mylinkedin.exception.post;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Attribute not found")
public class AttributeNotFoundException extends Exception {
}
