package com.cos.jwt.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cos.jwt.domain.pt.Pt;
import com.cos.jwt.domain.pt.PtRepository;
import com.cos.jwt.domain.user.User;

@Service
public class PtService {
	
	@Autowired
	PtRepository ptRepository;
	
	@Transactional
	public void ptWrite(@RequestBody Pt pt, User trainer) {
		
		
		ptRepository.save(pt);
		
		
	}

}
