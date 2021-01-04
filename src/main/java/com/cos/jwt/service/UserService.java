package com.cos.jwt.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cos.jwt.domain.user.User;
import com.cos.jwt.domain.user.UserDTO;
import com.cos.jwt.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌 IoC를 해줌.
@RequiredArgsConstructor
@Service
public class UserService {

	
	private final UserRepository userRepository;
	
	@Transactional // 유저 회원가입
	public String join(@RequestBody User user) {
		System.out.println(user);
		userRepository.save(user);
		
		return "회원 가입 완료" +user;
	}
	@Transactional // 유저 정보
	public User info(User user) {
		User userEntity = userRepository.findById(user.getUserNo())
				.orElseThrow(() -> new IllegalArgumentException("없다"));
		
		return userEntity;
	}
	
	@Transactional //유저 수정
	public void modify(UserDTO userDto, User user) {
		User userModify = userRepository.findById(user.getUserNo())
				.orElseThrow(() -> new IllegalArgumentException("없다"));
		userModify.setAddress(userDto.getAddress());
		userModify.setName(userDto.getName());
		userModify.setEmail(userDto.getEmail());
		userModify.setAuth_pt(userDto.getAuth_pt());
	}
	
	@Transactional //유저 탈퇴
	public int remove(User user) {
		System.out.println(user);
		if(user != null) {
			userRepository.deleteById(user.getUserNo());
			
			return 1;
		}else {
			return 0;
		}
	}
	
	@Transactional //유저 목록
	public Page<User> userList(User admin,Pageable page){
		return userRepository.findAll(page);
	}
	
	@Transactional //트레이너 피티 등록 권한 주기
	public void userAuth(int userNo) {
		User trainer = userRepository.findById(userNo)
				.orElseThrow(() -> new IllegalArgumentException(userNo + "번의 유저는 없습니다."));
		if(trainer !=null) {
			trainer.setAuth_pt(3);
			System.out.println("권한 주기 성공!!");
		}else {
			
			System.out.println("권한 주기 실패");
		}
	}
	
	
	@Transactional
	public String login(@RequestBody User user) {
		
		userRepository.findByIdAndPassword(user.getId(), user.getPassword());
	
		return "로그인";
	
	}
	
	
}
