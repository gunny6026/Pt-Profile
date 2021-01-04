package com.cos.jwt.order;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.jwt.domain.pt.Pt;
import com.cos.jwt.domain.user.User;
import com.cos.jwt.st.St;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class MpOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_no")
	private int orderNo;
	
	@ManyToOne
	@JoinColumn(name ="user_no")
	private User user;
	
	@ManyToOne
	@JsonIgnoreProperties({"user","orders"})
	@JoinColumn(name="or_pt_no")
	private Pt pt;
	
	@ManyToOne
	@JsonIgnoreProperties({"user","orders"})
	@JoinColumn(name="or_st_no")
	private St st;
	
	@CreationTimestamp
	private Timestamp orderDate;

}
