package com.SalGuMarket.www.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SalGuMarket.www.service.SmartContractService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/smartContract/*")
public class SmartContractController {

	private final SmartContractService smartContractService;
}
