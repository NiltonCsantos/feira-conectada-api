package org.feiraconectada.feiraconectadaapi.service.expo;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Service;

@Service
public class ExpoServiceImpl {
    private static final String EXPO_API_URL = "https://exp.host/--/api/v2/push/send";
    public void sendPushNotification(String token, String message, String title) {
        HttpResponse<JsonNode> response = Unirest.post(EXPO_API_URL)
                .header("Content-Type", "application/json")
                .body("{\"to\":\"" + token + "\","
                        + "\"sound\":\"default\","
                        + "\"title\":\"" + title + "\","
                        + "\"body\":\"" + message + "\"}")
                .asJson();

        if (response.getStatus() == 200) {
            System.out.println("Notificação enviada com sucesso!");
        } else {
            System.out.println("Erro ao enviar notificação: " + response.getBody());
        }
    }
}
