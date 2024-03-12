package com.SalGuMarket.www.handler;

import java.io.IOException;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.SalGuMarket.www.domain.ChatMessage;
import com.SalGuMarket.www.domain.ChatRoom;
import com.SalGuMarket.www.service.ChatService;
import com.SalGuMarket.www.service.ChattingLogService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSockChatHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;
    private final ChattingLogService chattingLog;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            log.info("WebSocket 연결이 확립되었습니다. Session ID: {}", session.getId());
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("Received message: {}"+payload);
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        ChatRoom room = chatService.findRoomByBno(chatMessage.getChatBno());
        log.info("room 객체확인 : "+ room);
        Set<WebSocketSession> sessions=room.getSessions();   //방에 있는 현재 사용자 한명이 WebsocketSession
        log.info("WebSocket Handler 부분 =======");
        log.info("chatMessage type : "+chatMessage.getType());
        log.info("chatMessage Message : "+ chatMessage.getMessage());
        log.info("chatMessage sender : "+ chatMessage.getSender());
        log.info("session : "+session);
        log.info("세션연결");
        
        if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
        	log.info("type : " + chatMessage.getType());
            //사용자가 방에 입장하면  Enter메세지를 보내도록 해놓음.  이건 새로운사용자가 socket 연결한 것이랑은 다름.
            //socket연결은 이 메세지 보내기전에 이미 되어있는 상태
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");  //TALK일 경우 msg가 있을 거고, ENTER일 경우 메세지 없으니까 message set            sendToEachSocket(sessions,new TextMessage(objectMapper.writeValueAsString(chatMessage)) );
            log.info(" 입장메세지 확인 : "+chatMessage.getMessage());
            sendToEachSocket(sessions,new TextMessage(objectMapper.writeValueAsString(chatMessage)) );
            log.info("message : "+message.getPayload());
        }else if (chatMessage.getType().equals(ChatMessage.MessageType.QUIT)) {
        	log.info("type : " + chatMessage.getType());
            sessions.remove(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 퇴장했습니다..");
            sendToEachSocket(sessions,new TextMessage(objectMapper.writeValueAsString(chatMessage)) );
            log.info("message payload : "+message.getPayload());
        }else if(chatMessage.getType().equals(ChatMessage.MessageType.TALK)){
        	log.info("session : " + session);
            log.info("message ~~~~~~~~: "+message.getPayload());
            log.info("type : " + chatMessage.getType());
            log.info("message : 0 :"+chatMessage.getMessage());
            // 메시지 저장
            chattingLog.saveChattingLog(chatMessage);
            sendToEachSocket(sessions,new TextMessage(objectMapper.writeValueAsString(chatMessage)) ); //입장,퇴장 아닐 때는 클라이언트로부터 온 메세지 그대로 전달.
        }
    }
    private void sendToEachSocket(Set<WebSocketSession> sessions, TextMessage message) {
        sessions.parallelStream().forEach(roomSession -> {
            try {
            	log.info("roomSession IN");
            	log.info("roomSession IN message {}", message.getPayload());
                roomSession.sendMessage(message);
            } catch (IOException e) {
            	log.error("IOException while sending message to session ID {}: {}", roomSession.getId(), e.getMessage());
                throw new RuntimeException(e);
            } catch (Exception e) {
                log.error("Exception while sending message to session ID {}: {}", roomSession.getId(), e.getMessage());
            }
        });
    }



    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
       //javascript에서  session.close해서 연결 끊음. 그리고 이 메소드 실행.
        //session은 연결 끊긴 session을 매개변수로 이거갖고 뭐 하세요.... 하고 제공해주는 것 뿐
    	log.info("WebSocket 연결을 끊었습니다. Session ID: {}", session.getId());
    }



}
