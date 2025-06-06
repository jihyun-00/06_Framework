package edu.kh.project.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.kh.project.board.model.dto.Comment;
import edu.kh.project.board.model.service.CommentService;
import lombok.extern.slf4j.Slf4j;

/* @RestController (REST API 구축을 위해서 사용하는 컨트롤러용 어노테이션)
 * 
 * => @Controller (요청/응답 제어 역할 명시 + bean으로 등록)
 *  + @ResponseBody (응답 본문으로 응답 데이터 자체를 반환)
 *  
 *  -> 모든 요청에 대한 응답을 응답 본문으로 반환하는 컨트롤러
 *  	(비동기 요청에 대한 컨트롤러)
 *   
 *   -> @ResponseBody 사용할 필요 X
 * 
 * */

//@Controller
@RestController// 모두 비동기이므로 다르게 사용 가능
@RequestMapping("comments") // 모든 요청은 비동기
@Slf4j
public class CommentController {
	
	@Autowired // 대신 final & @RequiredArgsConstructor 추가해도 가능
	private CommentService service;
	
	/** 댓글 목록 조회
	 * @param boardNo
	 * @return
	 */
	@GetMapping("")
	public List<Comment> select(@RequestParam("boardNo") int boardNo) {
		// List<Comment> (Java의 자료형 List)
		// HttpMessageConverter가
		// List -> JSON(문자열)로 변환해서 응답 -> JS
		
		return service.select(boardNo);
		
	}

	
	/** 댓글/답글 등록
	 * @return
	 */
	@PostMapping("")	// 요청받는 건 @RequestBody
	public int insert(@RequestBody Comment comment) {
		return service.insert(comment);
		
	}
	
	@DeleteMapping("")
	public int delete(@RequestBody int commentNo) {
		
		return service.delete(commentNo);
		
	}
	
	@PutMapping("")
	public int update(@RequestBody Comment comment) {
		log.debug("comment" + comment.getCommentContent());
		log.debug("comment" + comment.getCommentNo());
		return service.update(comment);
	}
}
