package com.ssafy.manv.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.manv.entity.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
	
	Optional<Comment> findByCommentId(Integer comment_id);

	List<Comment> findAllByBoardId(Integer board_id);
	
	List<Comment> findAllByEmail(String email);
	
	List<Comment> findAllByBoardIdAndEmail(Integer board_id, String email);
	
}
