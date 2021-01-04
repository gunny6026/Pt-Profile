package com.cos.jwt.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.jwt.domain.pt.Pt;
import com.cos.jwt.domain.pt.PtRepository;
import com.cos.jwt.domain.user.User;
import com.cos.jwt.domain.user.UserRepository;
import com.cos.jwt.service.PtService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("all")
public class PtController {
	
	private final PtService ptService;
	private final HttpSession session;
	
	
	
	@PostMapping("/pt/write")
	public ResponseEntity<?> ptwrite(@RequestBody Pt pt) {
		
	
		
		User trainer = (User) session.getAttribute("principal");
		ptService.ptWrite(pt,trainer);
		
		return new ResponseEntity<String>("ok",HttpStatus.OK);
	}
}
