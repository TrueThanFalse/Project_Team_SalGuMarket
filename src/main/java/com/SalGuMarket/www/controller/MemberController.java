package com.SalGuMarket.www.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SalGuMarket.www.domain.FileVO;
import com.SalGuMarket.www.domain.PagingVO;
import com.SalGuMarket.www.handler.FileHandler;
import com.SalGuMarket.www.handler.PagingHandler;
import com.SalGuMarket.www.security.MemberVO;
import com.SalGuMarket.www.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	
	private final MemberService memberService;
	
	private final PasswordEncoder passwordEncoder;
	private final FileHandler fileHandler;
	
	@GetMapping("/login")
	public void login() {}
	
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(MemberVO mvo,RedirectAttributes re) {
		log.info(">>>> mvo >>> " + mvo);
		mvo.setPwd(passwordEncoder.encode(mvo.getPwd()));
		int isOK = memberService.insert(mvo);
		if(isOK>0)re.addFlashAttribute("reg", "1");
		return "redirect:/";
	}
	
	@GetMapping("/mypage")
	public String mypage(HttpServletRequest request, Principal p, @RequestParam(name="email", required = false) String email, Model m) {
		log.info(">>>>>>>>>>>>>>>>>>>email"+email);
		if(email != null) {
			MemberVO mvo = memberService.selectEmail(email);
			m.addAttribute("mvo", mvo);
			FileVO fvo = memberService.getFile(email);
			if(fvo!=null) {
			String fileName = fvo.getFileName();
			m.addAttribute("fileName", fileName);
			}
			return "redirect:/member/otherspage?email="+email;
		}else {
			MemberVO mvo = memberService.selectEmail(p.getName());
			m.addAttribute("mvo", mvo);
			FileVO fvo = memberService.getFile(mvo.getEmail());
			if(fvo!=null) {
				String fileName = fvo.getFileName();				
				m.addAttribute("fileName", fileName);
				m.addAttribute("fvo", fvo);
			}
			log.info(">>>>>>>>>>>>.."+mvo);
			return "/member/mypage";
		}
	}
	
	@PostMapping("/profile")
	public String profile(@RequestParam(name="email") String email, @RequestParam(name="file", required = false)MultipartFile file, Model m) {
		FileVO fvo = null;
		MemberVO mvo = memberService.selectEmail(email);
		String nick = mvo.getNickName();
		if(file != null) {
			fvo = fileHandler.uploadProfile(nick, file);
		}else {
			//미선택시 기본 프사로
		}
		mvo.setFvo(fvo);
		mvo.setIsProfile(memberService.setProfile(mvo));
		m.addAttribute("mvo", mvo);
		return "redirect:/member/mypage";
	}
	
	@GetMapping("/otherspage")
	public void otherspage(@RequestParam(name="email", required = false) String email, Model m) {
		log.info(">>>>>>>>>>>>>>>>>>>"+email);
		MemberVO mvo = memberService.selectEmail(email);
		FileVO fvo = memberService.getFile(email);
		m.addAttribute("fvo", fvo);
		m.addAttribute("mvo", mvo);
	}
	
	@GetMapping("/{email}/{page}")
	@ResponseBody
	public PagingHandler list(@PathVariable("email")String email, @PathVariable("page")int page) {
		log.info(">>>> email >> " +email+"/ page >>" + page);
		//비동기 => 한 객제만 전송 가능
		PagingVO pgvo = new PagingVO(page, 5);
		PagingHandler ph = memberService.getBoardList(email, pgvo);
		return ph;
	}
	
	@GetMapping("/list")
	public void list (Model m, PagingVO pgvo) {
	    // 새로운 PagingVO 객체를 생성하고 qty를 9로 설정
		log.info("pgvo::::::::::::::::::"+pgvo);
	    int totalCount = memberService.getTotalCount(pgvo);
	    PagingHandler ph = new PagingHandler(pgvo, totalCount,9);

	    List<MemberVO> list = memberService.getList(pgvo);
	    
	    for(MemberVO mvo : list) {
	    	if(mvo.getIsProfile() == 1) {
	    		mvo.setFvo(memberService.getFile(mvo.getEmail()));
	    	}
	    }
	    
		m.addAttribute("list", list);
		m.addAttribute("pgvo", pgvo);
		m.addAttribute("ph", ph);
		}
	
//	관리자가 회원 탈퇴
	@GetMapping("/remove")
	public String remove (@RequestParam("email") String email, RedirectAttributes re) {
		log.info("email:::::::::::::::::::"+email);
		memberService.remove(email);
		re.addFlashAttribute("remove", "1");
		return "redirect:/member/list?pageNo=1&qty=9";
	}
	
//  회원 직접 탈퇴
	@GetMapping("/delete")
	public String delete(Principal p, RedirectAttributes re) {
		String email = p.getName();
		memberService.delete(email);
		//logout(req,res);
		re.addFlashAttribute("logout", "1");
		return "redirect:/member/login";
		
	}
	
	//아이디 중복확인
	@GetMapping(value ="/checkEmail/{email}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> checkEamil(@PathVariable("email") String email) {
	    log.info("email::::::::::::::" + email);
	    MemberVO mvo = memberService.selectEmail(email);
	    log.info("mvo::::::::::::::" + mvo);
	    return mvo == null ? new ResponseEntity<>("1", HttpStatus.OK) :
	            new ResponseEntity<>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//닉네임 중복확인
	@GetMapping(value ="/checkNick/{nickName}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> checkNick(@PathVariable("nickName") String nickName) {
		log.info("nick_name::::::::::::::" + nickName);
		MemberVO mvo = memberService.selectNickName(nickName);
		log.info("mvo::::::::::::::" + mvo);
		return mvo == null ? new ResponseEntity<>("1", HttpStatus.OK) :
			new ResponseEntity<>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
