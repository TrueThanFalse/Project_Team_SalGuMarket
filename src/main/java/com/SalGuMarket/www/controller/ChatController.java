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
    private final MemberService memberService;

    
    // 채팅방 목록
    @GetMapping("/chatList")
    public String chatList(Model model){
        List<ChatRoom> roomList = chatService.findAllRoom();
        model.addAttribute("roomList",roomList);
        return "chat/chatList";
    }

    
    // 방만들기
    @PostMapping("/createRoom")
    public String createRoom(Model model, @RequestParam(value="name") String name
    		,@RequestParam(value="userEmail") String Email) {
        ChatRoom room = chatService.createRoom(name);
        //MemberVO userEmail = memberService.selectUser(Email);
        model.addAttribute("room",room);
       // model.addAttribute("username",userEmail);
        return "chat/chatRoom";  //만든사람이 먼저들어감
    }

    // 방들어가기
    // 로그인된 user 들고가기 : 수정필요함
    @GetMapping("/chatRoom")
    public String chatRoom(Model model, @RequestParam(value="name") String name
    		,@RequestParam(value="userEmail") String Email){
        ChatRoom room = chatService.findRoomById(name);
        //MemberVO userEmail = memberService.selectUser(Email);
        model.addAttribute("room",room);
        //model.addAttribute("username",userEmail);
        return "chat/chatRoom";
    }
    
    
    // readCount 용
    
}
