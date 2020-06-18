package com.ssafy.manv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.manv.entity.Member;
import com.ssafy.manv.repo.MemberRepo;


@Service
public class MemberService {

	@Autowired
	private MemberRepo memberRepo;

	public Member findByEmail(String email) {
		return memberRepo.findByEmail(email).orElse(null);
		//실패할 경우 null을 리턴
	}
	
	public List<Member> findAll(){
		return memberRepo.findAll();
	}
	public Member findByEmailAndPwd(String email, String pwd){
		if(pwd == null) {
			return null;
		}
		return memberRepo.findByEmailAndPwd(email, pwd).orElse(null);
	}
	
	public Member save(Member member) {
		return memberRepo.save(member);
	}
	
	
	public void delete(Member member) {
		memberRepo.delete(member);
	}
}
