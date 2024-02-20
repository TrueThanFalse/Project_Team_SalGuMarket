package com.SalGuMarket.www.service;

import org.springframework.stereotype.Service;

import com.SalGuMarket.www.repository.NoticeBoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeBoardServiceImpl implements NoticeBoardService{
	
	private final NoticeBoardMapper nbm;

}
