package com.cos.jwt.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cos.jwt.domain.pt.Pt;
import com.cos.jwt.domain.pt.PtRepository;
import com.cos.jwt.domain.user.User;
import com.cos.jwt.domain.user.UserRepository;
import com.cos.jwt.service.PtService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PtController {
	
	private final PtService ptService;
	private final HttpSession session;
	
	
	
	@PostMapping("/pt/write") //피티 등록
	public ResponseEntity<?> ptwrite(HttpServletRequest request, @RequestParam("pt_name") String pt_name , @RequestParam("pt_content") String pt_content, @RequestParam("pt_address") String pt_address , @RequestParam("pt_price") int pt_price, @RequestParam("pt_img") MultipartFile pt_img) {
		
	
		
		User trainer = (User) session.getAttribute("principal");
		ptService.ptWrite(request, trainer, pt_name, pt_content, pt_address, pt_price, pt_img);	
		
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
}
