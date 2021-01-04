package com.cos.jwt.domain.board;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.cos.jwt.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_no")
	private int boardNo;
	private String title;
	
	@Lob
	private String content;
	
	@ManyToOne     //board를 select 해도 User 객체가 있기 때문에		
	@JoinColumn(name= "b_user_no") //User 테이블과 조인 한 결과 값을 준다.
	private User user;
	
	private Timestamp createDate;

}
