package com.SalGuMarket.www.service;

import org.springframework.stereotype.Service;

import com.SalGuMarket.www.repository.SmartContractMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SmartContractServiceImpl implements SmartContractService{

	private final SmartContractMapper smartContractMapper;
}
