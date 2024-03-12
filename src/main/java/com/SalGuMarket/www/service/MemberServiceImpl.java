package com.SalGuMarket.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SalGuMarket.www.domain.BoardVO;
import com.SalGuMarket.www.domain.FileVO;
import com.SalGuMarket.www.domain.PagingVO;
import com.SalGuMarket.www.handler.PagingHandler;
import com.SalGuMarket.www.repository.FileMapper;
import com.SalGuMarket.www.repository.MemberMapper;
import com.SalGuMarket.www.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final MemberMapper memberMapper;
	private final FileMapper fileMapper;

	@Override
	public int insert(MemberVO mvo) {
		// TODO Auto-generated method stub
		int isOK = memberMapper.insert(mvo);
		return memberMapper.insertAuthinit(mvo.getEmail());
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return memberMapper.getTotalCount(pgvo);
	}

	@Override
	public List<MemberVO> getList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return memberMapper.getList(pgvo);
	}

	@Override
	public int remove(String email) {
		// TODO Auto-generated method stub
		return memberMapper.remove(email);
	}

	@Override
	public int delete(String email) {
		// TODO Auto-generated method stub
		return memberMapper.delete(email);
	}

	@Transactional
	@Override
	public int setProfile(MemberVO mvo) {
		// TODO Auto-generated method stub
		String email = mvo.getEmail();
		if(mvo.getFvo() != null) {
			FileVO fvo = mvo.getFvo();
			fvo.setEmail(email);
			fileMapper.deleteFile(mvo.getEmail());
			fileMapper.insertProfile(fvo);
			memberMapper.yesProfile(mvo.getEmail());
		}else {
			//프사 미선택 삭제
			fileMapper.deleteFile(mvo.getEmail());
			memberMapper.noProfile(mvo.getEmail());
		}
		int profile = memberMapper.getIsProfile(email);
		mvo.setIsProfile(profile);
		return profile;
	}
	
	@Override
	public MemberVO selectEmail(String email) {
		// TODO Auto-generated method stub
		return memberMapper.selectEmail(email);
	}
	
	@Override
	public MemberVO selectNickName(String nickName) {
		// TODO Auto-generated method stub
		return memberMapper.selcetNickName(nickName);
	}

	@Override
	public FileVO getFile(String email) {
		// TODO Auto-generated method stub
		return fileMapper.getFile(email);
	}

	@Override
	public PagingHandler getBoardList(String email, PagingVO pgvo) {
		// TODO Auto-generated method stub
		int totalCount = memberMapper.selectEmailTotalCount(email);
		List<BoardVO> list = memberMapper.getBoardList(email, pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount, list, 1);
		return ph;
	}
}