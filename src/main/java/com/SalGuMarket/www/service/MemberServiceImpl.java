package com.SalGuMarket.www.service;

import org.springframework.stereotype.Service;

import com.SalGuMarket.www.repository.MemberMapper;
import com.SalGuMarket.www.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final MemberMapper memberMapper;

	@Override
	public int insert(MemberVO mvo) {
		// TODO Auto-generated method stub
		int isOK = memberMapper.insert(mvo);
		return memberMapper.insertAuthinit(mvo.getEmail());
	}

	@Override
	public MemberVO selectUser(String email) {
		MemberVO member = new MemberVO();
		member.setEmail(email);
		return member;
	}
}