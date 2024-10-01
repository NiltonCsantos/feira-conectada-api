package org.feiraconectada.feiraconectadaapi.service.firebase;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireBaseService {
    public void sendNotification(String token, String title, String body) {
        try {
            Message message = Message.builder()
                    .setToken("T5SYI3BuEZEe6UbW9TM5pT")
                    .setNotification(Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
                    .build();
            FirebaseMessaging.getInstance().send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendNotifications(List<String> tokens, String title, String body) {
        for (String token : tokens) {
            sendNotification(token, title, body);
        }
    }
}
