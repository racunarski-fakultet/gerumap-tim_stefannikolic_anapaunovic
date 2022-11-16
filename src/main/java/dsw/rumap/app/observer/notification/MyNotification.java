package dsw.rumap.app.observer.notification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyNotification {

    private NotificationType type;
    private Object information;

    public MyNotification(NotificationType type) {
        this.type = type;
    }

    public MyNotification(NotificationType type, Object information) {
        this.type = type;
        this.information = information;
    }
}
