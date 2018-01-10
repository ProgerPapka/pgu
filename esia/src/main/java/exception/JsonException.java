package exception;

import java.io.IOException;

public class JsonException extends Exception {
    public JsonException(IOException e) {
        super(e);
    }
}
