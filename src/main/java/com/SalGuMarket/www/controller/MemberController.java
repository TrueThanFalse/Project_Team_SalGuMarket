package com.SalGuMarket.www.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SalGuMarket.www.domain.PagingVO;
import com.SalGuMarket.www.handler.PagingHandler;
import com.SalGuMarket.www.security.MemberVO;
import com.SalGuMarket.www.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/list")
	public void list (Model m, PagingVO pgvo) {
	    // 새로운 PagingVO 객체를 생성하고 qty를 9로 설정
		log.info("pgvo::::::::::::::::::"+pgvo);
	    int totalCount = memberService.getTotalCount(pgvo);
	    PagingHandler ph = new PagingHandler(pgvo, totalCount,9);

	    List<MemberVO> list = memberService.getList(pgvo);
		m.addAttribute("list", list);
		m.addAttribute("pgvo", pgvo);
		m.addAttribute("ph", ph);
		}
	
	@GetMapping("/mypage")
	public void mypage () {}
	
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
}
