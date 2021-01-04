package com.cos.jwt.domain.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.boot.context.properties.bind.DefaultValue;

import com.cos.jwt.domain.order.MpOrder;
import com.cos.jwt.domain.pt.Pt;
import com.cos.jwt.domain.wish.MpWish;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	@Column(name ="user_no")
    private int userNo;
	
	private String address;
	private int auth_pt;
	private String email;

	@Column(unique = true)
	private String id;
    private String name;
    private String password;
    
    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy ="user")
    private List<MpOrder> orders;
    
    @JsonIgnoreProperties({"user" , "orders"})
    @OneToMany(mappedBy ="user")
    private List<MpWish> wishs;
	
    @JsonIgnoreProperties({"user" ,"orders"})
    @OneToMany(mappedBy ="user")
    private List<Pt> pts;

    
}
