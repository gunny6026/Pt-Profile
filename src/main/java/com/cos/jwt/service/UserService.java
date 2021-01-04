package com.cos.jwt.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cos.jwt.domain.user.User;
import com.cos.jwt.domain.user.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌 IoC를 해줌.
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public String join(@RequestBody User user) {
		System.out.println(user);
		userRepository.save(user);
		
		return "회원 가입 완료" +user;
	}
	
	@Transactional
	public String login(@RequestBody User user) {
		
		userRepository.findByIdAndPassword(user.getId(), user.getPassword());
	
		return "로그인";
	
	}
	
	
}
