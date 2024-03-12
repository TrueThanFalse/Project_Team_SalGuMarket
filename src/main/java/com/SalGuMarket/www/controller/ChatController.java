package com.SalGuMarket.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.SalGuMarket.www.domain.ChatRoom;
import com.SalGuMarket.www.security.MemberVO;
import com.SalGuMarket.www.service.ChatService;
import com.SalGuMarket.www.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@RequestMapping("/chat/*")
@Slf4j
public class ChatController {
	private final ChatService chatService;

    
    // 채팅방 목록
    @GetMapping("/chatList")
    public String chatList(Model model){
        List<ChatRoom> roomList = chatService.findAllRoom();
        model.addAttribute("roomList",roomList);
        return "chat/chatList";
    }

    // 방 만들기
    @PostMapping("/createRoom")
    public String createRoom(Model model,ChatRoom chatRoom) {
    	log.info("chatRoom : "+chatRoom);
        // 방 생성 및 정보 가져오기
    	chatService.createRoom(chatRoom);
    	
        log.info("chatBno 확인: "+chatRoom.getChatBno());
        return "index";
    }

    // 방 들어가기
    @GetMapping("/chatRoom")
    public String chatRoom(Model model,
    		@RequestParam("chatName") String chatName) {
    	log.info("chatName : "+chatName);
        // 채팅방 정보 가져오기
        ChatRoom room = chatService.findRoomById(chatName);

        model.addAttribute("room", room);
        return "chat/chatRoom";
    }
    
}
