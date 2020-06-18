package com.ssafy.manv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.manv.entity.Apply;
import com.ssafy.manv.repo.ApplyRepo;

@Service
public class ApplyService {

	@Autowired
	ApplyRepo applyRepo;
	
	public Optional<Apply> findByApplyId(Integer apply_id) {
		return applyRepo.findByApplyId(apply_id);
	}
	
	public List<Apply> findAllByEmail(String email){
		return applyRepo.findAllByEmail(email);
	}
	
	public List<Apply> findAllByBoardId(Integer board_id){
		return applyRepo.findAllByBoardId(board_id);
	}

	public List<Apply> findAllByEmailAndBoardId(String email,Integer board_id){
		return applyRepo.findAllByEmailAndBoardId(email, board_id);
	}
	
	public Apply save(Apply apply) {
		return applyRepo.save(apply);
	}
	
	public void delete(Apply apply) {
		applyRepo.delete(apply);
	}
}
