package dsw.rumap.app.msggenerator;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
public class Message {

    private String message;
    private MessageType type;
    private LocalDateTime timeStamp;

    public Message(MessageType type, String message) {
        this.type = type;
        this.message = message;
        this.timeStamp = LocalDateTime.now();

    }
}
