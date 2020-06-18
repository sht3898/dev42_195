package com.ssafy.manv.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.manv.entity.Board;


@Repository
public interface BoardRepo extends JpaRepository<Board, Integer> {

	Optional<Board> findByBoardId(int board_id);
	
	List<Board> findAll();

	List<Board> findAllByEmail(String email);


}
