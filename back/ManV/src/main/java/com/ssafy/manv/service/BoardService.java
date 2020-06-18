package com.ssafy.manv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.manv.entity.Board;
import com.ssafy.manv.repo.BoardRepo;

@Service
public class BoardService{

	@Autowired
	private BoardRepo boardRepo;
	
	public List<Board> findAll() {
		return boardRepo.findAll();
	}
	
	public Optional<Board> findByBoardId(int board_id) {
		return boardRepo.findByBoardId(board_id);
	}
	
	public Board save(Board board){
		return boardRepo.save(board);
	}
	
	public void delete(Board board) {
		boardRepo.delete(board);
	}
	
}
