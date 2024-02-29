package com.SalGuMarket.www.service;

import org.springframework.stereotype.Service;

import com.SalGuMarket.www.repository.BoardMapper;
import com.SalGuMarket.www.repository.CommentMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService{
	
	private final CommentMapper cm;
	private final BoardMapper bm;

}
