package com.ssafy.manv.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comment")
public class Comment implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="comment_id")
	private Integer commentId;
	
	@Column(name="boardId")
	private int boardId;
	
	private String email;
	private String ccontent;

	public Comment() {
		super();
	}

	public Comment(Integer commentId, int boardId, String email, String ccontent) {
		super();
		this.commentId = commentId;
		this.boardId = boardId;
		this.email = email;
		this.ccontent = ccontent;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", boardId=" + boardId + ", email=" + email + ", ccontent="
				+ ccontent + "]";
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCcontent() {
		return ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	
	
	
	
}
