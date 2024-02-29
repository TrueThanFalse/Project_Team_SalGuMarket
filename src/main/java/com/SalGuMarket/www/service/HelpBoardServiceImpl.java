package com.SalGuMarket.www.service;

import org.springframework.stereotype.Service;

import com.SalGuMarket.www.repository.HelpBoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class HelpBoardServiceImpl implements HelpBoardService{
	
	private final HelpBoardMapper hm;

}
