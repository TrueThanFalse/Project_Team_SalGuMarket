package com.SalGuMarket.www.service;

import java.util.List;

import org.springframework.ui.Model;

import com.SalGuMarket.www.domain.ChatRoom;

public interface ChatService {

    ChatRoom findRoomById(String chatName);

    List<ChatRoom> findAllRoom();

	void createRoom(ChatRoom chatRoom);

	ChatRoom findRoomByBno(long chatBno);
}
