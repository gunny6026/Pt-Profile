package com.cos.jwt.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.boot.context.properties.bind.DefaultValue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor  //빈 생성자
@NoArgsConstructor  // 풀 생성자
@Data  // getter , setter , to  String
@Entity // DB에 테이블 생성
@Builder
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_no;
	
	@Column(unique = true)
	private String id;
    private String name;
    private String email;
    private String address;
    private boolean auth_pt;
    private String password;
	

    
}
