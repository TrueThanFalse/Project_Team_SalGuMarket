package com.SalGuMarket.www.repository;

import org.apache.ibatis.annotations.Mapper;

import com.SalGuMarket.www.domain.ChatMessage;

@Mapper
public interface ChattingLogMapper {

	void saveChattingLog(ChatMessage chatMessage);

}
