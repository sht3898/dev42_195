package com.ssafy.manv.repo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.manv.entity.Member;


@Repository
public interface MemberRepo extends JpaRepository<Member, String> {

	Optional<Member> findByEmail(String email);
	
	Optional<Member> findByEmailAndPwd(String email, String pwd);
}
