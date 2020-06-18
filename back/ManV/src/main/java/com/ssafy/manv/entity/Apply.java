package com.ssafy.manv.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "apply")
public class Apply implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="apply_id")
	private Integer applyId;
	
	@Column(name="apply_date")
	private String applyDate;
	
	private String email;
	
	@Column(name="board_id")
	private Integer boardId;
	
	
	public Apply() {
		super();
	}


	public Apply(Integer applyId, String applyDate, String email, Integer boardId) {
		super();
		this.applyId = applyId;
		this.applyDate = applyDate;
		this.email = email;
		this.boardId = boardId;
	}
	

	@Override
	public String toString() {
		return "Apply [applyId=" + applyId + ", applyDate=" + applyDate + ", email=" + email + ", boardId=" + boardId
				+ "]";
	}


	public Integer getApplyId() {
		return applyId;
	}


	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}


	public String getApplyDate() {
		return applyDate;
	}


	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getBoardId() {
		return boardId;
	}


	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}
	
	
}
