package Exceptions;

import java.io.IOException;

public class InvalidValueException extends IOException {
    public InvalidValueException(String message) {
        super(message);
    }

}
