package com.ssafy.manv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.manv.entity.Comment;
import com.ssafy.manv.repo.CommentRepo;

@Service
public class CommentService {

	@Autowired
	private CommentRepo commentRepo;
	
	public List<Comment> findAll() {
		return commentRepo.findAll();
	}
	
	public Optional<Comment> findByCommentId(Integer comment_id) {
		return commentRepo.findByCommentId(comment_id);
	}
	public List<Comment> findAllByBoardId(Integer board_id){
		return commentRepo.findAllByBoardId(board_id);
	}
	
	public List<Comment> findAllByEmail(String email){
		return commentRepo.findAllByEmail(email);
	}
	
	public List<Comment> findAllByBoardIdAndEmail(Integer board_id, String email){
		return commentRepo.findAllByBoardIdAndEmail(board_id, email);
	}
	
	public Comment save(Comment comment){
		return commentRepo.save(comment);
	}
	
	public void delete(Comment comment) {
		commentRepo.delete(comment);
	}
}
