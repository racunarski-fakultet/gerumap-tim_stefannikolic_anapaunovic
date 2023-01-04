package dsw.rumap.app.msggenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Message {

    private Problem message;
    private MessageType type;
    private LocalDateTime timeStamp;

    public Message(MessageType type, Problem message) {
        this.type = type;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }

    public void setUp(MessageType type, Problem message){
        this.type = type;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }
}
