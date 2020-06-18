package com.ssafy.manv.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="board")
public class Board implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //default .AUTO
	@Column(name="board_id")
	private Integer boardId;
	
	private String title;
	private String host;

	@Column(name="apply_start")
	private String applyStart;

	@Column(name="apply_end")
	private String applyEnd;
	
	private String start;
	private String end;
	
	@Column(name="people_num")
	private int peopleNum;
	
	private int price; //총 상금으로 생각하자.
	private String location;
	private String info;
	private String img;
	private String email;
	
	@Column(name="post_date")
	private String postDate; 
	
	public Board() {
		super();
	}

	public Board(Integer boardId, String title, String host, String applyStart, String applyEnd, String start,
			String end, int peopleNum, int price, String location, String info, String img, String email,
			String postDate) {
		super();
		this.boardId = boardId;
		this.title = title;
		this.host = host;
		this.applyStart = applyStart;
		this.applyEnd = applyEnd;
		this.start = start;
		this.end = end;
		this.peopleNum = peopleNum;
		this.price = price;
		this.location = location;
		this.info = info;
		this.img = img;
		this.email = email;
		this.postDate = postDate;
	}

	@Override
	public String toString() {
		return "Board [boardId=" + boardId + ", title=" + title + ", host=" + host + ", applyStart=" + applyStart
				+ ", applyEnd=" + applyEnd + ", start=" + start + ", end=" + end + ", peopleNum=" + peopleNum
				+ ", price=" + price + ", location=" + location + ", info=" + info + ", img=" + img + ", email=" + email
				+ ", postDate=" + postDate + "]";
	}

	public Integer getBoardId() {
		return boardId;
	}

	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getApplyStart() {
		return applyStart;
	}

	public void setApplyStart(String applyStart) {
		this.applyStart = applyStart;
	}

	public String getApplyEnd() {
		return applyEnd;
	}

	public void setApplyEnd(String applyEnd) {
		this.applyEnd = applyEnd;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(int peopleNum) {
		this.peopleNum = peopleNum;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	
}
