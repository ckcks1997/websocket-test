package lesson7.lesson7;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> sessionList = new ArrayList<>();

    @Autowired
    private final AircraftRepository repository;

    public List<WebSocketSession> getSessionList() {
        return sessionList;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionList.add(session);
        System.out.println("connection established from"+session.toString()+"@ "+ Instant.now().toString());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            System.out.println("msg received: "+message + "from:"+session.toString());
            for (WebSocketSession sessionInList : sessionList) {
                if(sessionList != session){
                    sessionInList.sendMessage(message);
                    System.out.println("sending msg:"+message+"to:"+sessionInList.toString());
                }
            }
        } catch (Exception e){
            System.out.println("exception:"+e.getLocalizedMessage());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionList.remove(session);
        System.out.println("connection closed:"+session.toString()+"@ "+Instant.now().toString());
    }
}
