package com.SalGuMarket.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.SalGuMarket.www.domain.ChatRoom;

@Mapper
public interface ChatRoomMapper {

	void createRoom(ChatRoom chatRoom);

	ChatRoom joinRoom(String chatName);

	List<ChatRoom> findAllRoom();

	ChatRoom chattingSend(long chatBno);
    
}
