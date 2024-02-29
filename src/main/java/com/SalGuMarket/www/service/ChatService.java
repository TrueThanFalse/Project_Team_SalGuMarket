package com.SalGuMarket.www.service;

import java.util.List;

import com.SalGuMarket.www.domain.ChatRoom;

public interface ChatService {
	
	ChatRoom createRoom(String name);

	ChatRoom findRoomById(String name);

	List<ChatRoom> findAllRoom();

}
