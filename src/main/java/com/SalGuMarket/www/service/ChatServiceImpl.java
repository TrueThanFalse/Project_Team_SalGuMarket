package com.SalGuMarket.www.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.SalGuMarket.www.domain.ChatRoom;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements ChatService{
	
	// DB연결시
	/* private final ChatRoomMapper chatRoomMapper; */
	private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }
	
	@Override
	public ChatRoom createRoom(String name) {
	//	return chatRoomMapper.createRoom(name);
		ChatRoom chatRoom = ChatRoom.builder()
				.name(name)
				.build();
		chatRooms.put(name, chatRoom);
        return chatRoom;
	}

	@Override
	public ChatRoom findRoomById(String name) {
	//	return ChatRoomMapper.joinRoom(name);
		return chatRooms.get(name);
	}

	@Override
	public List<ChatRoom> findAllRoom() {
	//	return chatRoomMapper.allRoom();
		return new ArrayList<>(chatRooms.values());
	}

}
