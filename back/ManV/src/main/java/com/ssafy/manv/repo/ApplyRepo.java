package com.ssafy.manv.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.manv.entity.Apply;


@Repository
public interface ApplyRepo extends JpaRepository<Apply, Integer> {
	
	Optional<Apply> findByApplyId(Integer apply_id);
	
	List<Apply> findAllByEmail(String email);
	
	List<Apply> findAllByBoardId(Integer board_id);
	
	List<Apply> findAllByEmailAndBoardId(String email,Integer board_id);
	
}
