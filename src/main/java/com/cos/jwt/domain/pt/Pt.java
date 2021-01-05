package com.cos.jwt.domain.pt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cos.jwt.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Pt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pt_no")
	private int ptNo;
	
	@Column(nullable = false)
	private String pt_name;
	
	
	@Column(nullable = false)
	private String pt_address;
	
	private String pt_img;
	
	@Column(nullable = false)
	private String pt_content;
	
	private int pt_price;
	
	@JoinColumn(name="pt_owner")
	@ManyToOne
	private User user;
	

}
