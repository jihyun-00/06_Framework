package edu.kh.project.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.board.model.dto.Comment;
import edu.kh.project.board.model.mapper.CommentMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentMapper mapper;
	
	// 댓글 목록 조회 서비스
	@Override
	public List<Comment> select(int boardNo) {
		return mapper.select(boardNo);
	}

	// 댓글/답글 등록 서비스
	@Override
	public int insert(Comment comment) {
		log.debug("comment : " + comment.getCommentContent());
		return mapper.insert(comment);
	}

	// 댓글 삭제 서비스
	@Override
	public int delete(int commentNo) {
		return mapper.delete(commentNo);
	}

	// 댓글 수정 서비스
	@Override
	public int update(Comment comment) {
		return mapper.update(comment);
	}

}
